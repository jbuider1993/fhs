package com.fhs.pagex.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeVO {
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
     * 子集合
     */
    private List<TreeVO> children = new ArrayList<>();
}
