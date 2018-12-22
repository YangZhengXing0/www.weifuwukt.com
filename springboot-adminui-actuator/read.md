springboot 2.0 整合adminUi分布式微服务监控中心。AdminUI基于actuator实现能够返回界面展示监控信息，
AdminUI当作服务端，actuator配置的服务当作客户端，actuator配置的服务注册到服务端，并以json的格式把数据发给服务端展示出来；
## 启动访问
先启动服务端admin-ui-server，然后访问：http://localhost:8080/；
在启动客户端admin-ui-client，然后访问：http://localhost:8080/；
发现admin-ui-client服务注册到admin-ui-server平台上去了，点击wallboard或者可以点击应用查看信息，记住不能点击那个地址，是点击地址下面的一串数字