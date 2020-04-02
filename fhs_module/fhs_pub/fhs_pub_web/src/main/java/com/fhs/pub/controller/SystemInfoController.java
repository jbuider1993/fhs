package com.fhs.pub.controller;

import com.fhs.core.result.HttpResult;
import com.fhs.pub.vo.SystemHardwareInfoVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取系统信息
 * 感谢guns作者 stylefeng 老哥
 */
@RestController
@RequestMapping("ms/sys")
public class SystemInfoController {

    /**
     * 获取系统信息
     * @return
     */
    @RequestMapping("info")
    public HttpResult<SystemHardwareInfoVO> getSystemInfo(){
        SystemHardwareInfoVO systemHardwareInfo = new SystemHardwareInfoVO();
        systemHardwareInfo.copyTo();
        return HttpResult.success(systemHardwareInfo);
    }
}
