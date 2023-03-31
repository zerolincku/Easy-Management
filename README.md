# Easy-Management

Easy-Management 基于SpringBoot、Spring Security实现的前后端分离的权限管理系统

| 使用技术               | 版本            |
| ---------------------- |---------------|
| SpringBoot             | 2.3.2.RELEASE |
| MySQL                  | 8.0.27        |
| Druid                  | 1.1.16        |
| Mybatis-plus           | 3.2.0         |
| Mybatis-plus-generator | 3.3.1.tmp     |
| Spring Security        | 2.3.2.RELEASE |
| Jjwt                   | 0.9.0         |
| Swagger2               | 2.9.2         |
| Knife4j                | 2.0.4         |
| Hutool                 | 4.5.7         |
| Junit                  | 4.13          |
| Quartz                 | 2.3.2         |
| pagehelper             | 5.1.10        |

前端项目：https://gitee.com/zerolinck/easy-management-ui

Demo地址：http://119.3.159.234:8081/#/login

访客账户/密码（无修改数据权限）：test/123456

swagger地址：http://139.224.44.141:8089/doc.html

## 项目截图

![image-20200824222859241](https://gitee.com/zerolinck/Easy-Management/raw/master/doc/image/屏幕截图2020-11-09100504.jpg)

![image-20200824222938586](https://gitee.com/zerolinck/Easy-Management/raw/master/doc/image/屏幕截图2020-11-09095906.png)

![image-20200824222938586](https://gitee.com/zerolinck/Easy-Management/raw/master/doc/image/屏幕截图2020-11-09095943.png)

![image-20200824222859241](https://gitee.com/zerolinck/Easy-Management/raw/master/doc/image/image-20200824222859241.png)

![image-20200824222938586](https://gitee.com/zerolinck/Easy-Management/raw/master/doc/image/image-20200824222938586.png)

## 本地运行方法
1. 运行 db/management.sql，创建 management 数据库
2. 添加下述内容到 hosts 文件
```
# management
127.0.0.1 management-mysql
127.0.0.1 management-redis
127.0.0.1 management-app
```
3. 运行 ManagementApplication 启动类

## Docker-compose 运行项目方法
1. 执行命令 `docker-compose --profile deploy up`
2. 停止并删除容器 `docker-compose --profile deploy down`

## Docker-compose 单元测试方法
> 本地单元测试使用 `mvn -Pdev test` 就行了，docker-compose 是为了 Jenkins CI 时，一键启动运行项目所需的环境（数据库，Redis等），从而能够进行单元测试
1. 执行命令 `docker-compose --profile test up`
2. 停止并删除容器 `docker-compose --profile test down`

## 使用 Jenkins 所需插件
本项目的 Jenkinsfile 执行，需要在 Jenkins 中额外安装一下插件 `DingTalk`、`Gitee Plugin`、`Docker`、`Docker pipeline`
