package com.fhs.demo.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TestAopBean implements Serializable {

    @NotNull(message="TestAop的id字段 不可为null ")
    @Max(message="TestAop的id超过int最大值", value=2147483647)
    @Min(message="TestAop的id小于int最大值", value=-2147483648)
    private Integer id;

    @NotNull(message="TestAop的name字段不能为空")
    @Length(message="TestAop的name字段的长度最大为50")
    private String name;

    @NotNull(message="TestAop的addr字段不能为空")
    @Length(message="TestAop的addr字段的长度最大为50")
    private String addr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
