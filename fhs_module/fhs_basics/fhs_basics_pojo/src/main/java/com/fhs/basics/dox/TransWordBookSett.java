package com.fhs.basics.dox;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用于字典表翻译配置
 *
 * @author wanglei
 * @version [版本号, 2015年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class TransWordBookSett {
    /**
     * 用于显示的字段名  会吧翻译的结果set到这个字段上
     */
    private String showField;

    /**
     * 用于获取字典编码的字段
     */
    private String valueField;

    /**
     * 字典分组的编码
     */
    private String wordbookGroupCode;


    public TransWordBookSett(String showField, String valueField, String wordbookGroupCode) {
        this.showField = showField;
        this.valueField = valueField;
        this.wordbookGroupCode = wordbookGroupCode;
    }


    public String getShowField() {
        return showField;
    }


    public void setShowField(String showField) {
        this.showField = showField;
    }


    public String getValueField() {
        return valueField;
    }


    public void setValueField(String valueField) {
        this.valueField = valueField;
    }


    public String getWordbookGroupCode() {
        return wordbookGroupCode;
    }


    public void setWordbookGroupCode(String wordbookGroupCode) {
        this.wordbookGroupCode = wordbookGroupCode;
    }


}
