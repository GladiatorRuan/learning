# 黑马程序员SpringBoot3+Vue3全套笔记

## 基础篇

### Java注解





### P8基础篇-07_Bean扫描

Bean扫描

- 标签: <context:compoonent-scan base-package="XXXX" />
- 注解:@ComponentScan(basePackages="XXXX")

 在开始的时候我们并没有写上述注解,仍然能够扫描到对应的包,原因在于

> ```java
> SpringBootApplication是一个组合注解,组合了以下三个注解
> @SpringBootConfiguration
> @EnableAutoConfiguration
> @ComponentScan
> ```

具体如图

![](/Users/friedrichruan/Documents/Docs/Markdown/Java学习笔记/B站黑马程序员SpringBoot/图片/SpringBootApplication注解.png)

Commponent注解不设置指定的枯井的时候仍然能够找到对应的包,原因在于默认扫描添加了@Component注解的包及其子包,如果想访问特定的包,可以使用注解@ComponentScan(basePackages="XXXX")

### P9基础篇-08_Bean注册

注解

| 注解        | 说明                 | 位置                                      |
| ----------- | -------------------- | ----------------------------------------- |
| @Component  | 声明Bean的基础注解   | 不属于以下三类时,用此注解                 |
| @Controller | @Componentde衍生注解 | 标注在控制器上                            |
| @Service    | @Component的衍生注解 | 标注在业务类上                            |
| @Repository | @Component的衍生注解 | 标注在数据访问上,由于语Mybatis整合,用得少 |

如果要注册的Bean对象来自于第三方(不是自定义的),是无法使用@Component及衍生注解声明的

- @Bean

  - 如果要注册来自第三方Bean,建议在配置类中集中注册,在配置类代码中使用@Configuration注解,在Bean中使用@Bean注解. 同时配置类在启动类目录中

  ```java
  @Confituration
  public class CommonConfig{
    @Bean
    public Resolver resolver(){
      return new Resolver();
    }
  }
  ```

  

- @Import



### P10 基础篇-09_Bean注册条件



## 实战篇

### P19 实战篇-07_JWT令牌

JWT全称Json Web Token(https://jwt.io), 组织结构如下:

- 第一部分:Header, 记录令牌类型,算法

  ```json
  {
    "alg": "HS256",
    "typ": "JWT"
  }
  ```

- 第二部分: Payload(有效载荷),携带一些自定义信息,默认信息等

  ```json
  {
    "sub": "1234567890",
    "name": "John Doe",
    "iat": 1516239022
  }
  ```

- 第三部分: Siganature(签名),防止Token被篡改、确保安全性.将header,payload,并加入指定密钥,通过指定签名算法计算而来.

  ```json
  HMACSHA256(
    base64UrlEncode(header) + "." +
    base64UrlEncode(payload),
    [your-256-bit-secret]
  )
  ```

JWT载荷中,不要存储私密信息,base64等算法是可逆的.

如果jwt抛出异常,可能是令牌被篡改或者令牌已经失效.



### P34-实战篇-22_文章分类列表查询(条件分页)

条件查询,分页使用mybatis的pageHelper,但是根据网上知识得知,分页插件[不支持3.0以上的版本](https://blog.csdn.net/m0_62327332/article/details/129786180), 最开始POM文件引用的是

```xml
<!--pageHelper坐标-->
<dependency>
   <groupId>com.github.pagehelper</groupId>
   <artifactId>pagehelper-spring-boot-starter</artifactId>
   <version>1.4.6</version>
</dependency>
```

但是由于没有集成到SpringBoot中,导致helper没有正确引用,使用的时候遭遇如下报错

```java 
class java.util.ArrayList cannot be cast to class com.github.pagehelper.Page (java.util.ArrayList is in module java.base of loader 'bootstrap'; com.github.pagehelper.Page is in unnamed module of loader 'app')
```

正确的POM文件引用方式如下:

```xml
<!-- 使用mybatis进行条件分页 -->
<dependency>
   <groupId>com.github.pagehelper</groupId>
   <artifactId>pagehelper</artifactId>
   <version>5.2.0</version>
</dependency>
```

解决之后开始引用是正常的了.

动态SQL的配置方式

```xml
<mapper namespace="SpringBoot.learning.mapper.ArticleMapper">
    <!-- 动态SQL -->
    <select id="list" resultType="SpringBoot.learning.pojo.Article">
        select * from article
        <where>
            <if test="categoryId!=null">
                category_id=#{categoryId}
            </if>
            <if test="state!=null">
                and state=#{state}
            </if>
            and create_user=#{userId}
        </where>
    </select>
```



### P40-实战篇-28_登陆优化

登陆优化使用redis进行优化,关于redis的信息处理如下:

1. 安装redis

   ```shell
   brew install redis
   ```

2. 激活启动redis服务端. 安装完毕后在终端运行命令

   ```shell
   brew services start redis
   ```

3. 启动客户端

   ```shell 
   redis-cli
   ```

4. 查询信息

   ```shell
   get $key //username
   ```

5. 关闭服务器

   ```shell
   brew services stop redis
   ```

在POM文件中使用依赖

```xml
<!-- 引入redis坐标 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
```



## 知识点篇

### 响应状态码设置

参考文章:[自定义异常，返回异常信息](https://blog.csdn.net/leinminna/article/details/125495548)
