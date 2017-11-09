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
