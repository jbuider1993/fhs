package com.fhs.basics.controller;

import com.fhs.basics.service.WordBookService;
import com.fhs.basics.vo.WordbookVO;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("webApi/wordbook")
public class WordbookWebApiAction extends BaseController {

    @Autowired
    private WordBookService wordBookService;

    /**
     * 查询省市区外调接口
     * @param request
     * @param response
     */
    @RequestMapping("getData")
    @ResponseBody
    public void getData(HttpServletRequest request, HttpServletResponse response)
    {
        List<WordbookVO> list = wordBookService.getWordBookList(request.getParameter("wordbookGroupCode"));
        outJsonp(JsonUtils.list2json(list), response, request);
    }
}
