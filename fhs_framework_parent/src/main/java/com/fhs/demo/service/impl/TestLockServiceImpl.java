package com.fhs.demo.service.impl;

import com.fhs.common.utils.Logger;
import com.fhs.demo.service.TestLockService;
import com.fhs.lock.annotations.AddLock;
import com.fhs.ucenter.api.vo.SysUserVo;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.demo.service.impl
 * @ClassName: TestLockServiceImpl
 * @Author: JackWang
 * @CreateDate: 2019/1/8 0008 16:48
 * @UpdateUser: JackWang
 * @UpdateDate: 2019/1/8 0008 16:48
 * @Version: 1.0
 */
@Service
public class TestLockServiceImpl implements TestLockService {
    private static Logger log = Logger.getLogger(TestLockServiceImpl.class);

    @Override
    @AddLock(key = "'hello'+#p0.userId", maxWait = 5000, timeout =5)
    public void sayHello(SysUserVo user){
        try {
            log.info("拿到了锁，准备睡眠");
            Thread.sleep(6);
            log.info("你好" + user.getUserId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
