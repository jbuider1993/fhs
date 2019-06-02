package com.fhs.common.excel;
/**
 * excel 自定义验证器
 */
public interface ExcelValidor {
    /**
     * 验证参数是否符合要求
     * @param param 参数
     * @param valid 验证规则
     * @param errorBuilder
     * @param colName
     * @param rowIndex
     * @return
     */
    abstract boolean validParam(Object param,String valid,StringBuilder errorBuilder,char colName,int rowIndex);
}
