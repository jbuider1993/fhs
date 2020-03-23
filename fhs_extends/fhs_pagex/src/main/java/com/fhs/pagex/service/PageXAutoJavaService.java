package com.fhs.pagex.service;

import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.logger.Logger;
import com.fhs.module.base.config.BeetlConf;
import com.fhs.pagex.common.BeetlUtil;
import com.fhs.pagex.dto.PagexListSettDTO;
import com.fhs.pagex.loader.MemoryClassLoader;
import com.mybatis.jpa.common.ColumnNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 根据js代码自动生成javabean
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.service
 * @ClassName: PageXAutoJavaService
 * @Author: JackWang
 * @CreateDate: 2018/12/10 0010 9:02
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/10 0010 9:02
 * @Version: 1.0
 */
@Service
public class PageXAutoJavaService {

    private static final Logger LOG  = Logger.getLogger(PageXAutoJavaService.class);

    @Autowired
    private PageXDBService pageXDBService;

    public static final Set<String> DEFAULT_FIELD_SET = new HashSet<>();

    static
    {
        DEFAULT_FIELD_SET.add("create_user");
        DEFAULT_FIELD_SET.add("update_user");
        DEFAULT_FIELD_SET.add("create_time");
        DEFAULT_FIELD_SET.add("update_time");
    }

    /**
     * 翻译简写
     */
    private static Map<String,String> transAbbreviationMap = new HashMap<>();

    static
    {
        transAbbreviationMap.put("book","wordbook");
        transAbbreviationMap.put("user","sysUser");
        transAbbreviationMap.put("type","classifyInfo");
        transAbbreviationMap.put("fuser","basics:frontUser");
    }


    private BeetlConf beetlConf;


    /**
     *自动生成Java文件并编译为class
     * @param js js内容
     */
    public void autoJava(String js) throws Exception {
        if(beetlConf == null){
            beetlConf = SpringContextUtil.getBeanByName(BeetlConf.class);
        }
        PagexListSettDTO pagexListSettDTO = new PagexListSettDTO(js);
        String namespace =  ConverterUtils.toString(pagexListSettDTO.getModelConfig().get("namespace"));
        if(ConverterUtils.toBoolean(pagexListSettDTO.getModelConfig().get("trans")))
        {
            pageXDBService.getNeedTransNamespaceSet().add(namespace);
        }
        String javaClassName = StringUtil.firstCharUpperCase(ColumnNameUtil.underlineToCamel(namespace));
        //po对象
        Map<String,Object> poMap = new HashMap<>();
        poMap.put("className", javaClassName);
        StringBuilder transTypes = new StringBuilder("\"wordbook\",\"sysUser\",\"classifyInfo\",\"basics:frontUser\"");
        List<Map<String,Object>> javaFieldList = new ArrayList<>();
        Map<String,Object> tempField = null;
        Map<String,String> tempTransMap = null;
        Set<String> filedNameSet = new HashSet<>();
        filedNameSet.addAll(DEFAULT_FIELD_SET);
        filedNameSet.add(ConverterUtils.toString(pagexListSettDTO.getModelConfig().get("pkey")));
        //便利列表页面所有的行
        for(Map<String,Object> row : pagexListSettDTO.getListSett())
        {
            tempField =  new HashMap<>();

            if(filedNameSet.contains(row.get("name")))
            {
                continue;
            }
            filedNameSet.add(ConverterUtils.toString(row.get("name")));
            //此列需要翻译
            if(row.containsKey("trans")&& (!CheckUtils.isNullOrEmpty(row.get("trans"))) )
            {
                String trans = ConverterUtils.toString(row.get("trans"));
                //如果已经默认集成了就不集成了，如果默认没集成，就给 trans 拼接
                if(!transAbbreviationMap.containsKey(row.get("trans")))
                {
                    transTypes.append(",\"" + row.get("trans") + "\"");
                }
                else
                {
                    trans = transAbbreviationMap.get(trans);
                }
                tempTransMap = new HashMap<>();
                tempTransMap.put("type",trans);
                tempTransMap.put("key", ConverterUtils.toString(row.get("key")));
                tempField.put("trans",tempTransMap);
            }
            tempField.put("camelName",row.get("camelName"));
            javaFieldList.add(tempField);
        }
        poMap.put("transTypes",transTypes);
        poMap.put("fieldList",javaFieldList);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("po",poMap);
        paramMap.put("modelConfig",pagexListSettDTO.getModelConfig());
        String javaCode = BeetlUtil.renderBeelt("/pagex/auto_code/java_code_tag.html",paramMap);

        MemoryClassLoader loader = MemoryClassLoader.getInstrance();
        //如果已经存在则重新new一个类加载器，实现类刷新
        if( pageXDBService.getNamespaceClassMap().containsKey(namespace))
        {
            loader = new MemoryClassLoader();
        }
        try
        {
            loader.registerJava(javaClassName,javaCode);
            Class poClass = loader.getClass(javaClassName);
            pageXDBService.getNamespaceClassMap().put(namespace,poClass);
            LOG.info("代码生成注入到classloader成功：" + poClass);
        }
        catch (Exception e)
        {
            LOG.info("自动生成java代码注册错误：" + javaCode);
            LOG.error("注册java代码错误",e);
        }
    }


}
