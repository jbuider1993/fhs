package com.fhs.basics.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户下拉tree DTO
 */
@Data
public class SysUserOrgVO {
    /**
     * 名字
     */
    private String text;
    /**
     * id
     */
    private String id;
    /**
     * 父id
     */
    private String parentId;
    /**
     * 是否是用户
     */
    private Integer isUser;
    /**
     * 子集合
     */
    private List<SysUserOrgVO> children = new ArrayList<>();

    public SysUserOrgVO() {
    }

    public SysUserOrgVO(String text, String id, String parentId, Integer isUser, List<SysUserOrgVO> children) {
        this.text = text;
        this.id = id;
        this.parentId = parentId;
        this.isUser = isUser;
        this.children = children;
    }
}
