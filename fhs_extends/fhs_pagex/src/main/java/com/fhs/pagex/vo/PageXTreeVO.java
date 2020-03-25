package com.fhs.pagex.vo;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.Data;

import javax.script.ScriptException;
import java.util.Map;

/**
 * pagex TREE配置 DTO
 */
@Data
public class PageXTreeVO extends  PagexBaseVO {

    /**
     * tree配置对象
     */
    private ScriptObjectMirror treeObject;

    /**
     * key 不同的类型，value 对应的url
     * 比如部门管理tree他可能会用到用户管理上面
     * 就可以给map.put user->${basePath}/user_list.jsp?deptId=xx
     */
    private Map<String,Object>  listUrlSettMap;

    /**
     * fid
     */
    private Map<String,Object>  keySettMap;


    /**
     * 解析js 返回对象
     * @param js js
     * @throws NoSuchMethodException 如果调用某些方法找不到
     * @throws ScriptException 脚本本身有问题
     */
    public PageXTreeVO(String js) throws NoSuchMethodException, ScriptException {
        super.initScriptEngine(js);
        treeObject = (ScriptObjectMirror) scriptEngine.get("tree");
        this.initModelConfig();
        initTree();
    }

    /**
     * 初始化前段接口
     * @throws NoSuchMethodException
     * @throws ScriptException
     */
    public void initTree() throws NoSuchMethodException, ScriptException {
        listUrlSettMap = super.getMap("listUrlSett",treeObject);
        keySettMap = super.getMap("key",treeObject);
    }

    @Override
    Object getOtherFunctionJsObject() {
        // 不需要，所以返回null
        return null;
    }

}
