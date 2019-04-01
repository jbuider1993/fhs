package com.fhs.ucenter.api;

import com.fhs.core.db.DataSource;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.form.GetSingleFrontUserForm;
import com.fhs.ucenter.api.service.FeignFrontUserApiService;
import com.fhs.ucenter.api.vo.FrontUserVo;
import com.fhs.ucenter.bean.UcenterFrontUser;
import com.fhs.ucenter.bean.UcenterFrontUserBind;
import com.fhs.ucenter.service.LoginService;
import com.fhs.ucenter.service.UcenterFrontUserBindService;
import com.fhs.ucenter.service.UcenterFrontUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 前段用户统一登录
 * by jackwong
 *
 */
@RestController
@RequestMapping("/api/frontUser")
@DataSource("ucenter")
public class FrontUserApiServiceCloud implements FeignFrontUserApiService {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UcenterFrontUserService frontUserService;

    @Autowired
    private UcenterFrontUserBindService frontUserBindService;

    @Override
    @RequestMapping("/getSingleFrontUser")
    public HttpResult<FrontUserVo> getSingleFrontUser(@RequestBody GetSingleFrontUserForm getSingleFrontUserForm) {
        String userId = getSingleFrontUserForm.getUserId();
        if(userId == null)
        {
            userId = loginService.getUserIdByAccessToken(getSingleFrontUserForm.getAccessToken());
        }
        if(userId == null)
        {
            throw new ParamException("用户id和用户accessToken都没传或者无效");
        }
        UcenterFrontUser user = frontUserService.selectById(userId);
        if(user==null)
        {
            throw new ParamException("用户id无效：" + userId);
        }
        FrontUserVo resultVo  = new FrontUserVo();
        BeanUtils.copyProperties(user,resultVo);
        List<UcenterFrontUserBind> binds = frontUserBindService.findForList(UcenterFrontUserBind.builder().userId(user.getUserId()).build());
        Map<Integer,String> openIdMap = new HashMap<>();
        binds.forEach(bind->{
            openIdMap.put(bind.getAuthOpenidType(),bind.getAuthOpenid());
        });
        resultVo.setOpenIdMap(openIdMap);
        return HttpResult.success(resultVo);
    }
}