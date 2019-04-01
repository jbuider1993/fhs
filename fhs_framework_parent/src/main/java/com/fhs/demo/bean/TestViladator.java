package com.fhs.demo.bean;

import com.fhs.core.group.Add;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 描述
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.demo.bean
 * @ClassName: TestViladator
 * @Author: JackWang
 * @CreateDate: 2019/2/15 0015 15:10
 * @UpdateUser: JackWang
 * @UpdateDate: 2019/2/15 0015 15:10
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestViladator {

    @NotNull(message = "name不能为null",groups = Add.class)
    @NotEmpty(message = "name不能为空")
    private String name;

    @NotEmpty(message = "age不能为空")
    private String age;
}
