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
 * 流程xml管理
 *
 * @author wanglei
 * @version [版本号, 2017/07/25 11:04:22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@Builder
@TableName("t_work_flow_jbpm_xml")
@TransTypes(types = {"wordbook"})
public class WorkFlowJbpmXml extends BaseDO<WorkFlowJbpmXml> {
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
     * 流程名字
     */
    @NotNull(message = "{workflow.processName.null}")
    @Length(message = "{workflow.processName.length}", groups = {Add.class, Update.class}, max = 64, min = 0)

    @Column(name = "process_name", nullable = false, length = 64)
    private String processName;
    /**
     * 工作流平台
     */
    @NotNull(message = "{workflow.typeId.null}")
    @Max(message = "{workflow.typeId.max}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{workflow.typeId.min}", value = -2147483648, groups = {Add.class, Update.class})

    @Column(name = "type_id", nullable = false, length = 10)
    @Trans(type = "wordbook", key = "workFlow_xml_type")
    private Integer typeId;
    /**
     * xml内容
     */

    @Column(name = "process_descriptor", nullable = true, length = 65535)
    private String processDescriptor;
    /**
     * xml图片的base64
     */

    @Column(name = "xml_img_base64", nullable = true, length = 65535)
    private String xmlImgBase64;
    /**
     * 工作流key
     */
    @Length(message = "{workflow.processKey.length}", groups = {Add.class, Update.class}, max = 64, min = 0)

    @Column(name = "process_key", nullable = true, length = 64)
    private String processKey;

    /**
     * 工作流部署状态
     */

    @Column(name = "state", nullable = true, length = 64)
    @Trans(type = "wordbook", key = "workFlow_release_state")
    private Integer state = 0;

    /**
     * 是否可以选择下一步
     */

    @Column(name = "is_choose_next", nullable = true, length = 64)
    @Trans(type = "wordbook", key = "pub_boolean")
    private Integer isChooseNext;

    /**
     * 是否使用公共表单
     */

    @Column(name = "is_pub_form", nullable = true, length = 64)
    @Trans(type = "wordbook", key = "pub_boolean")
    private Integer isPubForm;

    public WorkFlowJbpmXml() {
    }

    /**
     * 表单名称
     */

    @Column(name = "form_name", nullable = true, length = 64)
    private String formName;

    public WorkFlowJbpmXml(Integer id, String processName, Integer typeId, String processDescriptor, String xmlImgBase64, String processKey, Integer state, Integer isChooseNext, Integer isPubForm, String formName) {
        this.id = id;
        this.processName = processName;
        this.typeId = typeId;
        this.processDescriptor = processDescriptor;
        this.xmlImgBase64 = xmlImgBase64;
        this.processKey = processKey;
        this.state = state;
        this.isChooseNext = isChooseNext;
        this.isPubForm = isPubForm;
        this.formName = formName;
    }
}
