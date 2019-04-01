package com.fhs.demo.action;

import com.fhs.core.result.HttpResult;
import com.fhs.demo.service.CacheTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/testCache")
public class TestCache {

    @Autowired
    private CacheTestService cacheTestService;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public HttpResult<String> hello(String name){
        HttpResult<String> httpResult = new HttpResult<>();
        httpResult.setCode (200);
        httpResult.setResult (true);
        httpResult.setData (cacheTestService.getHello(name));
        return httpResult;
    }

    @RequestMapping(value = "/hello2")
    @ResponseBody
    public HttpResult<String> hello2(String name){
        HttpResult<String> httpResult = new HttpResult<>();
        httpResult.setCode (200);
        httpResult.setResult (true);
        Map map = new HashMap();
        map.put("name", name);
        map.put("id", "123456");
        httpResult.setData (cacheTestService.getHello2(map));
        return httpResult;
    }

    @RequestMapping(value = "/testSession")
    @ResponseBody
    public HttpResult<String> testSession(HttpServletRequest request){
        System.out.println(request.getSession());
        return HttpResult.success("11");
    }

}
