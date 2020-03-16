package com.fhs.core.db.ds;

import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.exception.BusinessException;
import com.fhs.logger.Logger;
import com.mybatis.jpa.annotation.CatTableFlag;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 动态分库 适用于读写分离+主业务根据某种条件分库的情况 比如 用户和订单，
 * 用户在main库订单根据一定的算法分散到不同的库中，则需要使用本方案.
 * 本类阅读请从 determineReadOrWriteDB 方法开始
 * @Filename: DynamicCatDBProcessor.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: 92188199@q.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class DynamicCatDBProcessor extends ReadWriteDataSourceProcessor
{

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(DynamicCatDBProcessor.class);

    /** 分表的CatDBFlag 在第几个参数中. */
    private Map<Method, Integer> methodCatDBFlagParamIndexMap = new HashMap<>();

    /** CatDBFlag 是不是在一个map中. */
    private Map<Method, Boolean> methodCatDBFlagParamIsMap = new HashMap<>();

    /** CatDBFlag 是不是在一个bean中. */
    private Map<Method, Boolean> isBeanCattableFlagMap = new HashMap<>();

    /** 如果是bean的话，这个bean是第几个参数. */
    private Map<Method, Integer> methodBeanIndexMap = new HashMap<>();

    /** 如果是个bean的话，那么这个bean CatDBFlag 对应的字段是哪个. */
    private Map<Method, Field> fieldBeanMap = new HashMap<>();

    /** 如果在map中获取CatDBFlag 那么map的key是什么. */
    private Map<Method, String> methodCatDBFlagParamMapKeyMap = new HashMap<>();

    /** 只有一个库，不做读写分离. */
    private static final int DATASOURCE_TYPE_DEFAULT = 1;

    /** 做读写分离，但是不做分表分库. */
    private static final int DATASOURCE_TYPE_MS = 2;

    /** 做读写分离，做分表，不做分库. */
    private static final int DATASOURCE_TYPE_MS_CAT_TABLE = 3;


    /** 做读写分离，做分表，分库. */
    private static final int DATASOURCE_TYPE_MS_CAT_TABLE_DB = 4;

    /** 第一种，不做读写分离，也不做分表分库 第二种，做读写分离，但是不做分表分库 第三种，做读写分离，做分表，不做分库 第四种，做读写分离，做分表，分库. */
    private Map<String, Integer> datasourceSettMap;

    /** key zj的名字 val是choose的对象. */
    Map<String,DataSourceNameChoose> dataSourceChooseMap = null;

    /**
     * Instantiates a new dynamic cat DB processor.
     */
    public DynamicCatDBProcessor()
    {
        super();
    }

    /**
     * service方法执行的切面
     *
     * @param pjp the pjp
     * @return the object
     * @throws Throwable the throwable
     */
    public Object determineReadOrWriteDB(ProceedingJoinPoint pjp)
        throws Throwable
    {

        String datasourceName = getDataSourceName(pjp);
        ReadWriteDataSourceDecision.markOther();
        ReadWriteDataSourceDecision.setDataSource(datasourceName);
        Object result =  pjp.proceed();
        ReadWriteDataSourceDecision.reset();
        return result;
    }

    public Set<Method> toStringMethodSet = new HashSet<>();

    /**
     * 初始化datasource名称
     * @param classTarget
     * @param objMethod
     */
    public void initDataSourceName(Class<?> classTarget,Method objMethod, Object[] args)throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        String datasourceName = null;
        //总莫名其妙的被调到tostring
        if(toStringMethodSet.contains(objMethod) || objMethod.toString().contains("toString()"))
        {
            toStringMethodSet.add(objMethod);
            return;
        }
        //如果控制器设置了哪个库就用哪个库
        if(ReadWriteDataSourceDecision.isChoiceParam())
        {
            datasourceName = ReadWriteDataSourceDecision.getDataSource();
            ReadWriteDataSourceDecision.reset();
            ReadWriteDataSourceDecision.markOther();
            ReadWriteDataSourceDecision.setDataSource(datasourceName);
            return;
        }
        datasourceName = getDataSourceName(classTarget,objMethod,args);
        ReadWriteDataSourceDecision.markOther();
        ReadWriteDataSourceDecision.setDataSource(datasourceName);
    }

    /** key是method对象，value是datasourcename. */
    private Map<String,String> methodDatasourceNameMap = new HashMap<>();

    /**
     * 获取datasource name
     * @param classTarget service的类
     * @param objMethod 方法对象
     * @param args 参数列表
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private String getDataSourceName(Class<?> classTarget,Method objMethod, Object[] args)throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        /*
          1 先去缓存中去获取datasource name,如果有就直接返回
          2 缓存中没有，就去看下 service的类有没有DataSource注解有就获取下，理论上一定会有，没有就报错
            如果拿到了，判断下这个datasource 的分表分库读写分离配置，没配置就报错

         */

        DataSource dataSource = null;
        if(methodDatasourceNameMap.containsKey(classTarget + objMethod.toString()))
        {
            return methodDatasourceNameMap.get(classTarget + objMethod.toString());
        }
        dataSource = objMethod.getAnnotation(DataSource.class);
        //尝试去类里面拿值
        if(dataSource == null)
        {
            dataSource = classTarget.getAnnotation(DataSource.class);
        }
        if(dataSource == null)
        {
            LOG.debug(classTarget.toString() + "没有配置DataSource，将使用默认没有配置datasource");
            return null;
        }
        if(!datasourceSettMap.containsKey(dataSource.value()))
        {
            throw new BusinessException("service 注解的datasource " + dataSource.value() + "没有配置 读写配置:"  + dataSource.value());
        }
        String resultDataSourceName = null;
        /**
         *
         *第一种，不做读写分离，也不做分表分库  直接返回注解内容
         *第二种，做读写分离，但是不做分表分库  判断是否是读的方法，如果是拼接_salve 不是拼接_master
         *第三种，做读写分离，做分表，不做分库  处理同上
         *第四种，做读写分离，做分表，分库  看流程图
         */
        switch (datasourceSettMap.get(dataSource.value()))
        {
            case DATASOURCE_TYPE_DEFAULT:
                resultDataSourceName =  dataSource.value();
                break;
            case DATASOURCE_TYPE_MS:
            case DATASOURCE_TYPE_MS_CAT_TABLE:
                resultDataSourceName = super.isChoiceReadDB(objMethod.getName()) ? dataSource.value() + "_salve" : dataSource.value() + "_master";
                break;
            case DATASOURCE_TYPE_MS_CAT_TABLE_DB:
                DataSourceNameChoose dataSourceNameChoose = dataSourceChooseMap.get(dataSource.value());
                if(dataSourceNameChoose == null)
                {
                    throw new BusinessException(dataSource.value() + "--没有配置DataSourceNameChoose");
                }
                resultDataSourceName = getDataSourceNameCatDB(classTarget,objMethod.getParameterTypes(),args,objMethod,dataSourceNameChoose );
                break;
            default:
                throw new BusinessException(dataSource.value() + " 对应的datasourceSettMap 只支持 1,2,3,4 四种 您给的是:" +
                        datasourceSettMap.get(dataSource.value()));
        }
        methodDatasourceNameMap.put(classTarget + objMethod.toString(), resultDataSourceName);
        return resultDataSourceName;
    }



    /**
     * 根据切入点获取datasourcename 本方法流程图请参阅：
     * http://139.199.189.154:8090/pages/viewpage.action?pageId=4948128
     *
     * @param pjp 切入点
     * @return datasourcename
     * @throws NoSuchMethodException the no such method exception
     * @throws SecurityException the security exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException the illegal access exception
     */
    private String getDataSourceName(ProceedingJoinPoint pjp) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        // 首先获取方法名字，然后获取方法的参数，接着获取方法的对象，这届获取方法本身的method对象。
        String methodName = pjp.getSignature().getName();
        Class<?> classTarget = pjp.getTarget().getClass();
        Class<?>[] par = ((MethodSignature)pjp.getSignature()).getParameterTypes();
        if(classTarget.toString().contains("CGLIB"))
        {
            classTarget = classTarget.getSuperclass();
        }
        Method objMethod = classTarget.getMethod(methodName, par);
        return getDataSourceName(classTarget, objMethod,pjp.getArgs());
    }

    /**
     *
     * 分库的时候获取datasource的名字
     * @param classTarget   classTarget
     * @param paramClasses 参数类型
     * @param args 参数
     * @param objMethod 调用方法
     * @param dataSourceNameChoose  dataSourceNameChoose
     * @return  datasource名称
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private String getDataSourceNameCatDB(Class<?> classTarget, Class<?>[] paramClasses,Object[] args,
                                          Method objMethod, DataSourceNameChoose dataSourceNameChoose)
        throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        /*
          1  分库的时候需要获取分库标记(catDBFlag)，分库标记 字段 用@CatTableFlag 标记
             @CatTableFlag 可能被标记到一个 bean的某个字段上比如 user有一个 sex字段，男的一个库，女的一个库，那么我们要获取到本次参数中到底是男还是女
             这个男女就是catDBFlag的val   selectUser(User user)
             @CatTableFlag 可能被标记到一个参数上，比如 selectUserBySex(@CatTableFlag int sex);这个时候catDBFlag 就是第一个参数
             @CatTableFlag  可能被标记到一个map中，比如selectUser(@CatTableFlag("sex") Map paramMap);
                             这代表实际参数中map有一个key是sex 他的值就是catDBFlag的值
           2 根据方法的类型(读，还是写) 来调用dataSourceNameChoose getReadDataSourceName 或者getWriteDataSourceName 来获取实际的datasource name返回

         */
        String catDBFlag = null;
        String dataSourceName = null;
        // 如果已经有了此method的标记则表示可以从缓存里面取了
        if (isBeanCattableFlagMap.containsKey(objMethod))
        {
            // 如果目标是个bean 则需要找到这个bean 然后通过反射获取到分表标志
            if (isBeanCattableFlagMap.get(objMethod))
            {
                catDBFlag = getCacheBeanCatTableFlag(args, objMethod);
            }
            else // 如果目标是个map或者 其他的 则进此分支
            {
                catDBFlag = getCacheParamCatTableFlag(args, objMethod);
            }
        }
        else // 如果缓存中没有则需要重新开始计算
        {
            catDBFlag = getParamCatTableFlag(paramClasses, args, objMethod);
            if (catDBFlag == null)
            {
                try
                {
                    catDBFlag = getBeanParamCatTableFlag(args, objMethod);
                }
                catch (Exception e)
                {
                    LOG.error(this, e);
                }

            }
        }
        //如果是读 就去找读的datasourcename
        if (super.isChoiceReadDB(objMethod.getName()))
        {
            dataSourceName = dataSourceNameChoose.getReadDataSourceName(catDBFlag);
        }
        else
        {
            dataSourceName = dataSourceNameChoose.getWriteDataSourceName(catDBFlag);
        }
        return dataSourceName;
    }

    /**
     * 如果一个参数是一个dto 并且使用了@cattableflag 则返回标记字段的数据.
     *
     * @param args 参数
     * @param objMethod method对象
     * @return 分库的标识符
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException the illegal access exception
     */
    private String getCacheBeanCatTableFlag(Object[] args, Method objMethod)
        throws IllegalArgumentException, IllegalAccessException
    {
        return fieldBeanMap.get(objMethod).get(args[methodBeanIndexMap.get(objMethod)]).toString();
    }

    /**
     * 如果一个参数使用了@cattableflag 标记了 那么这个参数可以是一个字符串也可以是一个int或者一个map
     * 如果是int或者字符串，则直接取她们的值，如果是个map那么取map.get(cattableflag.value())
     *
     * @param args 参数
     * @param objMethod method对象
     * @return 分库的标识符
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException the illegal access exception
     */
    private String getCacheParamCatTableFlag(Object[] args, Method objMethod)
        throws IllegalArgumentException, IllegalAccessException
    {
        if (methodCatDBFlagParamIsMap.get(objMethod))
        {
            return ((Map<?, ?>)args[methodCatDBFlagParamIndexMap.get(objMethod)])
                .get(methodCatDBFlagParamMapKeyMap.get(objMethod))
                .toString();
        }
        return args[methodCatDBFlagParamIndexMap.get(objMethod)].toString();
    }

    /**
     * 如果一个参数是一个dto 并且使用了@cattableflag 则返回标记字段的数据.
     *
     * @param args 参数
     * @param objMethod method对象
     * @return 分库的标识符
     * @throws NoSuchFieldException the no such field exception
     * @throws SecurityException the security exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException the illegal access exception
     */
    private String getBeanParamCatTableFlag(Object[] args, Method objMethod)
        throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        String catDBFlag = null;
        Class<?> tempClass = null;
        Annotation tempAnnotation = null;
        for (int i = 0; i < args.length; i++)
        {
            tempClass = args[i].getClass();
            // 如果此参数的class中有分表标记则进去判断字段获取catDBFlag
            if ((tempAnnotation = tempClass.getAnnotation(CatTableFlag.class)) != null)
            {
                CatTableFlag catTableFlag = (CatTableFlag)tempAnnotation;
                String fieldName = catTableFlag.value();
                Field field = ReflectUtils.getDeclaredField(tempClass, fieldName);
                field.setAccessible(true);
                catDBFlag = field.get(args[i]).toString();
                isBeanCattableFlagMap.put(objMethod, true);
                methodBeanIndexMap.put(objMethod, i);
                fieldBeanMap.put(objMethod, field);
            }
        }
        return catDBFlag;
    }

    /**
     * 如果一个参数使用了@cattableflag 标记了 那么这个参数可以是一个字符串也可以是一个int或者一个map
     * 如果是int或者字符串，则直接取她们的值，如果是个map那么取map.get(cattableflag.value())
     *
     * @param paramClasses 参数class类型
     * @param args 参数
     * @param objMethod method对象
     * @return 分库的标识符
     */
    private String getParamCatTableFlag(Class<?>[] paramClasses, Object[] args, Method objMethod)
    {
        String catDBFlag = null;
        // 获取所有的参数的注解
        Annotation[][] parameterAnnotations = objMethod.getParameterAnnotations();
        if (parameterAnnotations != null && parameterAnnotations.length != 0)
        {
            int index = 0;
            for (Annotation[] parameterAnnotation : parameterAnnotations)
            {
                for (Annotation annotation : parameterAnnotation)
                {
                    // 如果判断有CatTableFlag 注解 则需要判断当前是否是个map，如果是map那么调用map.get方法获取分库标记，如果是普通的数据类型那么本身就是分库标记
                    if (annotation instanceof CatTableFlag)
                    {
                        methodCatDBFlagParamIndexMap.put(objMethod, index);
                        isBeanCattableFlagMap.put(objMethod, false);
                        if (args[index] instanceof Map)
                        {
                            methodCatDBFlagParamIsMap.put(objMethod, true);
                            CatTableFlag catTableFlag = (CatTableFlag)annotation;
                            catDBFlag = ((Map<?, ?>)args[index]).get(catTableFlag.value()).toString();
                            methodCatDBFlagParamMapKeyMap.put(objMethod, catTableFlag.value());
                        }
                        else
                        {
                            catDBFlag = args[index].toString();
                            methodCatDBFlagParamIsMap.put(objMethod, false);
                            break;
                        }
                    }
                }
                index++;
            }
        }
        return catDBFlag;
    }

    /**
     * 获取 第一种，不做读写分离，也不做分表分库 第二种，做读写分离，但是不做分表分库 第三种，做读写分离，做分表，不做分库 第四种，做读写分离，做分表，分库.
     *
     * @return bean的 第一种，不做读写分离，也不做分表分库 第二种，做读写分离，但是不做分表分库 第三种，做读写分离，做分表，不做分库 第四种，做读写分离，做分表，分库
     */
    public Map<String, Integer> getDatasourceSettMap()
    {
        return datasourceSettMap;
    }

    /**
     * 设置 第一种，不做读写分离，也不做分表分库 第二种，做读写分离，但是不做分表分库 第三种，做读写分离，做分表，不做分库 第四种，做读写分离，做分表，分库.
     *
     * @param datasourceSettMap 一个新的 第一种，不做读写分离，也不做分表分库 第二种，做读写分离，但是不做分表分库 第三种，做读写分离，做分表，不做分库 第四种，做读写分离，做分表，分库
     */
    public void setDatasourceSettMap(Map<String, Integer> datasourceSettMap)
    {
        this.datasourceSettMap = datasourceSettMap;
    }

    /**
     * 获取 key zj的名字 val是choose的对象.
     *
     * @return bean的 key zj的名字 val是choose的对象
     */
    public Map<String, DataSourceNameChoose> getDataSourceChooseMap()
    {
        return dataSourceChooseMap;
    }

    /**
     * 设置 key zj的名字 val是choose的对象.
     *
     * @param dataSourceChooseMap 一个新的 key zj的名字 val是choose的对象
     */
    public void setDataSourceChooseMap(Map<String, DataSourceNameChoose> dataSourceChooseMap)
    {
        this.dataSourceChooseMap = dataSourceChooseMap;
    }


}
