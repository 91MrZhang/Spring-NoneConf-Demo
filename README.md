# Spring-NoneConf-Demo
## SpringMVC的注解版

### 为什么搞这么一处呢？
因为熟悉了SpringBoot这种约定大于配置的模式之后，再用XML启动一个SpringMVC的项目，就有点逼迫强迫症了。


而我在的项目中，Tomcat对catalina.policy限制的比较严格，如果想把SpringBoot项目打成war包,需要Tomcat开设很高的反射权限，很明显这是很麻烦的。

所以就有了这个小脚手架

1、它集成了Hibernate、Mybatis，可以用Hibernate自己建表，用Mybatis自定义业务查询；总之就是比较现代化了。

2、这个主要是实现了一下ServletContainerInitializer接口，才能加载时启动，不过也得Servlet3.0才能启动。如果比这个低，还是得写web.xml。
