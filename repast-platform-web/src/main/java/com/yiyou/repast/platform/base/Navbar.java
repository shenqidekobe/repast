package com.yiyou.repast.platform.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单信息对象
 * */
public class Navbar {
	
	private String title;//标题
	private String icon;//图标
	private boolean spread=false;//是否展开
	private List<Children> children=new ArrayList<Navbar.Children>();
	
	public static class Children{
		private String title;
		private String icon;
		private String href;
		
		
		public Children(String title, String icon, String href) {
			super();
			this.title = title;
			this.icon = icon;
			this.href = href;
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getHref() {
			return href;
		}
		public void setHref(String href) {
			this.href = href;
		}
	}

	
	public Navbar(String title, String icon, boolean spread) {
		super();
		this.title = title;
		this.icon = icon;
		this.spread = spread;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public List<Children> getChildren() {
		return children;
	}

	public void setChildren(List<Children> children) {
		this.children = children;
	}
	
}
