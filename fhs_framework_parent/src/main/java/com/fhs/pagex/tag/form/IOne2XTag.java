package com.fhs.pagex.tag.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 一对多tag
 */
public interface IOne2XTag {
    /**
     * 表单中的html
     * @return
     */
    String getFormHtml();

    /**
     * 当数据加载的时候执行的代码
     * @return
     */
    String one2XDataLoadSuccessJs();

    /**
     * 保存的时候执行的代码
     * @return
     */
    String one2XSaveJs();

    /**
     * 全局
     * @return
     */
    String one2XOverallJs();

    /**
     * 设置值的代码
     * @return
     */
    String setValueJs();

    /**
     * 获取值的代码
     * @return
     */
    String getValueJs();

    /**
     * 设置当前运行状态为One2XModel模式
     */
    void makeOne2XModel();

    /**
     * 设置tag的配置信息
     * @param tagSett 标签配置
     * @return
     */
     void setTagSett(Map<String,Object> tagSett, HttpServletRequest request, HttpServletResponse response);

}
