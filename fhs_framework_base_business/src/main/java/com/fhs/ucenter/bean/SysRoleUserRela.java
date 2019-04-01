package com.fhs.ucenter.bean;
public class SysRoleUserRela 
{
 	/** 
	 * 角色id
	 */
	private int roleId;
 	/** 
	 * 用户id
	 */
	private int userId;
 
 	/** 
	 * 给角色id赋值
	 */
	public void setRoleId(int roleId)
	{
		this.roleId=roleId;
	}
 	/** 
	 * 获取角色id
	 */
	public int getRoleId()
	{
		return roleId;
	}
 	/** 
	 * 给用户id赋值
	 */
	public void setUserId(int userId)
	{
		this.userId=userId;
	}
 	/** 
	 * 获取用户id
	 */
	public int getUserId()
	{
		return userId;
	}
 	
}

