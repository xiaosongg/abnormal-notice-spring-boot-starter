# 一个异常通知的spring-boot-start框架 abnormal-notice-spring-boot-starter

# V1.0.0

## 介绍
异常通知插件，对于项目中出现的异常进行捕获，并发送给对应的第三方用于接收异常信息，目前只整合了邮箱和企业微信机器人，其它发送方式开发者可自己实现

## 前言
对于工程的开发，必然会伴随着各种bug，工程量越大，出现bug的概率也会越高。一般对于代码量较小的工程来说，一个人可能就足够去做开发与维护；但是对于代码量较大的工程往往是需要一个小团队协作开发。当工程基本完成，开始部署测试环境或者生产环境时，这些环境并不能像开发环境一样能快速的调试与维护，线上的工程一旦出现异常时，开发团队就需要主动感知异常并协调处理，当然人不能一天24小时去盯着线上工程，所以就需要一种机制来自动化的对异常进行通知，并精确到谁负责的那块代码。这样会极大地方便后续的运维。因此，本项目上线。


## 系统需求

![jdk版本](https://img.shields.io/badge/java-1.8%2B-red.svg?style=for-the-badge&logo=appveyor)
![maven版本](https://img.shields.io/badge/maven-3.2.5%2B-red.svg?style=for-the-badge&logo=appveyor)
![spring boot](https://img.shields.io/badge/spring%20boot-2.0.0.RELEASE%2B-red.svg?style=for-the-badge&logo=appveyor)


## 最快上手

- 将此工程通过``mvn clean install``打包到本地仓库中。
- 在你的工程中的``pom.xml``中做如下依赖

```
        <dependency>
            <groupId>com.wusong</groupId>
            <artifactId>abnormal-notice-spring-boot-starter</artifactId>
            <version>1.0.0-personal</version>
        </dependency>

```

### 邮箱通知
- 在``application.properties``或者``application.yml``中做如下的配置：

```
spring:
  mail:
    host: smtp.qq.com
    port: 25
    username: 开启smtp权限的邮箱用户名
    password: 密码
abnormal:
  enabled: true
  project-name: testDemo
  notice:
    enabled: true
    email:
      bcc: xxx@qq.com
      to: xxx@qq.com
      cc: xxx@qq.com
      enabled: true
    listen-type: common
    enable-async: true
    included-trace-package: com.wusong
```
具体说明如下：

|名称|参数类型|说明|必要配置|
|:-:|:-:|:-:|:-:|
|enabled|boolean|用于开启项目配置，属于总控开关|是|
|project-name|string|一般忽略，以spring.application.name替代|否|
|notice.enabled|boolean|用于开启异常通知|否|
|notice.listen-type|enum|监听类型，可扩展|否|
|notice.enable-async|boolean|是否开启异步通知，默认为false|否|
|notice.included-trace-package|string|异常追踪的包路径，一般情况下，此配置项就是配置你工程的包路径就可以了|否|
|notice.email.enabled|boolean|用于开启邮箱通知|否|
|notice.email.bcc|string|秘密抄送给谁|否|
|notice.email.to|string|发给谁|否|
|notice.email.cc|string|抄送给谁|否|


- 以上配置好以后就可以写demo测试了：

```
@RestController
@MessageNotice // 异常通知的监控来自这个注解
@Api(value = "DemoController", tags = "测试异常通知")
@RequestMapping("/demo")
public class DemoController {

    @ApiOperation("通知")
    @PostMapping("/testErrorNotice")
    public void testErrorNotice(String errorName) {

        throw new NullPointerException(errorName);
    }
}
```

调用测试接口后，假如邮箱配置没有问题的话，你填写的邮箱中就会出现如下类似的消息：
![效果](/src/main/resources/mail.png)
假如在你配置的邮箱中出现类似这个信息的话，那么恭喜你，你成功的产生了一个邮箱异常通知。



### 企业微信机器人通知
- 在``application.properties``或者``application.yml``中做如下的配置：

```
abnormal:
  enabled: true
  project-name: testDemo
  notice:
    enabled: true
    wechat:
      enabled: true
      key: 企业微信机器人唯一key
    listen-type: common
    enable-async: true
    included-trace-package: com.wusong
```

具体说明如下：

|名称|参数类型|说明|必要配置|
|:-:|:-:|:-:|:-:|
|enabled|boolean|用于开启项目配置，属于总控开关|是|
|project-name|string|一般忽略，以spring.application.name替代|否|
|notice.enabled|boolean|用于开启异常通知|否|
|notice.listen-type|enum|监听类型，可扩展|否|
|notice.enable-async|boolean|是否开启异步通知，默认为false|否|
|notice.included-trace-package|string|异常追踪的包路径，一般情况下，此配置项就是配置你工程的包路径就可以了|否|
|notice.wechat.enabled|boolean|用于开启企业微信机器人通知|否|
|notice.wechat.key|string|企业微信机器人唯一key|否|

- 以上配置好以后就可以写demo测试了：
首先在项目启动类添加@ForestScan注解
```
@SpringBootApplication
@ForestScan(basePackages = "com.ws.an.httpclient")
public class WuSongApplication {

	public static void main(String[] args) {
		SpringApplication.run(WuSongApplication.class, args);
	}

}
```

然后编写测试接口：
```
@RestController
@MessageNotice
@Api(value = "DemoController", tags = "测试异常通知")
@RequestMapping("/demo")
public class DemoController {

    @ApiOperation("通知")
    @PostMapping("/testErrorNotice")
    public void testErrorNotice(String errorName) {

        throw new NullPointerException(errorName);
    }
}

```
调用测试接口后，假如企业微信机器人配置没有问题的话，你填写的机器人就会发送类似的消息：
![效果](/src/main/resources/wechat.png)
假如在你配置的邮箱中出现类似这个信息的话，那么恭喜你，你成功的产生了一个邮箱异常通知。

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request
