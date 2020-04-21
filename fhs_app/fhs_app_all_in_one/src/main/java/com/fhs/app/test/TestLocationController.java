package com.fhs.app.test;

import com.fhs.core.result.HttpResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class TestLocationController {
    @RequestMapping("/webApi/test/addLocations")
    public HttpResult<String> xx(@RequestBody String json){
        System.out.println(json);
        return HttpResult.success("ok");
    }
}
