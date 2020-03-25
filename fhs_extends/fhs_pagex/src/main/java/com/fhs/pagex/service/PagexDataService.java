package com.fhs.pagex.service;

import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.exception.ParamException;
import com.fhs.logger.Logger;
import com.fhs.pagex.listener.JsRefreshListener;
import com.fhs.pagex.vo.PageXFrontVO;
import com.fhs.pagex.vo.PageXTreeVO;
import com.fhs.pagex.vo.PagexAddVO;
import com.fhs.pagex.vo.PagexListSettVO;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * pagex 包 所有的缓存数据都放到这里
 * 我是一个牛逼的单利
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.service
 * @ClassName: PagexDataService
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 20:58
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 20:58
 * @Version: 1.0
 */
public enum PagexDataService {

    /**
     * 是的只有一个
     */
    SIGNEL;

    private static final Logger LOG = Logger.getLogger(PagexDataService.class);

    /**
     * js内容存放map key为文件名
     */
    private Map<String, String> jsContentMap = new ConcurrentHashMap<>();

    /**
     * 列表页面html缓存
     */
    private Map<String, String> listPageHtmlCache = new ConcurrentHashMap<>();

    /**
     * addDTO缓存
     */
    private Map<String, PagexAddVO> pagexAddDtoCache = new ConcurrentHashMap<>();

    /**
     * listDTO缓存
     */
    private Map<String, PagexListSettVO> pagexListSettDTOCache = new ConcurrentHashMap<>();

    /**
     * pageXFrontDTO 缓存
     */
    private Map<String, PageXFrontVO> pageXFrontDTOCache = new ConcurrentHashMap<>();

    /**
     * PageXTreeDTO 缓存
     */
    private Map<String, PageXTreeVO> pageXTreeDTOCache = new ConcurrentHashMap<>();



    /**
     * 添加页面html缓存
     */
    private Map<String, String> addPageHtmlCache = new ConcurrentHashMap<>();
    /**
     * 刷新js的事件
     */
    private List<JsRefreshListener> jsRefreshListenerList = new ArrayList<>();

    /**
     * add页面扩展html模板路径
     */
    private Map<String, String> addPageExtendsHtmlPathMap = new ConcurrentHashMap<>();


    private PageXAutoJavaService pageXAutoJavaService;

    private PageXAutoSqlService pageXAutoSqlService;

    /**
     * 刷新js文件内容
     *
     * @param fileName    文件名称
     * @param fileContent 文件内容
     */
    public void refreshJsFile(String fileName, String fileContent) {
        String namespace = fileName.replace(".js", "");
        //如果内容已经更新执行刷新操作
        if (!ConverterUtils.toString(jsContentMap.get(namespace)).equals(fileContent)) {

            boolean isOldNamespace = jsContentMap.containsKey(namespace);
            jsContentMap.put(namespace, fileContent);
            addPageHtmlCache.remove(namespace);
            listPageHtmlCache.remove(namespace);
            pageXFrontDTOCache.remove(namespace);
            pagexAddDtoCache.remove(namespace);
            pagexListSettDTOCache.remove(namespace);
            pageXTreeDTOCache.remove(namespace);
            jsRefreshListenerList.forEach(listener -> {
                listener.jsRefresh(namespace, fileContent);
            });
            if (isOldNamespace) {
                LOG.info("正在刷新:" + namespace);
                try {
                    if (pageXAutoJavaService == null && SpringContextUtil.getApplicationContext()!=null) {
                        pageXAutoJavaService = SpringContextUtil.getBeanByName(PageXAutoJavaService.class);
                        pageXAutoSqlService = SpringContextUtil.getBeanByName(PageXAutoSqlService.class);
                    }
                    pageXAutoJavaService.autoJava(fileContent);
                    pageXAutoSqlService.autoSql(fileContent);
                    LOG.info("刷新完成:" + namespace);
                } catch (Exception e) {
                    LOG.error("刷新js文件错误,js:" + fileContent,e);
                }
            }
        }
    }


    /**
     * 获取js内容
     *
     * @param namespace namespace
     * @return js内容
     */
    public String getJsContent(String namespace) {
        return jsContentMap.get(namespace);
    }


    public Map<String, String> getListPageHtmlCache() {
        return listPageHtmlCache;
    }


    public void setListPageHtmlCache(Map<String, String> listPageHtmlCache) {
        this.listPageHtmlCache = listPageHtmlCache;
    }

    public Map<String, String> getAddPageHtmlCache() {
        return addPageHtmlCache;
    }

    public void setAddPageHtmlCache(Map<String, String> addPageHtmlCache) {
        this.addPageHtmlCache = addPageHtmlCache;
    }

    public Map<String, PagexAddVO> getPagexAddDtoCache() {
        return pagexAddDtoCache;
    }

    /**
     * 根据namespace获取 PagexAddDTO
     *
     * @param namespace namespace
     * @return PagexAddDTO
     */
    public PagexAddVO getPagexAddDTOFromCache(String namespace) {
        if (!jsContentMap.containsKey(namespace)) {
            throw new ParamException("namespace不存在:" + namespace);
        }
        if (!pagexAddDtoCache.containsKey(namespace)) {
            try {
                pagexAddDtoCache.put(namespace, new PagexAddVO(jsContentMap.get(namespace)));
            } catch (NoSuchMethodException e) {
                LOG.error("找不到方法", e);
            } catch (ScriptException e) {
                LOG.error("方法执行错误", e);
            }
        }
        return pagexAddDtoCache.get(namespace);
    }


    public Map<String, PagexListSettVO> getPagexListSettDTOCache() {
        return pagexListSettDTOCache;
    }

    /**
     * 根据namespace获取 PagexListSettDTO
     *
     * @param namespace namespace
     * @return PagexListSettDTO
     */
    public PagexListSettVO getPagexListSettDTOFromCache(String namespace) {
        if (!jsContentMap.containsKey(namespace)) {
            throw new ParamException("namespace不存在:" + namespace);
        }
        if (!pagexListSettDTOCache.containsKey(namespace)) {
            try {
                pagexListSettDTOCache.put(namespace, new PagexListSettVO(jsContentMap.get(namespace)));
            } catch (NoSuchMethodException e) {
                LOG.error("找不到方法", e);
            } catch (ScriptException e) {
                LOG.error("方法执行错误", e);
            }
        }
        return pagexListSettDTOCache.get(namespace);
    }

    public Map<String, PageXFrontVO> getPageXFrontDTOFromCache() {
        return pageXFrontDTOCache;
    }

    /**
     * 根据namespace获取 PageXFrontDTO
     *
     * @param namespace namespace
     * @return PagexListSettDTO
     */
    public PageXFrontVO getPageXFrontDTOFromCache(String namespace) {
        if (!jsContentMap.containsKey(namespace)) {
            throw new ParamException("namespace不存在:" + namespace);
        }
        if (!pageXFrontDTOCache.containsKey(namespace)) {
            try {
                pageXFrontDTOCache.put(namespace, new PageXFrontVO(jsContentMap.get(namespace)));
            } catch (NoSuchMethodException e) {
                LOG.error("找不到方法", e);
            } catch (ScriptException e) {
                LOG.error("方法执行错误", e);
            }
        }
        return pageXFrontDTOCache.get(namespace);
    }

    /**
     * 根据namespace获取 PageXFrontDTO
     *
     * @param namespace namespace
     * @return PagexListSettDTO
     */
    public PageXTreeVO getPageXTreeDTOFromCache(String namespace) {
        if (!jsContentMap.containsKey(namespace)) {
            throw new ParamException("namespace不存在:" + namespace);
        }
        if (!pageXTreeDTOCache.containsKey(namespace)) {
            try {
                pageXTreeDTOCache.put(namespace, new PageXTreeVO(jsContentMap.get(namespace)));
            } catch (NoSuchMethodException e) {
                LOG.error("找不到方法", e);
            } catch (ScriptException e) {
                LOG.error("方法执行错误", e);
            }
        }
        return pageXTreeDTOCache.get(namespace);
    }

    /**
     * 注册js刷新事件监听
     *
     * @param jsRefreshListener 监听
     */
    public void registerJsRefreshListener(JsRefreshListener jsRefreshListener) {
        jsRefreshListenerList.add(jsRefreshListener);
    }

    public Map<String, String> getAddPageExtendsHtmlPathMap() {
        return addPageExtendsHtmlPathMap;
    }

    public void setAddPageExtendsHtmlPathMap(Map<String, String> addPageExtendsHtmlPathMap) {
        this.addPageExtendsHtmlPathMap = addPageExtendsHtmlPathMap;
    }

    /**
     * 获取所有的namespace
     *
     * @return 所有的namespace
     */
    public Set<String> getAllJsNamespace() {
        return this.jsContentMap.keySet();
    }
}
