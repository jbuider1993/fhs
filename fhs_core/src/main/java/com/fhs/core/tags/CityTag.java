package com.fhs.core.tags;

import javax.servlet.jsp.JspException;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * 区县标签.
 *
 * @Filename: CityTag.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *              陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class CityTag extends BaseFormTag
{

    /** 区县名称. */
    private String areaName;

    @Override
    public void doTag()
        throws JspException, IOException
    {
        super.setClassName(super.getClassName() + " easyui-combobox");
        write(" <div class=\"fitemDiv\">");
        write("<label>" + super.getTitle() + ":</label>");
        write("<input id="+super.getName()+" editable=\"true\" data-options=\"\r\n" + "                            valueField: 'id',\r\n"
            + "                            textField:'areaName',\r\n"
            + "                            onSelect: function(rec){\r\n"
            + "                                if('undefined' != typeof cityName){cityName = rec.areaName;}\r\n");
        if (areaName != null)
        {
            write("                                $('#" + areaName + "').combobox('loadData', {});\r\n"
                + "                                var url = '" + BaseTagsURI.systemServiceUrl
                + "webApi/area/getProvinceData?areaParentId=' + rec.id + '&jsonpCallback=?' ;\r\n" + "                                $('#"
                + areaName + "').combobox('reload', url);\r\n" + "                                $('#" + areaName
                + "').combobox('clear');");
        }
        write("                            },onLoadSuccess:function () {" +
                "if(!isInitCityId &&  Object.keys($('#"+super.getName()+"').combobox('getData')).length!=0 && !isEdit && !isView){" +
                " $('#"+super.getName()+"').combobox('select', '440300');isInitCityId = true;}}\"");
        write(super.getHtml().toString());
        write(" </div>");
    }

    /**
     * 获取 区县名称.
     *
     * @return bean的 区县名称
     */
    public String getAreaName()
    {
        return areaName;
    }

    /**
     * 设置 区县名称.
     *
     * @param areaName 一个新的 区县名称
     */
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

}
