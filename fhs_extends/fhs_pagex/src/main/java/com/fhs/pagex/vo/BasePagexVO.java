package com.fhs.pagex.vo;

import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.base.pojo.vo.VO;


/**
 * 描述
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.bean
 * @ClassName: BasePagexBean
 * @Author: JackWang
 * @CreateDate: 2018/12/29 0029 12:33
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/29 0029 12:33
 * @Version: 1.0
 */
public class BasePagexVO<T extends BasePagexVO>  extends BaseDO<T> implements VO {

    private static final String ID_FIELD_NAME = "id";


    public Object getId() {
        if(ReflectUtils.getDeclaredField(this.getClass(), ID_FIELD_NAME) != null){
            return ReflectUtils.getValue(this, ID_FIELD_NAME);
        }
        return getPkey();
    }
}
