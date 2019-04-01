package com.fhs.demo.action;

import com.fhs.core.permission.ParamVaild;
import com.fhs.core.result.HttpResult;
import com.fhs.demo.bean.TestAopBean;
import com.fhs.redis.service.RedisCacheService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/testaop")
public class TestAop {

    @Autowired
    private RedisCacheService<String> redisCacheService;

    @RequestMapping(value = "/t",  method = {RequestMethod.POST})
    @ResponseBody
    public HttpResult<TestAopBean> testAop(@RequestBody @ParamVaild TestAopBean testAop){
        HttpResult<TestAopBean> httpResult = new HttpResult<TestAopBean>();
        httpResult.setCode (200);
        httpResult.setMessage ("ok!");
        httpResult.setResult (true);
        httpResult.setData (testAop);

        return httpResult;
    }

    @RequestMapping(value = "/goIndexJsp",  method = {RequestMethod.GET})
    public String goIndexJsp(){
        redisCacheService.addStr ( "sssss", "helloworld" );
        System.out.println(SecurityUtils.getSubject().isPermitted("frontMembershipCard:add"));
        return "index";
    }

}
