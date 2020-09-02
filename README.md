# spring-boot-rocketmq

#### 介绍
springboot+rocketmq，普通消费，顺序消费，事务消费，rocketmq-spring-boot-starter 2.1.1，rocketmq-client 4.7.1

#### 软件架构
软件架构说明
spring-boot 2.3.3
rocketmq 4.7.1

#### 安装教程

1.  下载rocketmq 4.7.1客户端
    下载地址：https://www.apache.org/dyn/closer.cgi?path=rocketmq/4.7.1/rocketmq-all-4.7.1-bin-release.zip
    解压压缩包
2.  打开D:\rocketmq-all-4.7.1\conf目录下的broker.conf文件，添加下面两行内容
    namesrvAddr = 127.0.0.1:9876
    brokerIP1 = 127.0.0.1
3.  进入rocketmq-all-4.7.1\bin目录，
    打开runbroker.cmd文件，liunx系统改为.sh文件
    修改内容：set "JAVA_OPT=%JAVA_OPT% -server -Xms100m -Xmx100m -Xmn50m"
    打开runserver.cmd文件，
    修改内容：set "JAVA_OPT=%JAVA_OPT% -server -Xms100m -Xmx100m -Xmn50m -XX:MetaspaceSize=12m -XX:MaxMetaspaceSize=32m"
    打开tools.cmd文件，
    修改内容：set "JAVA_OPT=%JAVA_OPT% -server -Xms100m -Xmx100m -Xmn25m -XX:MetaspaceSize=12m -XX:MaxMetaspaceSize=32m"
3.  选择部署控制台rocketmq-console
    原始仓库地址：https://github.com/apache/rocketmq-externals.git
    码云仓库地址：https://gitee.com/mirrors/RocketMQ-Externals.git
    修改application.properties文件，修改以下三行内容：
    server.servlet.context-path=/rocketmq
    server.port=xxxx
    rocketmq.config.namesrvAddr=127.0.0.1:9876
4.  springboot工程maven引入依赖
    <dependency>
      <groupId>org.apache.rocketmq</groupId>
      <artifactId>rocketmq-spring-boot-starter</artifactId>
      <version>2.1.1</version>
    </dependency>
    <dependency>
      <groupId>org.apache.rocketmq</groupId>
      <artifactId>rocketmq-client</artifactId>
      <version>4.7.1</version>
    </dependency>

#### 使用说明
1.  启动rocketmq：
    cd rocketmq-all-4.7.1\bin目录，执行下面两个命令分别启动
    start mqnamesrv.cmd
    start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
2.  启动rocketmq-console：
    cd rocketmq-externals/rocketmq-connect-console目录下，
    执行打包命令：mvn clean package -Dmaven.test.skip=true
    运行jar包，target目录下执行：java -jar rocketmq-console-ng-2.0.0.jar
3.  idea或eclipse导入spring-boot-rockeqmq工程启动或打包运行

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
