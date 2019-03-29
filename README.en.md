# fhs-framework 基于springBoot的快速开发平台

#### Description
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


#### Software Architecture
Software architecture description

#### Installation

1. xxxx
2. xxxx
3. xxxx

#### Instructions

1. xxxx
2. xxxx
3. xxxx

#### Contribution

1. Fork the repository
2. Create Feat_xxx branch
3. Commit your code
4. Create Pull Request


#### Gitee Feature

1. You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2. Gitee blog [blog.gitee.com](https://blog.gitee.com)
3. Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4. The most valuable open source project [GVP](https://gitee.com/gvp)
5. The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6. The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)