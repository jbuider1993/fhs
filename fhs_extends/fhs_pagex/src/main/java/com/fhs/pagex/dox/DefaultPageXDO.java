package com.fhs.pagex.dox;

import com.fhs.core.base.dox.BaseDO;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 只用来存sql用此类没实际用途
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.bean
 * @ClassName: DefaultPageXBean
 * @Author: JackWang
 * @CreateDate: 2018/12/17 0017 19:57
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/17 0017 19:57
 * @Version: 1.0
 */
@Entity
@Table(name="t_default")
@Data
public class DefaultPageXDO extends BaseDO<DefaultPageXDO> {
    @Id
    private String id;
}
