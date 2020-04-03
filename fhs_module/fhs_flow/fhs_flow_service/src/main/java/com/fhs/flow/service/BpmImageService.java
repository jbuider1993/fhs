package com.fhs.flow.service;

import java.io.InputStream;

/**
 * activiti工作流图片服务
 * @author Jackwong
 * @date 2019 -11-11 19:45:07
 */
public interface BpmImageService {
    /**
     * 获取工作流图片
     * @param actDefId 流程定义id
     * @param processInstanceId 流程实例id
     * @return 图片的inputstream
     * @throws Exception
     */
    InputStream draw(String actDefId, String processInstanceId) throws Exception;
}
