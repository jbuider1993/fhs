package com.fhs.demo.action;

import com.fhs.core.group.Add;
import com.fhs.core.result.HttpResult;
import com.fhs.demo.bean.TestViladator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/viladator")
public class TestViladatorAction {

    @RequestMapping(value = "/checkParam")
    @ResponseBody
    public HttpResult<Map> checkParam( @Valid TestViladator testAop) {
       System.out.println("");
       return HttpResult.success(new HashMap());
    }

    @RequestMapping(value = "/checkAddParam")
    @ResponseBody
    public HttpResult<Map> checkAddParam( @Validated(Add.class) TestViladator testAop) {
        System.out.println("");
        return HttpResult.success(new HashMap());
    }
}
