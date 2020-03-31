package com.fhs.basics.dox;

import com.fhs.core.base.dox.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 字典分组
 *
 * @author nanshouxiao
 * @version [版本号, 2015/12/22 15:13:20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_service_wordbook_group")
public class ServiceWordbookGroupDO extends BaseDO<ServiceWordbookGroupDO> {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7194228955295169250L;

    /**
     * ID
     */
    @NotNull(message = "{wordbook.groupId.null}", groups = {Update.class, Delete.class})
    @Max(message = "{wordbook.groupId.max}", value = 2147483647, groups = {Delete.class, Update.class})
    @Min(message = "{wordbook.groupId.min}", value = -2147483648, groups = {Delete.class, Update.class})
    @Id
    private Integer groupId;

    /**
     * 加密ID
     */
    @NotNull(message = "{wordbook.groupIdE.null}")
    @Transient
    private String groupIdE;

    /**
     * 分组编码
     */
    @NotNull(message = "groupName字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "groupName字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "group_name")
    private String groupName;

    /**
     * 分组编码
     */
    @NotNull(message = "wordbookGroupCode字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "wordbookGroupCode字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "wordbook_group_code")
    private String wordbookGroupCode;


    /**
     * 获取加密ID
     */
    public String getGroupIdE() {
        return groupIdE;
    }


    /**
     * 获取ID
     */
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }


    /**
     * 给分组名称赋值
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取分组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 给分组编码赋值
     */
    public void setWordbookGroupCode(String wordbookGroupCode) {
        this.wordbookGroupCode = wordbookGroupCode;
    }

    /**
     * 获取分组编码
     */
    public String getWordbookGroupCode() {
        return wordbookGroupCode;
    }

}
