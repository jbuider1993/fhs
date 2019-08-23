package com.fhs.ucenter.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户下拉tree DTO
 */
@Data
public class SysUserOrgDTO {
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
    private List<SysUserOrgDTO> children = new ArrayList<>();

    public SysUserOrgDTO(){}

    public SysUserOrgDTO(String text, String id, String parentId, Integer isUser, List<SysUserOrgDTO> children) {
        this.text = text;
        this.id = id;
        this.parentId = parentId;
        this.isUser = isUser;
        this.children = children;
    }
}
