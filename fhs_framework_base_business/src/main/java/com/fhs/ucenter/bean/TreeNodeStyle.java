package com.fhs.ucenter.bean;

/**
 * ZTree树节点样式Model
 */
import java.io.Serializable;

public class TreeNodeStyle implements Serializable {

	private static final long serialVersionUID = 6708467867386985283L;
	
	private String color = null;
	
	public TreeNodeStyle() {
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
