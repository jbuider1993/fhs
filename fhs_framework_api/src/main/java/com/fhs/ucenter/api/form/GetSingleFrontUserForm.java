package com.fhs.ucenter.api.form;

import com.fhs.core.base.form.BaseForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取单个用户form
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSingleFrontUserForm extends BaseForm {
    private String accessToken;
    private String userId;
}
