# Spring-NoneConf-Demo
小型springboot，几乎取消了xml配置文件，都在代码里配置


一些老项目使用的tomcat,jdk版本较老，都是单个tomcat封装了一层，带多个webapp


我这个除了log没做之外，基本上都有了，hibernate自动建表，mybatis实现业务增删改查

为什么多次一举，不直接用Spingboot呢?

实际上我参加的这个项目tomcat对catalina.policy限制的很严格，Springboot的项目要想成功部署，需要很高的java权限，所以折中了这个办法
