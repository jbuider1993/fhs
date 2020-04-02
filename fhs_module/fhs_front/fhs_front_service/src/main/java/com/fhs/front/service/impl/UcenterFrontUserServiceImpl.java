package com.fhs.front.service.impl;

import com.fhs.common.utils.CheckUtils;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.core.valid.checker.ParamChecker;
import com.fhs.front.api.rpc.FeignFrontUserApiService;
import com.fhs.front.dox.UcenterFrontUserBindDO;
import com.fhs.front.dox.UcenterFrontUserDO;
import com.fhs.front.form.GetSingleFrontUserForm;
import com.fhs.front.service.LoginService;
import com.fhs.front.service.UcenterFrontUserBindService;
import com.fhs.front.service.UcenterFrontUserService;
import com.fhs.front.vo.UcenterFrontUserBindVO;
import com.fhs.front.vo.UcenterFrontUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端用户表(UcenterFrontUser)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-11 14:14:58
 */
@Primary
@Service("UcenterFrontUserService")
@DataSource("base_business")
public class UcenterFrontUserServiceImpl extends BaseServiceImpl<UcenterFrontUserVO, UcenterFrontUserDO> implements UcenterFrontUserService, FeignFrontUserApiService {

    @Autowired
    private LoginService loginService;


    @Autowired
    private UcenterFrontUserBindService frontUserBindService;

    @Override
    public HttpResult<UcenterFrontUserVO> getSingleFrontUser(GetSingleFrontUserForm getSingleFrontUserForm) {
        String userId = getSingleFrontUserForm.getUserId();
        if (userId == null) {
            userId = loginService.getUserIdByAccessToken(getSingleFrontUserForm.getAccessToken());
            log.info("根据accessToken" + getSingleFrontUserForm.getAccessToken() + "获取到的userId为:" + userId);
        }
        if (CheckUtils.isNullOrEmpty(userId)) {
            throw new ParamException("用户id和用户accessToken都没传或者无效");
        }
        UcenterFrontUserVO user = this.selectById(userId);
        if (user == null) {
            throw new ParamException("用户id无效：" + userId);
        }
        List<UcenterFrontUserBindVO> binds = frontUserBindService.findForList(UcenterFrontUserBindDO.builder().userId(user.getUserId()).build());
        Map<Integer, String> openIdMap = new HashMap<>();
        binds.forEach(bind -> {
            openIdMap.put(bind.getAuthOpenidType(), bind.getAuthOpenid());
        });
        user.setOpenIdMap(openIdMap);
        return HttpResult.success(user);
    }

    @Override
    public HttpResult<Boolean> update(UcenterFrontUserVO frontUserVo) {
        ParamChecker.isNotNull(frontUserVo.getUserId(), "用户id不能为空");
        boolean result = 0 < this.updateSelectiveById(UcenterFrontUserVO.builder().userId(frontUserVo.getUserId()).mobile(frontUserVo.getMobile()).realName(frontUserVo.getRealName()).build());
        return HttpResult.success(result);
    }

    @Override
    public HttpResult<Boolean> add(UcenterFrontUserVO frontUserVo) {
        return HttpResult.success(this.insertSelective(frontUserVo) > 0);
    }

    @Override
    public HttpResult<UcenterFrontUserVO> find(UcenterFrontUserVO frontUserVo) {
        frontUserVo = this.selectBean(frontUserVo);
        if (frontUserVo == null) {
            return HttpResult.error(null);
        }
        return HttpResult.success(frontUserVo);
    }
}