package com.fhs.pagex.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.base.action.BaseAction;
import com.fhs.core.db.ReadWriteDataSourceDecision;
import com.fhs.pagex.dto.PagexBaseDTO;
import com.fhs.pagex.service.JoinService;
import com.fhs.pagex.service.ListExtendsHanleService;
import com.fhs.pagex.service.PageXDBService;
import com.fhs.pagex.service.PagexDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * pagex处理前段业务逻辑的公共代码
 */
public class PageXBaseAction extends BaseAction {

    @Autowired
    protected PageXDBService service;

    @Autowired
    protected JoinService joinService;

    @Autowired
    protected ListExtendsHanleService listExtendsHanleService;

    /**
     * 根据modelconfig中的db配置，来设置本操作连接的数据库
     * @param pagexBaseDTO  pagexBaseDTO
     */
    protected void setDB(PagexBaseDTO pagexBaseDTO){
        if(pagexBaseDTO!=null)
        {
            if(pagexBaseDTO.getModelConfig().containsKey("db"))
            {
                ReadWriteDataSourceDecision.markParam();
                ReadWriteDataSourceDecision.setDataSource(ConverterUtils.toString(pagexBaseDTO.getModelConfig().get("db")));
            }
        }
    }

    /**
     * 查询列表数据并且调用joinservice 填充
     * @param namespace  namespace
     * @param paramMap 参数
     * @return 填充好的JSONArray
     */
    protected JSONArray findListDataAndInitJoin(String namespace, Map<String,Object> paramMap){
        this.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        String resultJson = service.findListPage(paramMap, namespace);
        JSONArray rows = JSONObject.parseArray(resultJson);
        this.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        rows = joinService.initJoinData(rows, namespace);
        listExtendsHanleService.processingData(namespace,rows);
        return rows;
    }
}
