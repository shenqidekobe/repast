package com.yiyou.repast.platform.tools;


import org.springframework.data.domain.Page;

import repast.yiyou.common.util.DataGrid;

/**
 * jpa分页对象转换成DataGrid
 * */
public class PageConvertDataGrid<T> {
	
	private DataGrid<T> data;
	private Page<T> pages;
	
	public PageConvertDataGrid(Bulid<T> bulid){
		this.pages=bulid.pages;
		this.data=this.convert();
	}
	
	public static class Bulid<T>{
		private Page<T> pages;
		public Bulid<T> page(Page<T> page) {
			pages=page;
			return this;
		}
		public PageConvertDataGrid<T> build() {
            return new PageConvertDataGrid<T>(this);
        }
	}
	
	private DataGrid<T> convert(){
		DataGrid<T> data=new DataGrid<T>();
		data.setRecords(pages.getContent());
		data.setPageCount(pages.getTotalPages());
		data.setRowCount(pages.getTotalElements());
		return data;
	}

	public DataGrid<T> getData() {
		return data;
	}
	
}
