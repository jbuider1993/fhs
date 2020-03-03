
#    FHS-Framework 简介
 **fhs-framwork是一个集成了国内外诸多优秀开源项目的快速开发平台，除了在常规快速开发平台提供 用户，角色，权限，菜单，字典，操作日志，代码生成器 等功能的基础上，还在以下方面为您的快速开发做出了努力。**

#### 2.0升级日志：
 
      fhs-framework升级到了2.0，springboot和springcloud,jetcache,apollo 等依赖都进行了大版本升级和之前的项目可能会有些不兼容，如果需要之前版本请移步：https://gitee.com/fhs-opensource/fhs-framework/blob/v1.0.2
	  a 升级springboot版本为2.2.5 cloud版本升级到：Hoxton.SR1
	  b jetcache升级到2.5.16 并且使用lettuce连接redis
	  c shedlock升级到4.3 apollo client升级到了1.5
	  d 去掉了自定义的spring cache  manager定义，后续缓存统一使用jetcache
	  e 支持Apollo动态变更日志级别
	  f logback_fhs.xml 改为了logback-spring.xml


 
#### 1. 项目基础框架搭建期 
&#8194;&#8194;&#8194;&#8194;导入一份sql，copy一个pom稍作修改，copy3个配置文件稍作修改，copy一个springboot启动类稍作修改即可完成框架搭建。
 
#### 2. DB接入方面
##### - &#8194;&#8194;&#8194;&#8194;减少手写sql
   
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;fhs 使用了Mybatis Jpa+Mybatis Plus 框架来帮大家自动生成常见sql，Mybatis Jpa是fhs-opensource下的一款基于Mybatis的JPA的实现，为了补足复杂sql的生成，Mybatis Jpa 又对Mybatis Plus做了兼容，可以使用Mybatis Plus的注解来实现sql自动生成，有了Mybatis Jpa+Mybatis Plus 实现80%的单表查询无需写一行sql的效果
 
##### - &#8194;&#8194;&#8194;&#8194;数据源路由
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;通过简单的配置即可实现分库，分表，读写分离操作。

##### - &#8194;&#8194;&#8194;&#8194;声明式事物
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;  add，update，del，save 开头的service方法，会默认开启事物，自定义部分请使用注解。

##### - &#8194;&#8194;&#8194;&#8194;数据权限控制
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194; 通过简单的配置即可实现组合或者单一数据权限控制

#### 3. 日常业务方面

##### - &#8194;&#8194;&#8194;&#8194;大量的base类使用
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;通过继承即可完成大部分CRUD操作。
##### - &#8194;&#8194;&#8194;&#8194;提供常见的工具类
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;日期，日志，反射，网络，校验，文件等等。
##### - &#8194;&#8194;&#8194;&#8194;其他
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;统一验证框架处理器，统一异常处理器，控制器返回数据json字段过滤器，xss,csrf拦截器等等

#### 4. 前端封装
##### - &#8194;&#8194;&#8194;&#8194;给Easyui,Jquery Validform，My97做了整容手术
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;Easyui是一款颜值稍低但是功能强大上手容易开发效率极高的UI框架，为了让其能不被大家抛弃，继续让他发光发热，我们为其定制了一套BootStrap皮肤,效果堪比Layui。
##### - &#8194;&#8194;&#8194;&#8194;使用Beetl标签技术对常见的表单控件做了封装
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;后台程序员有句俗话叫做后端10分钟，前端2小时。前端是很多人不愿意碰触的，于是有了很多公司一个项目要招聘2波人，后端专门写后端，前端专门写前段，但是明明一个人就能搞定的事情，非得要2个人？使用fhs的标签，所有的控件做到了统一化，不需要自己写js去初始化，去做校验，去赋值，只要使用了标签，标签中初始化，布局html，赋值，获取值，校验的js就都包含了，很大程度上降低了前端的学习和使用成本。
##### - &#8194;&#8194;&#8194;&#8194;一款帮你写代码的引擎-PAGEX
&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;使用市面上的代码生成器，你做一个CRUD的需要多久呢？如果加上Excel导出，校验，列点击排序这些功能呢？如果要加数据权限，分库，支持多租户呢？要写多少后台代码，写多少sql，写多少js和html？使用pagex，你只需要定义一个JS，你需要的java类框架在运行期(非生成到硬盘上噢)直接给你编译为class扔到classLoader了，Html JS SQL 后台接口 按照指定的路径请求引擎也帮你自动生成，而你无需担心JS被暴露，因为JS仅仅被PAGEX引擎加载当做配置文件用的，既然是配置文件为何选择JS呢？第一：JAVA有JS引擎，可以执行JS代码；第二：JS中有JSON格式，做配置比XML和YML方便，比Properties强大；第三：很多CRUD我们需要自己写一些JS方法，来控制一些插件的隐藏显示以及一些前端业务，把他们写到JS文件中总比写到XML中强很多倍吧。

&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;PAGEX可以通过简单的配置自动生成CRUD代码，可实现导入，字段排序，数据权限，租户权限，字典翻译，表关联，各类表单插件一行json配置等功能，更让人惊喜的是，pagex的js可以通过Idea 的EasyCode插件自动生成，然后稍作改动就可以使用了。

&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;下面是使用PAGEX引擎的一个我们项目中月租户类型管理的demo
```javascript

    var modelConfig= {title:'月租户类型',pkey:'id',type:'uuid',orderBy:'update_time Desc',
        namespace:"parking_lease_type",table:'t_park_lease_type',trans:true,db:"park"};
    
    var listPage={
        listFieldSett:function(){
    	  return [
    		  {name:'lease_name',title:'类型名称',width:'20%',align:'center'},
              {name:'park_id',title:'停车场名称',width:'20%',isJoin:true,namespace:'parking',showField:'transMap.parkName',align:'center'},//自动表关联
              {name:'is_disable',title:'是否禁用',width:'10%',formart:'formatRowColor',align:'center',trans:'book',key:'is_disable',showField:'transMap.is_disableName'},//字典翻译
              {name:'create_user',title:'创建人',width:'8%',align:'center',trans:'user',showField:'transMap.create_userUserName'},//用户翻译
              {name:'create_time',title:'创建时间',width:'10%',align:'center'},
              {name:'update_user',title:'更新人',width:'8%',align:'center',trans:'user',showField:'transMap.create_userUserName'},
              {name:'update_time',title:'更新时间',width:'10%',align:'center'},
              {name:'is_sync',title:'是否已下发',width:'5%',align:'center',trans:'book',key:'yesOrNo',showField:'transMap.is_syncName'},//字典翻译
      ]},
      isColumnButton:function(){
    	  return  false;
      },
      filters:function(){
          return [
              {name:'park_id',type:'select',url:'${path.basePath}/ms/x/parking/findListData',
                  valuefield:'id',textfield:'parkName',title:'停车场'},//下拉插件
              {name:'lease_name',type:'input',title:'出入口名称',filterType:'like'},
    	  ];      
      }, 
      buttons:function(){
          return [
              //自定义按钮数组
          ];
      },
      disableButtons:function(){
    	    return [];//禁用掉默认提供的按钮 默认提供了增删改查 + 导出
      },
      otherFunctions:function(){
          return {}//其他的自定义方法
      }
    };
    
    var add={ 
    	formFields:function(){//表单内容
    	     return [
                 {name:'park_id',type:'select',url:'${path.basePath}/ms/x/parking/findListData',
                     valuefield:'id',textfield:'parkName',title:'停车场',required:true,},//一个下拉
                 {name:'lease_name',title:'名称',required:true,type:'input'},//一个input
                 {name:'is_disable',title:'是否禁用',type:'switch',dft:false},//一个开关滑块
                 {name:'is_sync',title:'是否下发',type:'hide'},//一个隐藏域
    		 ];
    	},
        otherFunctions:function(){
          return {
    	     ready:function(){
    	    },
    	    loadSuccess:function(info){//加载后台数据成功的事件
    
    	    },
    	    onSave:function(){//保存前执行方法
                $('#isSync').val(0);
    	    },
    		saveSucess:function(){//保存成功执行方法
    	    },
    		saveError:function(){//保存失败执行的方法
    		    
    	    },
    	  }		
       }
    }
```
#### 5. 翻译服务
 &#8194;&#8194;&#8194;&#8194;翻译服务用于根据表中存放的id来翻译出对应的文字给做客户做显示使用，系统默认实现对省市区，后台用户，部门，字典的翻译支持，您通过简单的几行js配置即可把自己的表维护的翻译服务中，翻译服务使用进程缓存，当数据有更新的时候会自动重新加载(支持分布式)，有着很高的效率，可减少表关联sql的书写。
 
 &#8194;&#8194;&#8194;&#8194;自定义表加入到翻译服务demo(使用pagex方法):
  ```javascript
  var modelConfig= {title:'停车场',pkey:'id',type:'uuid',orderBy:'update_time Desc',
    namespace:"parking",table:'t_park_parking',trans:true,extendsParam:'parent_park_id=${param.parent_park_id}',
    joinColumns:JSON.stringify({park_name:'parkName'}),db:"park",dp:JSON.stringify({id:'parkIds'}),isMultiTenant:true};

  ```
 &#8194;&#8194;&#8194;&#8194;使用：
 
```javascript
   {name:'park_id',title:'停车场名称',width:'20%',trans:'pagex',key:'parking',showField:'transMap.parkName',align:'center'},//自动表关联
 ```
 &#8194;&#8194;&#8194;&#8194;或者在java代码中使用
```java
      @Trans(type="pagex",key="parking")
	  private String parkId;
 ```
#### 6. c端支持
 &#8194;&#8194;&#8194;&#8194;支持微信，支付宝 用户自动登陆接入。
 
#### 7.支持分布式和单机部署模式

 &#8194;&#8194;&#8194;&#8194;项目集成了分布式模式和单机模式2种模式，分布式模式依赖比较多，需要安装apollo(分布式配置中心)，CAS，redis，启动文件服务jar包，静态文件jar包，eureka jar包。
	 单机模式，可以把文件服务的依赖，静态文件的依赖，都添加到自己项目的依赖中，可以不依赖apollo，使用本地配置文件启动。。

#### 技术栈
- 前端:Easyui(美化过的Easyui),Layui(首页)，Validform，My 97(定制过主题)。
- 后端校验：hibernate vilidator。
- 后端：SpringBoot 2.2.5 + Springcloud（可选）
- ORM：Mybatis(基础)+JPA(一对一&一对多查询，数据权限注解)+Plus(条件查询器)
- 模板引擎：beetl+JSP
- 无后端业务的快速开发引擎:PAGEX
- 分布式配置：Apollo
- 缓存：jetcache+spring data cache
- 分布式任务：shedlock



#### 使用说明

 &#8194;&#8194;&#8194;&#8194;1. 早期写的部分 文档   http://114.116.20.119/  (建设中)

 &#8194;&#8194;&#8194;&#8194;2  新出炉的文档 https://gitee.com/fhs-opensource/fhs-framework/wikis/pages

  

#### 参与贡献获取技术支持
官方QQ 群：976278956

体验地址：http://114.116.20.119:8081/   admin  123456
         