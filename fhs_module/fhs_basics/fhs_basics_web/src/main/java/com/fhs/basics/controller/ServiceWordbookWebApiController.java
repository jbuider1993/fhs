package com.fhs.basics.controller;

import com.fhs.basics.service.ServiceWordBookService;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("webApi/wordbook")
public class ServiceWordbookWebApiController extends BaseController {

    @Autowired
    private ServiceWordBookService wordBookService;

    /**
     * 查询省市区外调接口
     */
    @RequestMapping("getData")
    public void getData(String wordbookGroupCode) {
         super.outJsonp(JsonUtils.list2json(wordBookService.getWordBookList(wordbookGroupCode)));
    }
}
