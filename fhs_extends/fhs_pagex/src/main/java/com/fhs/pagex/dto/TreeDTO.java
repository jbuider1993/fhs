package com.fhs.pagex.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeDTO {
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
    private List<TreeDTO> children = new ArrayList<>();
}
