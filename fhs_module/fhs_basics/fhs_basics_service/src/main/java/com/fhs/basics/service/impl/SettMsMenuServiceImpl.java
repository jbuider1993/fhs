package com.fhs.basics.service.impl;

import com.fhs.basics.api.rpc.FeignSysMenuApiService;
import com.fhs.basics.constant.BasicsMenuConstant;
import com.fhs.basics.dox.SettMsMenuDO;
import com.fhs.basics.dox.SettMsMenuPermissionDO;
import com.fhs.basics.dox.UcenterMsTenantDO;
import com.fhs.basics.mapper.SettMsMenuMapper;
import com.fhs.basics.mapper.SettMsMenuPermissionMapper;
import com.fhs.basics.service.SettMsMenuService;
import com.fhs.basics.service.UcenterMsTenantService;
import com.fhs.basics.vo.SettMsMenuVO;
import com.fhs.basics.vo.TreeDataVO;
import com.fhs.basics.vo.TreeModelVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.ListUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.config.EConfig;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.result.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单业务实现类
 *
 * @author Administrator
 */
@Service
@DataSource("base_business")
public class SettMsMenuServiceImpl extends BaseServiceImpl<SettMsMenuVO, SettMsMenuDO> implements SettMsMenuService, FeignSysMenuApiService {

    @Autowired
    private SettMsMenuMapper mapper;

    @Autowired
    private SettMsMenuPermissionMapper adminMenuButtonMapper;

    @Autowired
    private UcenterMsTenantService tenantService;

    private int ZERO = 0;

    /**
     * 根据父Id获取树集合
     *
     * @return 返回树节点对象集合
     * @paramparentId 父Id
     */
    @Override
    public List<TreeModelVO> findMenuTrees(String parentId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<TreeModelVO> allMenu = mapper.findMenuTrees(paramMap);
        if (CheckUtils.isNullOrEmpty(parentId)) {
            return allMenu;
        }
        Set<String> parentIds = new HashSet<>();
        parentIds.add(parentId);
        List<TreeModelVO> result = new ArrayList<>();
        for (TreeModelVO temp : allMenu) {
            if (parentIds.contains(temp.getPid())) {
                result.add(temp);
                parentIds.add(temp.getId());
            }
        }
        return result;
    }

    /**
     * 获取菜单树json 数据
     */
    @Override
    public List<TreeDataVO> findMenuTreeToJson(Integer menuType, String groupCode) {

        TreeDataVO root = new TreeDataVO();
        root.setName("root");
        root.setId(BasicsMenuConstant.MENU_ROOT_STR);
        root.setMenuLevel(0);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        //saas模式过滤子系统
        if (ConverterUtils.toBoolean(EConfig.getOtherConfigPropertiesValue("isSaasModel"))) {
            String systemIds = tenantService.selectBean(UcenterMsTenantDO.builder().groupCode(groupCode).build()).getSystemIds();
            if (systemIds != null) {
                paramMap.put("systemIds", StringUtil.getStrToIn(systemIds.split(",")));
            }
        }
        List<TreeDataVO> list = mapper.findMenuTreesByMenuType(paramMap);
        list.add(0, root);
        List<SettMsMenuPermissionDO> buttons = adminMenuButtonMapper.findForListFromMap(new HashMap<>());
        // 标记哪些按钮id属于哪些菜单
        Map<String, Set<Integer>> buttonMap = new HashMap<>();
        Set<Integer> buttonTypeSet = null;
        for (SettMsMenuPermissionDO button : buttons) {
            if (buttonMap.containsKey(ConverterUtils.toString(button.getMenuId()))) {
                buttonMap.get(ConverterUtils.toString(button.getMenuId())).add(button.getPermissionType());
            } else {
                buttonTypeSet = new HashSet<>();
                buttonTypeSet.add(button.getPermissionType());
                buttonMap.put(ConverterUtils.toString(button.getMenuId()), buttonTypeSet);
            }
        }
        Map<String, TreeDataVO> treeDataMap = new LinkedHashMap<>();
        TreeDataVO father = null;
        List<TreeDataVO> result = new ArrayList<>();
        //遍历所有的菜单
        for (TreeDataVO tempTree : list) {
            father = treeDataMap.get(tempTree.getParentid());
            treeDataMap.put(tempTree.getId(), tempTree);
            if (father != null) {
                // 我的级别是爸爸的+1
                tempTree.setMenuLevel(father.getMenuLevel() + 1);
            }
            if (buttonMap.containsKey(tempTree.getId())) {
                tempTree.setAllitem(tempTree.getId());
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(1)) {
                setReaditem(treeDataMap, tempTree.getId());
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(2)) {
                setWriteitem(treeDataMap, tempTree.getId());
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(3)) {
                setEditeitem(treeDataMap, tempTree.getId());
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(4)) {
                setDelitem(treeDataMap, tempTree.getId());
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(5)) {
                tempTree.setElseitem(tempTree.getId());
            }
            //如果有爸爸则吧我设置为爸爸的儿子
            if (treeDataMap.containsKey(tempTree.getParentid())) {
                father.setIsdir(Constant.INT_TRUE);
                father.getChildren().add(tempTree);
            } else {
                //对root特殊处理
                if (BasicsMenuConstant.MENU_ROOT_STR.equals(tempTree.getId())) {
                    result.add(tempTree);
                    tempTree.setAllitem(tempTree.getId());
                    tempTree.setReaditem(tempTree.getId());
                    tempTree.setWriteitem(tempTree.getId());
                    tempTree.setEditeitem(tempTree.getId());
                    tempTree.setDelitem(tempTree.getId());
                    tempTree.setElseitem(tempTree.getId());
                }

            }
        }
        return result;
    }

    /**
     * 设置自己的祖宗十八代都有查询按钮
     *
     * @param treeDataMap 所有的对象集合 key id val object
     * @param fatherId    爸爸的id
     */
    private void setReaditem(Map<String, TreeDataVO> treeDataMap, String fatherId) {
        TreeDataVO tempTree = treeDataMap.get(fatherId);
        if (tempTree != null) {
            tempTree.setReaditem(tempTree.getId());
            tempTree.setAllitem(tempTree.getId());
            setReaditem(treeDataMap, tempTree.getParentid());
        }
    }

    /**
     * 设置自己的祖宗十八代都有添加按钮
     *
     * @param treeDataMap 所有的对象集合 key id val object
     * @param fatherId    爸爸的id
     */
    private void setWriteitem(Map<String, TreeDataVO> treeDataMap, String fatherId) {
        TreeDataVO tempTree = treeDataMap.get(fatherId);
        if (tempTree != null) {
            tempTree.setWriteitem(tempTree.getId());
            tempTree.setAllitem(tempTree.getId());
            setWriteitem(treeDataMap, tempTree.getParentid());
        }
    }

    /**
     * 设置自己的祖宗十八代都有编辑按钮
     *
     * @param treeDataMap 所有的对象集合 key id val object
     * @param fatherId    爸爸的id
     */
    private void setEditeitem(Map<String, TreeDataVO> treeDataMap, String fatherId) {
        TreeDataVO tempTree = treeDataMap.get(fatherId);
        if (tempTree != null) {
            tempTree.setEditeitem(tempTree.getId());
            tempTree.setAllitem(tempTree.getId());
            setEditeitem(treeDataMap, tempTree.getParentid());
        }
    }

    /**
     * 设置自己的祖宗十八代都有删除按钮
     *
     * @param treeDataMap 所有的对象集合 key id val object
     * @param fatherId    爸爸的id
     */
    private void setDelitem(Map<String, TreeDataVO> treeDataMap, String fatherId) {
        TreeDataVO tempTree = treeDataMap.get(fatherId);
        if (tempTree != null) {
            tempTree.setDelitem(tempTree.getId());
            tempTree.setAllitem(tempTree.getId());
            setDelitem(treeDataMap, tempTree.getParentid());
        }
    }

    /**
     * 获取菜单树json 数据
     */
    @Override
    public List<TreeDataVO> findMenuTreeToJson(String menuType) {
        List<TreeDataVO> list = mapper.findMenuTreesByMenuType(new HashMap<>());
        List<SettMsMenuPermissionDO> buttons = adminMenuButtonMapper.findForListFromMap(new HashMap<String, Object>());
        Map<Integer, Set<Integer>> buttonMap = new HashMap<>();
        Set<Integer> buttonTypeSet = null;
        for (SettMsMenuPermissionDO button : buttons) {
            if (buttonMap.containsKey(button.getMenuId())) {
                buttonMap.get(button.getMenuId()).add(button.getPermissionType());
            } else {
                buttonTypeSet = new HashSet<>();
                buttonTypeSet.add(button.getPermissionType());
                buttonMap.put(button.getMenuId(), buttonTypeSet);
            }
        }
        Map<String, TreeDataVO> treeDataMap = new LinkedHashMap<>();
        TreeDataVO father = null;
        List<TreeDataVO> result = new ArrayList<>();
        for (TreeDataVO tempTree : list) {
            father = treeDataMap.get(ConverterUtils.toInt(tempTree.getParentid()));
            if (buttonMap.containsKey(tempTree.getId())) {
                tempTree.setAllitem(tempTree.getId());
                if (father != null) {
                    father.setAllitem(father.getId());
                }
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(1)) {
                tempTree.setReaditem(tempTree.getId());
                if (father != null) {
                    father.setReaditem(father.getId());
                }
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(2)) {
                tempTree.setWriteitem(tempTree.getId());
                if (father != null) {
                    father.setWriteitem(father.getId());
                }
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(3)) {
                tempTree.setEditeitem(tempTree.getId());
                if (father != null) {
                    father.setEditeitem(father.getId());
                }
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(4)) {
                tempTree.setDelitem(tempTree.getId());
                if (father != null) {
                    father.setDelitem(father.getId());
                }
            }
            if (buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(5)) {
                tempTree.setElseitem(tempTree.getId());
                if (father != null) {
                    father.setEditeitem(father.getId());
                }
            }
            //如果有爸爸则吧我设置为爸爸的儿子
            if (treeDataMap.containsKey(ConverterUtils.toInt(tempTree.getParentid()))) {
                father.setIsdir(1);
                father.getChildren().add(tempTree);
            } else {
                result.add(tempTree);
            }
            tempTree.setAllitem(tempTree.getId());
            tempTree.setReaditem(tempTree.getId());
            tempTree.setWriteitem(tempTree.getId());
            tempTree.setEditeitem(tempTree.getId());
            tempTree.setDelitem(tempTree.getId());
            tempTree.setElseitem(tempTree.getId());
            treeDataMap.put(tempTree.getId(), tempTree);
        }
        return result;
    }

    /**
     * 获取菜单根节点
     *
     * @return
     */
    private List<TreeDataVO> findRootMap() {
        return mapper.findMenuRootTrees();
    }


    /***
     * 通过ID判断是否含有子节点
     *
     * @param parentId
     * @return
     */
    private boolean isExit(int parentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fatherMenuId", parentId);
        int count = mapper.isExit(map);
        if (count > ZERO) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过父id获取所有子集合数据
     *
     * @param parentId
     * @return 节点对象集合
     */
    private List<TreeDataVO> readMenuJosonById(int parentId, Integer menuType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fatherMenuId", parentId);
        map.put("menuType", menuType);
        return mapper.findMenuTreesByParentId(map);
    }

    @Override
    public boolean deleteButton(SettMsMenuDO menu) {
        return mapper.deleteButton(menu) > ZERO;
    }

    @Override
    public boolean delete(SettMsMenuDO bean) {
        deleteButton(bean);
        return super.delete(bean);
    }

    @Override
    public List<Map<String, Object>> searchServer() {
        return mapper.searchServer();
    }

    @Override
    public boolean updateDisable(SettMsMenuDO menu) {
        try {
            mapper.updateDisable(menu);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public int add(SettMsMenuDO bean) {
        int count = super.add(bean);
        this.updateDisable(bean);
        return count;
    }

    @Override
    public boolean update(SettMsMenuDO bean) {
        boolean result = super.update(bean);
        this.updateDisable(bean);
        return result;
    }

    @Override
    public HttpResult<List<SettMsMenuVO>> findIdAndNameAndNamespaceList() {
        return HttpResult.success(ListUtils.copyListToList(mapper.findIdAndNameAndNamespaceList(), SettMsMenuVO.class));
    }


}
