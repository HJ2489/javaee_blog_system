# javaee_blog_system项目安装说明

## 项目基本配置

| 配置       | 版本          |
| ---------- | ------------- |
| SpringBoot | 2.1.3.RELEASE |
| jdk        | 1.8.0_372     |
| maven      | 3.8.8         |
| redis      | 3.0.054       |

## 项目运行步骤

### 1.创建数据库	

首先在`src/main/resources/SQL`路径下可以找到一个名为`blog_system.sql`的sql文件，将其到相应的数据库软件（我使用的是navicat15）中运行创建数据库 。

### 2.在ide中运行项目

#### 2.1 打开项目

打开项目方式推荐选择导入项目（原因在于如果项目不是idea模型，使用import可以避免许多不必要的麻烦），具体步骤如下：

- 点击`file->new->Project from Existing Sources`![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604193928793.png)
- 选择本项目的存储路径，并选择本项目，点击OK，选择`import project from external model -> maven`， 然后点击create![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604194117021.png)

#### 2.2 修改maven

打开项目后需要修改maven配置，点击`file->settings`，然后搜索`maven`，将下面三处修改为你的maven配置。

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604193343602.png)

#### 2.3 修改项目配置

点击`file->settings`，搜索complier，将项目的jdk版本调成8

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604194606434.png)

将以下地方的版本修改成1.8或8

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604194347725.png)

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604194415647.png)

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604194444288.png)

#### 2.4 导入pom

找到项目目录下的pom.xml文件，打开

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604194808941.png)

然后点击右侧的maven，点击图中按钮等待导入相关依赖。

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604194929068.png)

#### 2.5启动redis

我用的redis版本是3.0.504，下载链接：[redis](https://github.com/MicrosoftArchive/redis/releases "redis3.0.504下载链接")()

下载之后解压之后进入文件夹，然后运行`redis-server.exe`

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604195049789.png)

当显示以下界面表示redis启动成功

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604195710684.png)

#### 2.6启动项目

在idea中启动项目，当出现以下界面表示项目启动成功。

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604195748123.png)

#### 2.7 访问浏览器

在浏览器的地址栏输入localhost

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604195841891.png)

点击登录

![](http://typora-imagehost.oss-cn-guangzhou.aliyuncs.com/img/image-20230604195908505.png)

这里提供两组用户名和密码

| 用户名 | 密码   | 是否拥有进入后台管理的权限 |
| ------ | ------ | -------------------------- |
| admin  | 123456 | 是                         |
| tom    | 123456 | 否                         |

> 注：数据库中提供的用户的密码都是123456



本项目的github地址：[github](https://github.com/HJ2489/javaee_blog_system)
