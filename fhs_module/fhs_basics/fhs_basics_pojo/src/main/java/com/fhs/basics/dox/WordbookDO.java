package com.fhs.basics.dox;


import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 字典实体类
 *
 * @author wanglei
 * @version [版本号, 2015年8月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_service_wordbook")
public class WordbookDO extends BaseDO<WordbookDO> {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4651593237917581586L;

    /**
     * id
     */
    @Id
    private Integer wordbookId;

    /**
     * 加密id
     */
    @Transient
    private String wordbookIdE;

    /**
     * 字典code
     */
    @NotNull(message = "wordbookCode字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "wordbookCode字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "wordbook_code")
    private String wordbookCode;

    /**
     * 字典解释/描述
     */
    @NotNull(message = "wordbookDesc字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "wordbookDesc字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "wordbook_desc")
    private String wordbookDesc;

    /**
     * 字典解释/描述
     */
    @Length(message = "wordbookDescEN字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "wordbook_desc_en")
    private String wordbookDescEN;

    /**
     * 字典解释/描述
     */
    @Length(message = "wordbookDesc字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "wordbook_desc_tw")
    private String wordbookDescTW;


    /**
     * 字典分组code
     */
    @NotNull(message = "wordbookGroupCode字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "wordbookGroupCode字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "wordbook_group_code")
    private String wordbookGroupCode;

    /**
     * 排序字段
     */
    @Max(message = "orderNum字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "orderNum字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "order_num")
    private Integer orderNum;


}
