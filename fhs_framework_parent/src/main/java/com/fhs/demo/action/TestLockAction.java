package com.fhs.demo.action;

import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.Logger;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.exception.BusinessException;
import com.fhs.core.result.HttpResult;
import com.fhs.demo.service.TestLockService;
import com.fhs.ucenter.api.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.demo.action
 * @ClassName: TestLockAction
 * @Author: JackWang
 * @CreateDate: 2019/1/8 0008 16:51
 * @UpdateUser: JackWang
 * @UpdateDate: 2019/1/8 0008 16:51
 * @Version: 1.0
 */
@Controller
@RequestMapping("/lock")
public class TestLockAction {

    private static Logger log = Logger.getLogger(TestLockAction.class);
    @Autowired
    private TestLockService testLockService;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public HttpResult<String> hello(String name){
        String uuid = StringUtil.getUUID();
        SysUserVo vo = new SysUserVo();
        vo.setUserId("222");
        log.info("即将调用:" + uuid + DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN));
        try
        {
            testLockService.sayHello(vo);
        }catch (BusinessException e)
        {
            log.info("系统正忙:" + uuid );
        }

        log.info("调用完成:" + uuid+ DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN));
        return HttpResult.success("111");
    }

}
