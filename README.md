#### 微服务架构4个核心问题
 1. 服务很多，客户端该怎么访问？
 2. 服务很多，服务之间如何通信？
 3. 服务很多，如何治理？
 4. 服务挂了怎么办？

#### springcloud 生态 
springcloud可以解决上面-微服务架构4个核心问题
![输入图片说明](image/springcloud%E6%A1%86%E6%9E%B6%E5%9B%BE.png)

市面上存在的3套解决方案：
1. Spring Cloud NetFlix 一站式解决方案

   api网关，zuul组件
   
   Feign ---HttpClient--- Http通信方式，同步，阻塞
   
   服务注册发现：Eureka
   
   熔断机制：Hystrix
   
2. Apache Dubbo Zookeeper 半自动，需要整个别人的(Dubbo 这个方案并不完善~)

    API：没有，找第三方组件，或者自己实现
    
    Dubbo
    
    服务注册发现：Zookeeper
    
    没有：借助 Hystrix
    
3. Spring Cloud Alibaba 一站式解决方案，更简单

4. 新概念：服务网格 Server Mesh

#### ACID原则
 - A(Atomicity) 原子性
 - C(Consistency) 一致性
 - I(Isolation) 隔离性
 - D(Durbility) 持久性
 
#### CAP原则
 - C(Consistency) 强一致性
 - A(Availability) 可用性
 - P(Partition tolerance) 分区容错性
 
CAP 原则指的是，这三个要素最多只能同时实现两点，不可能三者兼顾。
 - CA:单点集群，满足一致性，可用性系统，通常可扩展性较差
 - CP:满足一致性，分区容错性的系统，通常性能不是特别高
 - AP:满足可用性，分区容错性的系统，通常可能对一致性要求低一些
 
#### Eureka 
- 服务注册与发现
- 集群配置
- Eureka AP原则  (如果连接失败了，会自动切换到其它节点，只不过查到的信息可能不是最新的。如果在15分钟内超过85%的节点都没有正常心跳，Eureka就认为客户端与注册中心出现了网络故障，此时会出现一下情况：1.Eureka不再从注册列表中移除因为长时间没收到心跳而应该过期的服务2.Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其它节点上3.当网络稳定时，当前实例新的注册信息会被同步到其它节点中)
- 对比Zookeeper CP原则  (如果master节点挂了，剩余节点会重新进行leader选举，选举期间整个集群不可用，由于选举时间太长（30~120s），所以无法容忍)

 对比Zookeeper结论：Eureka可以很好的应对网络故障导致部分节点失去联系的情况，而不会像Zookeeper那样使整个注册服务瘫痪

#### Ribbon
- 客户端负载均衡的工具
- 将用户的请求平摊的分配到多个服务上,从而达到系统的高可用
- 进程内负载均衡器
- 常见的负载均衡软件有Nginx,lvs等
- ribbon通过【微服务名称】进行访问
- IRule

#### Feign 
- HTTP的通讯方式，同步并阻塞
- 接口，社区要求，更加面向接口编程
- Feign通过【接口和注解】进行访问
- Feign集成了Ribbon

#### Hystrix 断路器
- 服务降级
- 服务熔断
- 服务限流
- 接近实时的监控
- dashboard
- 当失败的调用到一定的阈值，缺省是5秒内20次调用失败就会启动熔断机制。熔断机制注解是@HystrixCommand

#### Zuul 
- 微服务网关

#### Spring Cloud Config
- 服务统一配置中心
- C-S-GIT

#### Bus
- 消息总线

## 微服务技术栈有哪些

![输入图片说明](image/%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%8A%80%E6%9C%AF%E6%A0%88.png)

![输入图片说明](image/springcloud%E4%B8%8Edubbo%E5%AF%B9%E6%AF%94.png)


### 项目代码
    springcloud-provider-dept-8001 --- 服务提供者
    springcloud-consumer-dept-80 --- 服务消费者
    springcloud-eureka-7001  --- eureka服务注册中心
    将springcloud-provider-dept-8001 注册到eureka服务注册中心 http://localhost:7001/
    http://localhost:8001/actuator/health  http://localhost:8001/actuator/info
    如果EurekaService在一定时间内没有接受到某个微服务实例的心跳，EurekaService将会注销该服务实例（默认90秒）
    
    电脑C:\Windows\System32\drivers\etc\hosts
    模拟集群
    127.0.0.1 eureka7001.com
    127.0.0.1 eureka7002.com
    127.0.0.1 eureka7003.com
    启动集群，3个服务注册中心
    springcloud-eureka-7001
    springcloud-eureka-7002
    springcloud-eureka-7003
    将springcloud-provider-dept-8001 注册到eureka集群中
    
    springcloud-consumer-dept-80 --- 配置负载均衡
    3个微服务注册到Eureka中
    springcloud-provider-dept-8002 --- 配置多个服务提供端(3个微服务名称一致)
    springcloud-provider-dept-8003 --- 配置多个服务提供端(3个微服务名称一致)
 ![输入图片说明](image/ribbon.png)
 
    ribbon通过【微服务名称】进行访问
    Feign 通过【接口和注解】进行访问，Feign集成了Ribbon
    springcloud-api --- 编写Feign接口
    springcloud-consumer-dept-feign --- feign调用