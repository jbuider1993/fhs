# fhs-framework 基于springBoot的快速开发平台

#### 介绍
目前开源社区中有各种各样的快速开发平台，一般都包含了基础的菜单，角色，用户，日志，权限，字典，在加一个代码生成器，就算快速开发平台了（这些功能我们都有），fhs 和他们有什么不同呢（一定要看到最后）？

1   mybatis jpa
      是mybatis 对于jpa注解的一种实现，可以不写一行代码实现crud + 批处理，一对一，一对多，数据权限，分页操作，如果你习惯了mybatis plus，他本身继承了mybatis plus，兼容mp所有注解，你可以用jpa注解，也可以用mp注解，复杂的查询交给mp，简单的用mpj。

2  数据权限
    通过几行配置即可完成数据权限接入，支持多重数据权限，比如通过资源  部门，和分类  做双重数据权限过滤

3 数据源路由
     支持分库分表读者分离，简单的配置下就好。
    分库支持不同的业务在不同的表中，或者相同的表在不同的库中，根据一个标志动态切换数据源，基于aop+自定义datasource

4  声明式事物
     add，update，del，save 开头的service方法，会默认开启事物，自定义部分请使用注解。


#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)