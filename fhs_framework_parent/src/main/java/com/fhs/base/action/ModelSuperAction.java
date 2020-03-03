package com.fhs.base.action;

import com.fhs.common.ExcelExportTools;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.base.action.BaseAction;
import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.base.service.BaseService;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.NotPremissionException;
import com.fhs.core.exception.ParamException;
import com.fhs.core.exception.YZBNotLogException;
import com.fhs.core.group.Add;
import com.fhs.core.group.Update;
import com.fhs.core.log.LogDesc;
import com.fhs.core.page.Pager;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.vo.SysUserVo;
import com.mybatis.jpa.context.DataPermissonContext;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;


/**
 * 基础的model super action
 *
 * @Filename: ModelSuperAction.java
 * @Version: 1.0
 * @Author: jianbo.qin
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 *
 */
public class ModelSuperAction<T> extends BaseAction<T>
{
    private static final Logger LOG = Logger.getLogger(ModelSuperAction.class);

    @Autowired
    private BaseService<T> baseService;

    public BaseService<T> getBaseService()
    {
        return baseService;
    }

    /**
     * 查询bean列表数据
     *
     * @param request response
     * @throws Exception
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Pager<T> findPage(T e, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        if (isPermitted(request, "see"))
        {
            PageSizeInfo pgeSizeInfo = getPageSizeInfo(request);
            List<T> dataList = baseService.selectPageForOrder(e, pgeSizeInfo.getPageStart(),
                    pgeSizeInfo.getPageSize(),this.formartOrderBy(request));
            int count = baseService.findCountJpa(e);
            request.getSession().setAttribute(this.getClass() + "preLoadParam",e);
            return new Pager<T>(count,dataList);
        }
        else
        {
            throw new NotPremissionException();
        }
    }


    /**
     * 无分页查询bean列表数据
     *
     * @param request response
     * @throws Exception
     */
    @RequestMapping("findList")
    @ResponseBody
    public List<T> findList(T e, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        if (isPermitted(request, "see"))
        {
            List<T> dataList = baseService.findForList(e);
            return dataList;
        }
        else
        {
            throw new NotPremissionException();
        }
    }

    /**
     * 根据map条件查询返回page對象
     *
     * @param request
     * @param response
     */
    @RequestMapping("findPageByM")
    @LogDesc(value = " 根据map条件查询返回page對象", type = LogDesc.SEE)
    @ResponseBody
    public Pager<T> findPageByM(HttpServletRequest request, HttpServletResponse response)
    {
        if (isPermitted(request, "see"))
        {
            Map<String, Object> map = getPageTurnNum(request);
            map.put("orderBy",this.formartOrderBy(request));
            List<T> dataList = baseService.findForListFromMap(map);
            int count = baseService.findCountFromMap(map);
            request.getSession().setAttribute(this.getClass() + "preLoadParam",map);
            return new Pager<T>(count,dataList);
        }
        else
        {
            throw new NotPremissionException();
        }
    }

    /**
     *  公共导出excel 03  by jackwang
     * @param request  request
     * @param response response
     */
    @RequestMapping("pubExportExcel")
    @LogDesc(value = "导出数据", type = LogDesc.SEE)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        /*
           1 获取参数
           2  根据参数类型获取List<T>
           3 根据列配置将List<T> 转换为obejct[][]
           5  将obejct[][] 转换为 poi对象
           6  从poi获取二进制 下载
         */
        List<T> dataList = getExportData(request);
        exportExcel(dataList, request,  response);
    }

    /**
     * 有数据导出excel by jackwang
     * @param dataList  数据集合
     * @param request request
     * @param response response
     */
    protected  void exportExcel(List<T> dataList,HttpServletRequest request, HttpServletResponse response)
    {
        ExcelExportTools.exportExcel(dataList,request,response);
    }

    /**
     * 获取需要导出的数据
     * @param request request
     * @return 需要导出的数据
     */
    private List<T> getExportData(HttpServletRequest request){
        Object param = request.getSession().getAttribute(this.getClass() + "preLoadParam");
        if(param instanceof  Map)
        {
            return baseService.findForListFromMap((Map)param);
        }
        else
        {
            //如果session中拿不到参数，则自己new一个
            if(param == null)
            {
                //子类集成我的时候传的泛型是什么就new什么
                Type t = this.getClass().getGenericSuperclass();
                if (t instanceof ParameterizedType) {
                    Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                    if(p.length>0)
                    {
                        try {
                            param = Class.forName(p[0].getTypeName()).newInstance();
                        } catch (InstantiationException e) {
                            LOG.error(this,e);
                        } catch (IllegalAccessException e) {
                            LOG.error(this,e);
                        } catch (ClassNotFoundException e) {
                            LOG.error(this,e);
                        }
                    }
                }
            }
            if(param==null)
            {
                throw new ParamException("导出没有调用查询方法设置查询参数");
            }
            return baseService.findForList((T)param);
        }
    }

    /**
     * 格式化导出数据
     * @param request request
     * @param dataList 需要被格式化的数据
     * @return 格式化后的数据
     */
    private Object[][] parseExportData(HttpServletRequest request,List<T> dataList){
        final Map<String,String> fieldMap = (Map<String, String>) request.getSession().getAttribute("exportField");
        final Object[][] rows = new Object[dataList.size()][fieldMap.size()];
        Set<String> fieldSet = fieldMap.keySet();
        for(int i = 0;i<dataList.size();i++)
        {
            T rowData = dataList.get(i);
            Object[] row = new Object[fieldSet.size()];
            rows[i] = row;
            int fieldIndex = 0;
            for(String field:fieldSet)
            {
                Object value = null;
                if(field.contains("transMap"))
                {
                     value = ((SuperBean)rowData).getTransMap().get(field.replace("transMap.",""));
                }
                else
                {
                     value = ReflectUtils.getValue(rowData,field);
                }
                row[fieldIndex] = value;
                fieldIndex ++;
            }
        }
        return  rows;
    }
    /**
     * 获取excel的title
     * @param request request
     * @return title集合
     */
    private String[] getExportTitleArray(HttpServletRequest request){
        return ExcelExportTools.getExportTitleArray(request);
    }

    /**
     * 将导出的列配置信息缓存到session中
     * @param fieldSett 导出配置
     * @param request request
     * @return 成功
     */
    @RequestMapping("setExportField")
    @ResponseBody
    public HttpResult setExportField(@RequestBody String fieldSett,HttpServletRequest request){
        ExcelExportTools.setExportField(fieldSett,request);
        return HttpResult.success();
    }

    /**
     * 根据ID集合查询对象数据
     * @param id id
     * @param request request
     * @return
     * @throws Exception
     */
    @RequestMapping("info/{id}")
    @ResponseBody
    @LogDesc(value = "根据ID集合查询对象数据", type = LogDesc.SEE)
    public T info(@PathVariable("id") String id, HttpServletRequest request)
            throws Exception
    {
        if (isPermitted(request, "see"))
        {
            T bean = baseService.findBeanById(id);
            return bean;
        }
        else
        {
            throw new NotPremissionException();
        }
    }



    /**
     * 获取单条bean数据
     * @param request
     * @throws Exception
     */
    @RequestMapping("infoByM")
    @ResponseBody
    @LogDesc(value = "根据Map集合查询对象数据", type = LogDesc.SEE)
    public T infoByM(HttpServletRequest request)
            throws Exception
    {
        if (isPermitted(request, "see"))
        {
            EMap<String, Object> paramMap = this.getParameterMap(request);
            T bean = baseService.findBeanFromMap(paramMap);
            return bean;
        }
        else
        {
            throw new NotPremissionException();
        }
    }

    /**
     * groupcode字段
     */
    private Field groupCodeField ;

    /**
     * 添加
     *
     * @param e bean
     * @param check 检查结果
     */
    @RequestMapping("add")
    @ResponseBody
    @LogDesc(value = "添加", type = LogDesc.ADD)
    public HttpResult add(@Validated(Add.class) T e, BindingResult check, HttpServletRequest request,
                          HttpServletResponse response)
    {
        if (isPermitted(request, "add"))
        {
            if (!check.hasErrors())
            {
                if (e instanceof BaseDO)
                {
                    BaseDO<?> baseDo = (BaseDO<?>)e;
                    //如果是saas模式，并且bean中包含groupcode字段，则给其设置值
                    if(ConverterUtils.toBoolean(EConfig.getOtherConfigPropertiesValue("isSaasModel"))
                            && ReflectUtils.getDeclaredField(e.getClass(),"groupCode")!=null && ReflectUtils.getValue(e,"groupCode")==null)
                    {
                        ReflectUtils.setValue(e,"groupCode",getSessionuser(request).getGroupCode());
                    }
                    baseDo.preInsert(getSessionuser(request).getUserId());
                }
                baseService.insert(e);
                return HttpResult.success();
            }
            return HttpResult.error(null,JsonUtils.list2json(check.getAllErrors()));
        }
        throw new NotPremissionException();
    }


    /**
     *根据id删除对象
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("del")
    @ResponseBody
    @LogDesc(value = "删除", type = LogDesc.DEL)
    public HttpResult del(@RequestParam("id") String id, HttpServletRequest request)
    {
        if (isPermitted(request, "del"))
        {
            try
            {
                baseService.deleteById(id);
            }
            catch (Exception e1)
            {
                LOG.error("数据删除错误:" + id,e1);
                return HttpResult.error();
            }
            return HttpResult.success();
        }
        throw new NotPremissionException();
    }

    /**
     * 更新bean数据
     *
     * @param e bean
     * @param check 检查结果
     */
    @RequestMapping("update")
    @ResponseBody
    @LogDesc(value = "更新", type = LogDesc.UPDATE)
    public HttpResult update(@Validated(Update.class) T e, BindingResult check, HttpServletRequest request,
                             HttpServletResponse response)
    {
        if (isPermitted(request, "update"))
        {
            try
            {
                if (e instanceof BaseDO)
                {
                    BaseDO<?> baseDo = (BaseDO<?>)e;
                    baseDo.preUpdate(getSessionuser(request).getUserId());
                }
                baseService.updateSelectiveById(e);
            }
            catch (Exception e1)
            {
                LOG.error("数据更新错误:" + JsonUtils.bean2json(e),e1);
                return HttpResult.error();
            }
            return HttpResult.success();
        }
        throw new NotPremissionException();
    }

    /**
     * 判断登录人是否有权限
     *
     * @param request request
     * @param permitName 权限名称
     * @return true 有权限 false 没有权限
     */
    public boolean isPermitted(HttpServletRequest request, String permitName)
    {
        String path = request.getServletPath();
        String namespace = path.split("/")[2];
        boolean bool = SecurityUtils.getSubject().isPermitted(namespace + ":" + permitName);
        return bool;
    }

    /**
     * 获取session里面的user
     *
     * @param request 请求对象
     * @return session里面的user
     */
    protected SysUserVo getSessionuser(HttpServletRequest request) {
        return (SysUserVo)request.getSession().getAttribute(Constant.SESSION_USER);
    }

    /**
     * 无分页查询bean列表数据
     * @param e
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("findListData")
    public void findListData(T e, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if (isPermitted(request, "see"))
        {
            List<T> list = baseService.findForList(e);
            String jsonStr = JsonUtils.list2json(list);
            super.outWriteJson(jsonStr, response);
        }
    }



    private static final Map<String,String> BASE_FIELD_MAP = new HashMap<>();
    static{
        BASE_FIELD_MAP.put("transMap.createUserUserName","create_user");
        BASE_FIELD_MAP.put("transMap.updateUserUserName","update_user");
        BASE_FIELD_MAP.put("transMap.businessIdEntName","business_id");
        BASE_FIELD_MAP.put("transMap.verifyUserIdUserName","verify_user_id");
    }

    /**
     * 格式化order by xx asc desc
     * @param request request
     * @return order by 内容
     */
    protected   String formartOrderBy(HttpServletRequest request){
        String fieldName = request.getParameter("sortTzwName");
        String order = request.getParameter("order");
        if(CheckUtils.isNullOrEmpty(fieldName) || CheckUtils.isNullOrEmpty(order))
        {
            return "";
        }
        Map<String,String> fieldMap = getFormartField();
        // 如果子类设置了某个字段的db 字段名则取 子类设置的
        if(fieldMap!=null && fieldMap.containsKey(fieldName))
        {
            fieldName = fieldMap.get(fieldName);
        }
        else
        {
            // 如果子类
            if(BASE_FIELD_MAP.containsKey(fieldName))
            {
                fieldName = BASE_FIELD_MAP.get(fieldName);
            }else
            {
                // 如果带翻译的话，把后面的 name去掉
                if(fieldName.contains("transMap"))
                {
                    fieldName = fieldName.replace("transMap.","");
                    fieldName = fieldName.substring(0,fieldName.lastIndexOf("Name"));
                }
                for (char i='A';i<'Z';i++) {
                    fieldName = fieldName.replaceAll(i + "","_" + i);
                }
            }

        }
        return  fieldName.toLowerCase() + " " + order;
    }

    /**
     * 获取格式化字段的参数
     * @return key 前端字段，val db字段
     */
    public Map<String,String> getFormartField(){
        return null;
    }

    @Override
    public EMap<String, Object> getParameterMap(HttpServletRequest request) {
        EMap<String, Object> result = super.getParameterMap(request);
        result.put("permission", DataPermissonContext.getDataPermissonMap());
        result.put("loginUserId",getSessionuser(request).getUserId());
        return result;
    }
}
