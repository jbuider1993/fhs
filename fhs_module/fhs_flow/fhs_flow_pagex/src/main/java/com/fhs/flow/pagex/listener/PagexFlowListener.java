package com.fhs.flow.pagex.listener;

import com.alibaba.fastjson.JSONObject;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.EMap;
import com.fhs.core.db.ds.ReadWriteDataSourceDecision;
import com.fhs.core.listener.DistributedListener;
import com.fhs.flow.constant.FlowConstant;
import com.fhs.logger.anno.LogDesc;
import com.fhs.pagex.service.PageXDBService;
import com.fhs.pagex.service.PagexDataService;
import com.fhs.pagex.vo.PagexBaseVO;
import com.mybatis.jpa.context.MultiTenancyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class PagexFlowListener implements DistributedListener {

    @Autowired
    protected PageXDBService service;

    @Override
    public Set<String> namespace() {
        Set<String> namespaces = new HashSet<>();
        namespaces.add(FlowConstant.LISTENER_INSTANCE);
        return namespaces;
    }

    @Override
    public String eventType() {
        return FlowConstant.ENVENT_NEWS;
    }

    @Override
    public Map<String, Object> onMessage(Map<String, Object> param) {
        //有namespace的才是pagex需要监听的,其他的不管
        if(param.containsKey("namespace")){
            String namespace = ConverterUtils.toString(param.get("namespace"));
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", param.get("businessKey"));
            setDB(PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace));
            Map<String,Object> dbParam = JSONObject.parseObject(service.findBean(paramMap, namespace));
            dbParam.put("instanceStatus",param.get("instanceStatus"));
            EMap<String,Object> updateParam = new EMap<>();
            updateParam.putAll(dbParam);
            service.update(updateParam,namespace);
        }
        return null;
    }

    protected void setDB(PagexBaseVO pagexBaseDTO) {
        if (pagexBaseDTO != null) {
            if (pagexBaseDTO.getModelConfig().containsKey("db")) {
                ReadWriteDataSourceDecision.markParam();
                ReadWriteDataSourceDecision.setDataSource(ConverterUtils.toString(pagexBaseDTO.getModelConfig().get("db")));
            }
        }
    }
}
