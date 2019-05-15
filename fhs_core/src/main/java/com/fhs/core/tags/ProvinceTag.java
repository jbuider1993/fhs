package com.fhs.core.tags;

import com.fhs.core.config.EConfig;

import javax.servlet.jsp.JspException;
import java.io.IOException;


/**
 * 省份tag.
 *
 * @Filename: ProvinceTag.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class ProvinceTag extends BaseFormTag
{

    /** 市那个字段的名称. */
    private String cityName;

    /** 区县字段的名称. */
    private String areaName;

    @Override
    public void doTag()
        throws JspException, IOException
    {
        super.setClassName(super.getClassName() + " easyui-combobox");
        write(" <div class=\"fitemDiv\">");
        write("<label>" + super.getTitle() + ":</label>");
        write("<input id="+super.getName()+" editable=\"true\"  data-options=\"\r\n" + "                            url: '"
            + EConfig.getPathPropertiesValue("systemServiceUrl") + "webApi/area/getProvinceData?areaParentId=0&jsonpCallback=?',\r\n"
            + "                            valueField: 'id',\r\n"
            + "                            textField:'areaName',\r\n"
            + "                            onSelect: function(rec){\r\n"
            + "                                if('undefined' != typeof provinceName){ provinceName = rec.areaName;}\r\n");
        if (cityName != null)
        {
            write("                                $('#cityId').combobox('loadData', {});\r\n"
                + "                                var cityUrl = '" + EConfig.getPathPropertiesValue("systemServiceUrl")
                + "webApi/area/getProvinceData?areaParentId=' + rec.id + '&jsonpCallback=?';\r\n" + "                                $('#"
                + cityName + "').combobox('reload', cityUrl);\r\n" + "                                $('#" + cityName
                + "').combobox('clear');\r\n");
        }
        if (areaName != null)
        {
            write("$('#" + cityName + "').combobox('loadData', {});\r\n" +
                "$('#" + areaName + "').combobox('loadData', {});\r\n" +
                "$('#" + areaName + "').combobox('clear');\r\n"
                + "},onLoadSuccess:function(v){" +
                    " if(!isInitProvinceId){" +
                    "  $('#"+super.getName()+"').combobox('select', '440000');isInitProvinceId = true;}}\r\n" + "\" ");
        }
        write(super.getHtml().toString());
        write(" </div>");
    }

    /**
     * 获取 市那个字段的名称.
     *
     * @return bean的 市那个字段的名称
     */
    public String getCityName()
    {
        return cityName;
    }

    /**
     * 设置 市那个字段的名称.
     *
     * @param cityName 一个新的 市那个字段的名称
     */
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    /**
     * 获取 区县字段的名称.
     *
     * @return bean的 区县字段的名称
     */
    public String getAreaName()
    {
        return areaName;
    }

    /**
     * 设置 区县字段的名称.
     *
     * @param areaName 一个新的 区县字段的名称
     */
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

}
