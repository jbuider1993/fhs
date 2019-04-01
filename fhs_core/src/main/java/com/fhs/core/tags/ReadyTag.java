package com.fhs.core.tags;

/**
 *  keng 表单加载失败的 时候的一个公共处理
 *  此方法要处理省市区 UE 多图 单图 地图 的回显
 *  如果判断isview  renderView (dialog也给他做了)
 *  如果formLoadSuccess 不存在就不用调用了，如果存在就调用一下。
 * @Filename: ReadyTag.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class ReadyTag
{
     private String formId = "addUpdateForm";

     private String dialogId = "addOrUpdateDialog";

     private String nameSpace;

     private String idField = "id";

     private String updateFun = "update";

     private String addFun = "add";

     private String getBeanFun = "getBeanData";


}
