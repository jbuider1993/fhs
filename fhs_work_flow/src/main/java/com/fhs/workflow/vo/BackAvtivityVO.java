package com.fhs.workflow.vo;

import com.fhs.core.base.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 可回退 节点
 * @author Jackwong
 * @date 2019 -11-12 16:28:30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackAvtivityVO extends BaseVo<BackAvtivityVO> {
    // 标题
    private String title;
    // id
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BackAvtivityVO that = (BackAvtivityVO) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }
}
