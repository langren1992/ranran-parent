# ranran-parent
* ranran-eureka-server `服务发现中心`
* ranran-admin-server `应用监控中心`
* ranran-config `服务配置管理`
* ranran-config-server `配置中心分发服务端/熔断被监控`
* ranran-config-client `配置中心使用客户端`
* ranran-hystrix-server `熔断监控中心`
## 应用监控
1. 应用中线管理的全部是服务中心发现的服务
2. /metrics unauthorized 问题需要在每个被监控的项目中去掉权限验证  
`management.security.enabled=false`
## 熔断器监控
1. `http://localhost:7001/hystrix.strean` 没有反应的情况下，表示熔断还没触发开启  
2. 需要高频访问`http://localhost:7001/order/1/100` 触发熔断效果
3. 出现`:ping`及相关结果，可以添加到`熔断监控中心`
## 配置使用客户中心
1. `resources`下的配置文件需是优先级高的`bootstrap.properties`（参考：https://springcloud.cc/spring-cloud-dalston.html 上下文引导）