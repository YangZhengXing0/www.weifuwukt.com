## spring session可实现集群环境下session会话共享
## springboot2.x spring-session-data-redis
springboot2.x 中spring-session-data-redis,默认就是配置了，只需要加入spring-session-data-redis
这个依赖包就会自动配置到容器中让我们使用
## 测试方式
把spring-session-data-redis依赖包删除测试和添加spring-session-data-redis依赖包测试，
效果是：添加了spring-session-data-redis依赖包的可以实现session会话共享
## 实现sesssion会话共享有很多中方式，但是最好的还是使用缓存或者使用令牌（个人觉得这两种方式比较好）