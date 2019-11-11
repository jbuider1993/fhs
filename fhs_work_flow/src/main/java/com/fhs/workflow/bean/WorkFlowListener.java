package com.fhs.workflow.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransTypes;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


/**
 * 流程监听器
 *
 * @author wanglei
 * @version [版本号, 2017/07/24 11:06:16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@Data
@Builder
@TransTypes(types = {"wordbook"})
@TableName("t_work_flow_listener")
public class WorkFlowListener extends BaseDO<WorkFlowListener> {
    /**
     *
     */
    @NotNull(message = "{workflow.id.null}", groups = {Update.class, Delete.class})
    @Max(message = "{workflow.id.max}", value = 2147483647, groups = {Delete.class, Update.class})
    @Min(message = "{workflow.id.min}", value = -2147483648, groups = {Delete.class, Update.class})
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private Integer id;
    /**
     * 监听器名称
     */
    @NotNull(message = "{workflow.listenerName.null}")
    @Length(message = "{workflow.listenerName.length}", groups = {Add.class, Update.class}, max = 32, min = 0)
    @Column(name = "listener_name", nullable = false, length = 32)
    private String listenerName;
    /**
     * 监听器类型
     */
    @NotNull(message = "{workflow.listenerType.null}")
    @Column(name = "listener_type", nullable = false, length = 10)
    @Trans(type = "wordbook", key = "workFlow_listener_type")
    private Integer listenerType;
    /**
     * 事件
     */
    @NotNull(message = "{workflow.event.null}")
    @Column(name = "event", nullable = false, length = 10)
    @Trans(type = "wordbook", key = "workFlow_event_type")
    private String event;
    /**
     * 执行类型
     */
    @NotNull(message = "{workflow.excuteType.null}")
    @Column(name = "excute_type", nullable = false, length = 10)
    @Trans(type = "wordbook", key = "workFlow_excute_type")
    private String excuteType;
    /**
     * 执行内容
     */
    @NotNull(message = "{workflow.excuteContent.null}")
    @Column(name = "excute_content", nullable = false, length = 102)
    private String excuteContent;

    public WorkFlowListener() {
    }

    public WorkFlowListener(Integer id, String listenerName, Integer listenerType, String event, String excuteType, String excuteContent) {
        this.id = id;
        this.listenerName = listenerName;
        this.listenerType = listenerType;
        this.event = event;
        this.excuteType = excuteType;
        this.excuteContent = excuteContent;
    }
}
