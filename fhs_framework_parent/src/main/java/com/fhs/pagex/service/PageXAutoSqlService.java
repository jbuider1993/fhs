package com.fhs.pagex.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.exception.ParamException;
import com.fhs.pagex.bean.DefaultPageXBean;
import com.fhs.pagex.dao.DefaultPageXDAO;
import com.fhs.pagex.dto.PagexAddDTO;
import com.fhs.pagex.dto.PagexListSettDTO;
import com.mybatis.jpa.cache.JpaTools;
import com.mybatis.jpa.common.ColumnNameUtil;
import com.mybatis.jpa.meta.PersistentMeta;
import com.mybatis.jpa.statement.MybatisStatementAdapter;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 自动生成sql服务
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.service
 * @ClassName: PageXAutoSqlService
 * @Author: JackWang
 * @CreateDate: 2018/12/11 0011 21:14
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/11 0011 21:14
 * @Version: 1.0
 */
@Service
public class PageXAutoSqlService {
    /**
     *
     */
    public void autoSql(String js) throws NoSuchMethodException, ScriptException {
        PagexAddDTO pagexAddDTO = new PagexAddDTO(js);
        String namespace = ConverterUtils.toString(pagexAddDTO.getModelConfig().get("namespace"));
        String insertSql = autoInsert(pagexAddDTO);
        parseSql("insertPageX", namespace, "insert", insertSql);
        String deleteSql = autoDel(pagexAddDTO);
        parseSql("delPageX", namespace, "delete", deleteSql);
        String updateSql = autoUpdateSql(pagexAddDTO);
        parseSql("updatePageX", namespace, "update", updateSql);
        String findSql = autoFind(pagexAddDTO);
        parseSql("findBeanPageX", namespace, "select", findSql);
        PagexListSettDTO pagexListSettDTO = new PagexListSettDTO(js);
        String findPageSql = autoFindPage(pagexListSettDTO);
        parseSql("findPageX", namespace, "select", findPageSql);
        String findPageCountSql = autoFindPageCount(pagexListSettDTO);
        parseSql("findPageCountPageX", namespace, "select", findPageCountSql);
    }


    /**
     * 已经存在的sqlid
     */
    private Set<String> existSqlIdSet = new HashSet<>();


    private Map<String, MappedStatement> mappedStatements;

    /**
     * 将sql 添加到mybatis中
     *
     * @param sqlName   sql的名字
     * @param nameSpace namespace
     * @param type      类型
     * @param scriptSql sql
     */
    private synchronized void parseSql(String sqlName, String nameSpace, String type, String scriptSql) {
        if (mappedStatements == null) {
            Configuration configuration = SpringContextUtil.getBeanByName(SqlSession.class).getConfiguration();
            try {
                Field mappedStatementsField = Configuration.class.getDeclaredField("mappedStatements");
                mappedStatementsField.setAccessible(true);
                mappedStatements = (Map<String, MappedStatement>) mappedStatementsField.get(configuration);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String sqlId = nameSpace + "_" + sqlName;
        //吧之前的缓存清除掉
        mappedStatements.remove(DefaultPageXDAO.class.getName() + "." + sqlId);
        /*if(existSqlIdSet.contains(sqlId))
        {
            throw new ParamException("sql已经存在:" + nameSpace + "." + type);
        }*/
        MybatisStatementAdapter adapter = JpaTools.statementAdapterMap.get(DefaultPageXBean.class.getName());
        // 方法名
        adapter.setMethodName(sqlId);
        existSqlIdSet.add(sqlId);
        adapter.setParameterTypeClass(Object.class);
        PersistentMeta persistentMeta = JpaTools.persistentMetaMap.get(DefaultPageXDAO.class.getName());

        SqlCommandType sqlCommandType = null;
        String fromPreSql = null;
        switch (type) {
            case "select":
                sqlCommandType = SqlCommandType.SELECT;
                // adapter.setResultMapId(ResultMapConstants.DEFAULT_NAMESPACE + "." + persistentMeta.getEntityName());
                // 返回值类型
                adapter.setResultType(Map.class);
                break;
            case "delete":
                sqlCommandType = SqlCommandType.DELETE;
                adapter.setResultType(Integer.class);
                break;
            case "update":
                sqlCommandType = SqlCommandType.UPDATE;
                adapter.setResultType(Integer.class);
                break;
            case "insert":
                sqlCommandType = SqlCommandType.INSERT;
                adapter.setResultType(Integer.class);
                break;
            default:
                throw new ParamException("不支持此类型");
        }
        // sqlScript
        adapter.setSqlScript(scriptSql);
        adapter.setSqlCommandType(sqlCommandType);
        adapter.setResultMapId(null);
        // 主键策略
        adapter.setKeyGenerator(new NoKeyGenerator());
        adapter.setKeyProperty(null);
        adapter.setKeyColumn(null);
        try {
            //如果一个sql已经存在是正常现象
            adapter.parseStatement();
        } catch (IllegalArgumentException e) {

        }


    }

    /**
     * 自动生成insert sql
     *
     * @param pagexAddDTO pagexAddDTO
     * @return insert 的sql
     */
    public String autoInsert(PagexAddDTO pagexAddDTO) {
        Map<String, Object> modelConfig = pagexAddDTO.getModelConfig();
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO " + modelConfig.get("table") + " (`");
        sqlBuilder.append(modelConfig.get("pkey") + "`");
        List<Map<String, Object>> fields = pagexAddDTO.getFormFieldSett();
        Set<String> fieldNameSet = new HashSet<>();
        StringBuilder valueFieldBuilder = new StringBuilder("");
        for (Map<String, Object> field : fields) {
            if (CheckUtils.isNotEmpty(field.get("name"))) {
                String[] names = ConverterUtils.toString(field.get("name")).split(",");
                for (String name : names) {
                    fieldNameSet.add(name);
                    sqlBuilder.append(",`" + name + "`");

                    valueFieldBuilder.append(",#{" + ColumnNameUtil.underlineToCamel(name) + "}");
                }

            }
        }
        String groupCodeSql = "";
        String groupCodeValSql = "";
        if (ConverterUtils.toBoolean(pagexAddDTO.getModelConfig().get("isMultiTenant"))) {
            groupCodeSql = ",group_code";
            groupCodeValSql = ",#{groupCode}";
        }
        sqlBuilder.append(",`create_time`,`create_user`,`update_time`,`update_user`  " + groupCodeSql + ")  VALUES ("
                + ("uuid".equals(modelConfig.get("type")) ? "REPLACE(UUID(), '-', '')" : "null"));
        sqlBuilder.append(valueFieldBuilder);
        if (sqlBuilder.length() != 0) {
            sqlBuilder.append(",");
        }
        sqlBuilder.append("DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:%S'),#{createUser},DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:%S'),#{updateUser}" + groupCodeValSql + ")");
        return sqlBuilder.toString();
    }

    /**
     * 自动生成删除的sql
     *
     * @param pagexAddDTO pagexAddDTO
     * @return 根据id删除数的sql
     */
    public String autoDel(PagexAddDTO pagexAddDTO) {
        Map<String, Object> modelConfig = pagexAddDTO.getModelConfig();
        StringBuilder sqlBuilder = new StringBuilder(" DELETE FROM  " + modelConfig.get("table") + " WHERE ");
        sqlBuilder.append(modelConfig.get("pkey") + " = #{id}");
        return sqlBuilder.toString();
    }

    /**
     * 自动生成查询单个的sql
     *
     * @param pagexAddDTO pagexAddDTO
     * @return 根据id查询单个sql
     */
    public String autoFind(PagexAddDTO pagexAddDTO) {
        Map<String, Object> modelConfig = pagexAddDTO.getModelConfig();
        StringBuilder sqlBuilder = new StringBuilder(" SELECT " + modelConfig.get("pkey") + " AS " + modelConfig.get("pkeyCamel"));
        List<Map<String, Object>> fields = pagexAddDTO.getFormFieldSett();
        for (Map<String, Object> field : fields) {
            if (CheckUtils.isNotEmpty(field.get("name"))) {
                String[] names = ConverterUtils.toString(field.get("name")).split(",");
                for (String name : names) {
                    sqlBuilder.append("," + name + " AS " + ColumnNameUtil.underlineToCamel(name));
                }
            }
        }
        sqlBuilder.append(",create_time createTime,create_user createUser,update_time updateTime,update_user updateUser FROM " + modelConfig.get("table") + " WHERE " + modelConfig.get("pkey") + "=#{id}");
        return sqlBuilder.toString();
    }

    /**
     * 自动生成查询列表的sql
     *
     * @param pagexListSettDTO pagexListSettDTO
     * @return 生成根据条件查询列表sql
     */
    public String autoFindPage(PagexListSettDTO pagexListSettDTO) {
        Map<String, Object> modelConfig = pagexListSettDTO.getModelConfig();
        StringBuilder sqlBuilder = new StringBuilder("<script> SELECT create_time createTime,create_user createUser,update_time updateTime," +
                "update_user updateUser," + modelConfig.get("pkey") + " AS " + modelConfig.get("pkeyCamel"));
        List<Map<String, Object>> fields = pagexListSettDTO.getListSett();
        String name = null;
        for (Map<String, Object> field : fields) {
            if (PageXAutoJavaService.DEFAULT_FIELD_SET.contains(field.get("name"))) {
                continue;
            }
            name = ConverterUtils.toString(field.get("name"));
            sqlBuilder.append("," + name + " AS " + ColumnNameUtil.underlineToCamel(name));
        }
        sqlBuilder.append(" FROM " + modelConfig.get("table") + autoPagerWhere(pagexListSettDTO));
        String orderBy = " <if test=\"sortTzwName != null and sortTzwName !='' \" >  " +
                "        ORDER BY  ${sortTzwName}  ${order}" +
                "      </if> ";
        String defaultOrderBy = modelConfig.containsKey("orderBy") ?
                " <if test=\"sortTzwName == null or sortTzwName =='' \" >  " +
                        " ORDER BY " + ConverterUtils.toString(modelConfig.get("orderBy")) +
                        "      </if> " : "";
        // 处理order by
        sqlBuilder.append(orderBy);
        sqlBuilder.append(defaultOrderBy);
        String limit = " <if test=\"start != -1 and end !=-1 \" >  " +
                "        LIMIT  ${start},${end} " +
                "      </if> </script>";
        sqlBuilder.append(limit);
        return sqlBuilder.toString();
    }

    /**
     * 自动生成查询列表总数的sql
     *
     * @param pagexListSettDTO pagexListSettDTO
     * @return 查询列表总数的sql
     */
    public String autoFindPageCount(PagexListSettDTO pagexListSettDTO) {
        Map<String, Object> modelConfig = pagexListSettDTO.getModelConfig();
        StringBuilder sqlBuilder = new StringBuilder("<script> SELECT COUNT(1) as countNum");
        sqlBuilder.append(" FROM " + modelConfig.get("table") + autoPagerWhere(pagexListSettDTO) + "</script>");
        return sqlBuilder.toString();
    }

    /**
     * 自动生成列表和查总数sql的过滤条件sql
     *
     * @param pagexListSettDTO pagexListSettDTO
     * @return 生成列表和查总数sql的过滤条件sql
     */
    private String autoPagerWhere(PagexListSettDTO pagexListSettDTO) {
        StringBuilder sqlBuilder = new StringBuilder("<where>");
        List<Map<String, Object>> fields = pagexListSettDTO.getFilters();
        String fieldName = null;
        String operator = null;
        String camelName = null;
        Set<String> hasWhereFields = new HashSet<>();
        for (Map<String, Object> field : fields) {
            fieldName = ConverterUtils.toString(field.get("name"));
            camelName = ColumnNameUtil.underlineToCamel(fieldName);
            hasWhereFields.add(fieldName);
            //代表是between
            if (ConverterUtils.toBoolean(field.get("isBT"))) {
                //数据要小于等于max
                sqlBuilder.append(" <if test=\"");
                sqlBuilder.append(camelName + "Max  !='' and   ");
                sqlBuilder.append(camelName + "Max  !=null \"> ");
                sqlBuilder.append(" AND " + fieldName + "  <![CDATA[<=]]> #{" + camelName + "Max}");
                sqlBuilder.append("</if>");
                //数据要大于等于min
                sqlBuilder.append(" <if test=\"");
                sqlBuilder.append(camelName + "Min  !='' and   ");
                sqlBuilder.append(camelName + "Min  !=null \"> ");
                sqlBuilder.append(" AND " + fieldName + "  <![CDATA[>=]]> #{" + camelName + "Min}");
                sqlBuilder.append("</if>");
            }

            sqlBuilder.append(" <if test=\"");
            sqlBuilder.append(camelName + "  !='' and   ");
            sqlBuilder.append(camelName + " !=null \"> ");
            if (!field.containsKey("filterType")) {
                operator = "=";
            } else if (ConverterUtils.toString(field.get("filterType")).contains("like")) {
                sqlBuilder.append(" AND " + fieldName + " LIKE concat('%',#{" + camelName + "},'%')");
                sqlBuilder.append("</if>");
                continue;
            } else {
                operator = ConverterUtils.toString(field.get("filterType"));
            }
            sqlBuilder.append(" AND " + fieldName + "<![CDATA[" + operator + "]]> #{" + camelName + "}");
            sqlBuilder.append("</if>");
        }
        //如果需要做数据权限校验
        if (pagexListSettDTO.getModelConfig().containsKey("dp")) {
            JSONObject dpSett = JSON.parseObject(ConverterUtils.toString(pagexListSettDTO.getModelConfig().get("dp")));
            dpSett.keySet().forEach(key -> {
                //获取这个字段要根据哪个数据权限来做过滤
                String dpGroup = dpSett.getString(key);
                sqlBuilder.append(" <if test=\"dataPermissin !=null \">");
                sqlBuilder.append(" <if test=\"");
                sqlBuilder.append(" dataPermissin." + dpGroup + "  !='' and   ");
                sqlBuilder.append("dataPermissin." + dpGroup + " !=null \"> ");
                sqlBuilder.append(" AND  " + key + " IN (${dataPermissin." + dpGroup + "}) ");
                sqlBuilder.append("</if>");
                sqlBuilder.append("</if>");
            });
        }
        //处理扩展参数
        if (pagexListSettDTO.getModelConfig().containsKey("extendsParam")) {
            String[] extendsFilters = StringUtil.toString(pagexListSettDTO.getModelConfig().get("extendsParam")).split("&");
            for (String extendsFilter : extendsFilters) {
                String clomun = extendsFilter.split("=")[0];
                hasWhereFields.add(clomun);
                sqlBuilder.append(" <if test=\"");
                sqlBuilder.append(clomun + "  !='' and   ");
                sqlBuilder.append(clomun + " !=null \"> ");
                sqlBuilder.append(" AND " + clomun + "= #{" + clomun + "}");
                sqlBuilder.append("</if>");
            }
        }
        PagexAddDTO addDTO = PagexDataService.SIGNEL.getPagexAddDTOFromCache(pagexListSettDTO.getModelConfig().get("namespace").toString());
        List<Map<String, Object>> formFieldSett = addDTO.getFormFieldSett();

        for (Map<String, Object> filed : formFieldSett) {
            fieldName = ConverterUtils.toString(filed.get("name"));
            camelName = ConverterUtils.toString(filed.get("camelName"));
            if (hasWhereFields.contains(fieldName) || CheckUtils.isNullOrEmpty(fieldName) || CheckUtils.isNullOrEmpty(camelName)) {
                continue;
            }
            if ("group_code".equals(fieldName)) {
                continue;
            }
            sqlBuilder.append(" <if test=\"");
            sqlBuilder.append(camelName + "  !='' and   ");
            sqlBuilder.append(camelName + " !=null \"> ");
            sqlBuilder.append(" AND " + fieldName + "<![CDATA[=]]> #{" + camelName + "}");
            sqlBuilder.append("</if>");
        }
        //是否为多租户模式
        if (ConverterUtils.toBoolean(addDTO.getModelConfig().get("isMultiTenant"))) {
            sqlBuilder.append(" AND group_code <![CDATA[=]]> #{groupCode}");
        }
        sqlBuilder.append("</where>");
        return sqlBuilder.toString();
    }


    /**
     * 拼接更新sql
     *
     * @param pagexAddDTO pagexAddDTO
     * @return 更新sql
     */
    public String autoUpdateSql(PagexAddDTO pagexAddDTO) {
        Map<String, Object> modelConfig = pagexAddDTO.getModelConfig();
        // columns
        StringBuilder sets = new StringBuilder();
        List<Map<String, Object>> fields = pagexAddDTO.getFormFieldSett();
        sets.append("<trim prefix='' suffix='' suffixOverrides=',' > ");
        String camelName = null;
        for (Map<String, Object> field : fields) {
            if (CheckUtils.isNotEmpty(field.get("name"))) {
                if (CheckUtils.isNotEmpty(field.get("name"))) {
                    String[] names = ConverterUtils.toString(field.get("name")).split(",");
                    for (String fieldName : names) {
                        camelName = ColumnNameUtil.underlineToCamel(fieldName);
                        sets.append("<if test='" + camelName + "!= null'> ");
                        // columnName = #{ }
                        sets.append(fieldName).append(" = ").append("#{" + camelName + "}")
                                .append(" , ");
                        sets.append("</if> ");
                    }
                }
            }
        }
        sets.append("</trim> ");
        return "<script> " + "UPDATE " + modelConfig.get("table") + " SET " + sets.toString()
                + ",update_time=DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:%S'),update_user=#{updateUser}" +
                "  WHERE " + modelConfig.get("pkey") + " = #{id} </script>";
    }


}
