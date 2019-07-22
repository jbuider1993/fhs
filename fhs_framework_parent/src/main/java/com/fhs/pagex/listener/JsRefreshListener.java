package com.fhs.pagex.listener;

/**
 * 刷新js的handel
 */
@FunctionalInterface
public interface JsRefreshListener {

    /**
     * 刷新js
     * @param namespace  namespace
     * @param js 新的js内容
     */
    void jsRefresh(String namespace,String js);
}
