package com.fhs.core.trans.service;

import com.fhs.core.base.pojo.vo.VO;

import java.util.List;

/**
 * 只有实现了这个接口的才能自动翻译
 * by jackwong
 */
public interface AutoTransAble<V extends VO> {

    /**
     * 根据ids查询
     * @return vo
     */
    List<V> findByIds(List<? extends Object> ids);

    /**
     * 获取db中所有的数据
     * @return db中所有的数据
     */
    List<V> select();

    /**
     * 根据id获取 vo
     * @param primaryValue id
     * @return vo
     */
    V selectById(Object primaryValue);
}
