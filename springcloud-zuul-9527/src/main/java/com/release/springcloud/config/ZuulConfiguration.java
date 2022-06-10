package com.release.springcloud.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.cglib.proxy.*;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.web.ZuulController;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 解决错误：NoSuchMethodError: org.springframework.boot.web.servlet.error.ErrorController.getErrorPath
 * 错误出现原因：最新的 spring-boot 2.5.5以上版本，从 ErrorController 中删除了 getErrorPath() API，但spring-cloud-starter-netflix-zuul 2.2.7.RELEASE以上版本 仍然调用此 API 并导致此错误。
 *
 * @author yancheng
 * @since 2022/6/10
 */
@Configuration
public class ZuulConfiguration {
    /**
     * The path returned by ErrorController.getErrorPath() with Spring Boot < 2.5
     * (and no longer available on Spring Boot >= 2.5).
     */
    private static final String ERROR_PATH = "/error";
    private static final String METHOD = "lookupHandler";

    /**
     * Constructs a new bean post-processor for Zuul.
     *
     * @param routeLocator    the route locator.
     * @param zuulController  the Zuul controller.
     * @param errorController the error controller.
     * @return the new bean post-processor.
     */
    @Bean
    public ZuulPostProcessor zuulPostProcessor(@Autowired RouteLocator routeLocator,
                                               @Autowired ZuulController zuulController,
                                               @Autowired(required = false) ErrorController errorController) {
        return new ZuulPostProcessor(routeLocator, zuulController, errorController);
    }

    private enum LookupHandlerCallbackFilter implements CallbackFilter {
        INSTANCE;

        @Override
        public int accept(Method method) {
            if (METHOD.equals(method.getName())) {
                return 0;
            }
            return 1;
        }
    }

    private enum LookupHandlerMethodInterceptor implements MethodInterceptor {
        INSTANCE;

        @Override
        public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            if (ERROR_PATH.equals(args[0])) {
                // by entering this branch we avoid the ZuulHandlerMapping.lookupHandler method to trigger the
                // NoSuchMethodError
                return null;
            }
            return methodProxy.invokeSuper(target, args);
        }
    }

    private static final class ZuulPostProcessor implements BeanPostProcessor {

        private final RouteLocator routeLocator;
        private final ZuulController zuulController;
        private final boolean hasErrorController;

        ZuulPostProcessor(RouteLocator routeLocator, ZuulController zuulController, ErrorController errorController) {
            this.routeLocator = routeLocator;
            this.zuulController = zuulController;
            this.hasErrorController = (errorController != null);
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (hasErrorController && (bean instanceof ZuulHandlerMapping)) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(ZuulHandlerMapping.class);
                enhancer.setCallbackFilter(LookupHandlerCallbackFilter.INSTANCE); // only for lookupHandler
                enhancer.setCallbacks(new Callback[] {LookupHandlerMethodInterceptor.INSTANCE, NoOp.INSTANCE});
                Constructor<?> ctor = ZuulHandlerMapping.class.getConstructors()[0];
                return enhancer.create(ctor.getParameterTypes(), new Object[] {routeLocator, zuulController});
            }
            return bean;
        }
    }
}
