package com.fhs.ucenter.service.impl;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.ucenter.bean.SysMenu;
import com.fhs.ucenter.bean.SysMenuPermission;
import com.fhs.ucenter.bean.TreeData;
import com.fhs.ucenter.bean.TreeModel;
import com.fhs.ucenter.dao.SysMenuDAO;
import com.fhs.ucenter.dao.SysMenuPermissionDAO;
import com.fhs.ucenter.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 菜单业务实现类
 *
 * @author Administrator
 *
 */
@Service
@DataSource("base_business")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService
{

    @Autowired
    private SysMenuDAO dao;

    @Autowired
    private SysMenuPermissionDAO adminMenuButtonDAO;

    private int ZERO = 0;

    /**
     * 根据父Id获取树集合
     *
     * @paramparentId 父Id
     * @return 返回树节点对象集合
     */
    @Override
    public List<TreeModel> findMenuTrees(String parentId)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
		List<TreeModel> allMenu = dao.findMenuTrees(paramMap);
        if(CheckUtils.isNullOrEmpty(parentId))
		{
			return  allMenu;
		}
		Set<String> parentIds = new HashSet<>();
		parentIds.add(parentId);
		List<TreeModel> result = new ArrayList<>();
		for(TreeModel temp : allMenu)
		{
			if(parentIds.contains(temp.getPid()))
			{
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
    public List<TreeData> findMenuTreeToJson(Integer menuType)
    {
    	List<TreeData> list = dao.findMenuTreesByMenuType(menuType+"");
		TreeData root = new TreeData();
		root.setName("root");
		root.setId("0");
		root.setMenuLevel(0);
		list.add(0,root);
        List<SysMenuPermission> buttons = adminMenuButtonDAO.findForListFromMap(new HashMap<String,Object>());
        // 标记哪些按钮id属于哪些菜单
        Map<String,Set<Integer>> buttonMap = new HashMap<>();
        Set<Integer> buttonTypeSet = null;
        for(SysMenuPermission button : buttons)
        {
        	if(buttonMap.containsKey(ConverterUtils.toString(button.getMenuId())))
        	{
        		buttonMap.get(ConverterUtils.toString(button.getMenuId())).add(button.getPermissionType());
        	}
        	else
        	{
        		buttonTypeSet = new HashSet<>();
        		buttonTypeSet.add(button.getPermissionType());
        		buttonMap.put(ConverterUtils.toString(button.getMenuId()), buttonTypeSet);
        	}
        }
        Map<String,TreeData> treeDataMap = new LinkedHashMap<>();
        TreeData father = null;
        List<TreeData> result = new ArrayList<>();
        //遍历所有的菜单
        for(TreeData tempTree : list)
        {
        	father = treeDataMap.get(tempTree.getParentid());
			treeDataMap.put(tempTree.getId(), tempTree);
			if(tempTree.getName().equals("支付子系统"))
			{
				System.out.println("11");
			}
			if(father!=null)
			{
				// 我的级别是爸爸的+1
				tempTree.setMenuLevel(father.getMenuLevel()+1);
			}
        	if(buttonMap.containsKey(tempTree.getId()))
        	{
        		tempTree.setAllitem(tempTree.getId() );
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(1))
        	{
        		setReaditem(treeDataMap,tempTree.getId());
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(2))
        	{
        		setWriteitem(treeDataMap,tempTree.getId());
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(3))
        	{
        		setEditeitem(treeDataMap,tempTree.getId());
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(4))
        	{
        		setDelitem(treeDataMap,tempTree.getId());
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(5))
        	{
        		tempTree.setElseitem(tempTree.getId());
        	}
        	//如果有爸爸则吧我设置为爸爸的儿子
        	if(treeDataMap.containsKey(tempTree.getParentid()))
        	{
        		father.setIsdir(1);
        		father.getChildren().add(tempTree);
        	}
        	else
        	{
        		//对root特殊处理
        		if("0".equals(tempTree.getId()))
        		{
        			result.add(tempTree);
        			tempTree.setAllitem(tempTree.getId() );
        			tempTree.setReaditem(tempTree.getId() );
        			tempTree.setWriteitem(tempTree.getId() );
        			tempTree.setEditeitem(tempTree.getId() );
        			tempTree.setDelitem(tempTree.getId() );
        			tempTree.setElseitem(tempTree.getId() );
        		}

        	}
        }
        return result;
    }

	/**
	 * 设置自己的祖宗十八代都有查询按钮
	 * @param treeDataMap 所有的对象集合 key id val object
	 * @param fatherId 爸爸的id
	 */
	private void setReaditem(Map<String,TreeData> treeDataMap,String fatherId){
		TreeData tempTree = treeDataMap.get(fatherId);
		if(tempTree!=null)
		{
			tempTree.setReaditem(tempTree.getId());
			tempTree.setAllitem(tempTree.getId());
			setReaditem(treeDataMap,tempTree.getParentid());
		}
	}

	/**
	 * 设置自己的祖宗十八代都有添加按钮
	 * @param treeDataMap 所有的对象集合 key id val object
	 * @param fatherId 爸爸的id
	 */
	private void setWriteitem(Map<String,TreeData> treeDataMap,String fatherId){
		TreeData tempTree = treeDataMap.get(fatherId);
		if(tempTree!=null)
		{
			tempTree.setWriteitem(tempTree.getId());
			tempTree.setAllitem(tempTree.getId());
			setWriteitem(treeDataMap,tempTree.getParentid());
		}
	}

	/**
	 * 设置自己的祖宗十八代都有编辑按钮
	 * @param treeDataMap 所有的对象集合 key id val object
	 * @param fatherId 爸爸的id
	 */
	private void setEditeitem(Map<String,TreeData> treeDataMap,String fatherId){
		TreeData tempTree = treeDataMap.get(fatherId);
		if(tempTree!=null)
		{
			tempTree.setEditeitem(tempTree.getId());
			tempTree.setAllitem(tempTree.getId());
			setEditeitem(treeDataMap,tempTree.getParentid());
		}
	}

	/**
	 * 设置自己的祖宗十八代都有删除按钮
	 * @param treeDataMap 所有的对象集合 key id val object
	 * @param fatherId 爸爸的id
	 */
	private void setDelitem(Map<String,TreeData> treeDataMap,String fatherId){
		TreeData tempTree = treeDataMap.get(fatherId);
		if(tempTree!=null)
		{
			tempTree.setDelitem(tempTree.getId());
			tempTree.setAllitem(tempTree.getId());
			setDelitem(treeDataMap,tempTree.getParentid());
		}
	}

    /**
     * 获取菜单树json 数据
     */
    @Override
    public List<TreeData> findMenuTreeToJson(String menuType)
    {
        List<TreeData> list = dao.findMenuTreesByMenuType(menuType);
        List<SysMenuPermission> buttons = adminMenuButtonDAO.findForListFromMap(new HashMap<String,Object>());
        Map<Integer,Set<Integer>> buttonMap = new HashMap<>();
        Set<Integer> buttonTypeSet = null;
        for(SysMenuPermission button : buttons)
        {
        	if(buttonMap.containsKey(button.getMenuId()))
        	{
        		buttonMap.get(button.getMenuId()).add(button.getPermissionType());
        	}
        	else
        	{
        		buttonTypeSet = new HashSet<>();
        		buttonTypeSet.add(button.getPermissionType());
        		buttonMap.put(button.getMenuId(), buttonTypeSet);
        	}
        }
        Map<String,TreeData> treeDataMap = new LinkedHashMap<>();
        TreeData father = null;
        List<TreeData> result = new ArrayList<>();
        for(TreeData tempTree : list)
        {
        	father = treeDataMap.get(ConverterUtils.toInt(tempTree.getParentid()));
        	if(buttonMap.containsKey(tempTree.getId()))
        	{
        		tempTree.setAllitem(tempTree.getId() );
        		if(father!=null)
        		{
        			father.setAllitem(father.getId() );
        		}
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(1))
        	{
        		tempTree.setReaditem(tempTree.getId() );
        		if(father!=null)
        		{
        			father.setReaditem(father.getId() );
        		}
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(2))
        	{
        		tempTree.setWriteitem(tempTree.getId() );
        		if(father!=null)
        		{
        			father.setWriteitem(father.getId() );
        		}
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(3))
        	{
        		tempTree.setEditeitem(tempTree.getId() );
        		if(father!=null)
        		{
        			father.setEditeitem(father.getId() );
        		}
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(4))
        	{
        		tempTree.setDelitem(tempTree.getId() );
        		if(father!=null)
        		{
        			father.setDelitem(father.getId() );
        		}
        	}
        	if(buttonMap.containsKey(tempTree.getId()) && buttonMap.get(tempTree.getId()).contains(5))
        	{
        		tempTree.setElseitem(tempTree.getId() );
        		if(father!=null)
        		{
        			father.setEditeitem(father.getId() );
        		}
        	}
        	//如果有爸爸则吧我设置为爸爸的儿子
        	if(treeDataMap.containsKey(ConverterUtils.toInt(tempTree.getParentid())))
        	{
        		father.setIsdir(1);
        		father.getChildren().add(tempTree);
        	}
        	else
        	{
        			result.add(tempTree);
        	}
            tempTree.setAllitem(tempTree.getId() );
            tempTree.setReaditem(tempTree.getId() );
            tempTree.setWriteitem(tempTree.getId() );
            tempTree.setEditeitem(tempTree.getId() );
            tempTree.setDelitem(tempTree.getId() );
            tempTree.setElseitem(tempTree.getId() );
            treeDataMap.put(tempTree.getId(), tempTree);
        }
        return result;
    }

    /**
     * 获取菜单根节点
     *
     * @return
     */
    private List<TreeData> findRootMap()
    {
        return dao.findMenuRootTrees();
    }



    /***
     * 通过ID判断是否含有子节点
     *
     * @param parentId
     * @return
     */
    private boolean isExit(int parentId)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fatherMenuId", parentId);
        int count = dao.isExit(map);
        if (count > ZERO)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 通过父id获取所有子集合数据
     *
     * @param parentId
     * @return 节点对象集合
     */
    private List<TreeData> readMenuJosonById(int parentId, Integer menuType)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fatherMenuId", parentId);
        map.put("menuType", menuType);
        return dao.findMenuTreesByParentId(map);
    }

    @Override
    public boolean deleteButton(SysMenu menu)
    {
        return dao.deleteButton(menu) > ZERO;
    }

    @Override
    public boolean delete(SysMenu bean)
    {
        deleteButton(bean);
        return super.delete(bean);
    }

    @Override
    public List<Map<String, Object>> searchServer()
    {
        return dao.searchServer();
    }

    @Override
    public boolean updateDisable(SysMenu menu)
    {
        try
        {
            dao.updateDisable(menu);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int add(SysMenu bean)
    {
        int count = super.add(bean);
        this.updateDisable(bean);
        return count;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean update(SysMenu bean)
    {
        boolean result = super.update(bean);
        this.updateDisable(bean);
        return result;
    }

    @Override
    public List<SysMenu> findIdAndNameAndNamespaceList()
    {
        return dao.findIdAndNameAndNamespaceList();
    }



}
