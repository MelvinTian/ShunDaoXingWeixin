package com.sdx.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/****
 * 树的封装
 * @author zz
 *
 */
public class Tree implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//业务ID
	private String parentId;//父节点ID
	private String name;//节点名称
	private Boolean open;//是否展开当前节点,true：打开，false：关闭
	private Boolean isParent;//当前节点是否是父节点
	private String iconOpen;//打开时节点图标
	private String iconClose;//关闭时节点图标
	private String icon;//叶子节点图标
	private String level;//层级
	private Boolean checked;//复选框选项，默认false;true:当前节点选中，false:当前节点未选中
	private Map<String,Object> treeExtend = new HashMap<String,Object>();//扩展属性
	private List<Tree> children = new ArrayList<Tree>();//子节点数据列表
	private String css;
	private String path;
	private int isMenu;
	private int sortId;
	private String flowkey;
	private int dataType;
    private Boolean nocheck;//是否显示复选框，true显示
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public String getIconOpen() {
		return iconOpen;
	}
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public String getIconClose() {
		return iconClose;
	}
	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Map<String, Object> getTreeExtend() {
		return treeExtend;
	}
	public void setTreeExtend(Map<String, Object> treeExtend) {
		this.treeExtend = treeExtend;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public int getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}
	/**
	 * @return the sortId
	 */
	public int getSortId() {
		return sortId;
	}
	/**
	 * @param sortId the sortId to set
	 */
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

    public Boolean getNocheck() {
        return nocheck;
    }

    public void setNocheck(Boolean nocheck) {
        this.nocheck = nocheck;
    }
    /**
     * @return the flowKey
     */
    public String getFlowkey()
    {
        return flowkey;
    }
    /**
     * @param flowKey the flowKey to set
     */
    public void setFlowkey(String flowkey)
    {
        this.flowkey = flowkey;
    }
	/**
	 * @return the dataType
	 */
	public int getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
}
