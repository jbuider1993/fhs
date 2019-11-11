package com.fhs.workflow.bean;

import java.io.Serializable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.mybatis.jpa.annotation.*;
import com.fhs.core.group.*;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.bean.BaseDO;
import javax.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fhs.core.base.bean.BaseDO;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 监听器(FlowListener)实体类
 *
 * @author sb生成的代码
 * @since 2019-11-11 14:28:44
 */

@Data
@Builder
@TableName("t_flow_listener")
public class FlowListener extends BaseDO<FlowListener> {
    private static final long serialVersionUID = -34427919221391088L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    
    //标题
        @NotEmpty
    @NotNull(message="标题字段不可为null", groups = {Update.class, Delete.class})
  @Length(message = "标题字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("title")
    private String title;
    
    //url
        @NotEmpty
    @NotNull(message="url字段不可为null", groups = {Update.class, Delete.class})
  @Length(message = "url字段的长度最大为255", groups = {Add.class, Update.class}, max = 255)
    @TableField("uri")
    private String uri;
    
    //备注
    @Length(message = "备注字段的长度最大为255", groups = {Add.class, Update.class}, max = 255)
    @TableField("remark")
    private String remark;
    
    //类型0 全局 2 连线 3 节点
         @NotNull(message="类型0 全局 2 连线 3 节点字段不可为null", groups = {Update.class, Delete.class})
  @TableField("type")
    private Integer type;
    
    //在哪个服务器上
         @NotNull(message="在哪个服务器上字段不可为null", groups = {Update.class, Delete.class})
  @TableField("server")
    private Integer server;
    

    public FlowListener(){}
    
     public FlowListener(
         String id,
         String title,
         String uri,
         String remark,
         Integer type,
         Integer server ){
              this.id=id;
              this.title=title;
              this.uri=uri;
              this.remark=remark;
              this.type=type;
              this.server=server;
          }


}