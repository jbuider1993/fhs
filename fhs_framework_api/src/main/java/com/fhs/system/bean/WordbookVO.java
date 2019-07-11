package com.fhs.system.bean;

import com.fhs.core.base.vo.BaseVo;
import lombok.Data;

/**
 * 字典实体类
 *
 * @author wanglei
 * @version [版本号, 2015年8月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class WordbookVO extends BaseVo<WordbookVO>
{

    /**
     * id
     */
    private Integer wordbookId;

    /** 加密id */
    private String wordbookIdE;

    /**
     * 字典code
     */
    private String wordbookCode;

    /**
     * 字典解释/描述
     */
    private String wordbookDesc;

    /**
     * 字典解释/描述
     */
    private String wordbookDescEN;

    /**
     * 字典解释/描述
     */
    private String wordbookDescTW;


    /**
     * 字典分组code
     */
    private String wordbookGroupCode;

    /**
     * 排序字段
     */
    private Integer orderNum;


}
