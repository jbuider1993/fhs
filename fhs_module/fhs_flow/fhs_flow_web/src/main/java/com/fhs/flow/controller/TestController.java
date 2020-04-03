package com.fhs.flow.controller;

import com.fhs.flow.service.FlowCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RequestMapping("/flowtest")
@Controller
public class TestController {
    @Autowired
    private FlowCoreService flowCoreService;

    @RequestMapping("/startInstance")
    public void startInstance() {
        try {
            flowCoreService.updateStartProcessInstanceByKey("process1573627651428", "4", new HashMap<>(), new HashMap<>(), "1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
