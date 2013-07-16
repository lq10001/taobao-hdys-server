package com.ly.comm;

import org.nutz.dao.pager.Pager;

public class Page extends Pager{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 232222222222333L;	
	
	private int pageNum;
	
	private int numPerPage;
	
	
	public int getPageNum() {
		return super.getPageNumber();
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		super.setPageNumber(pageNum);
	}

	public int getNumPerPage() {
		return super.getPageSize();
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
		super.setPageSize(numPerPage);
	}

	/**
	 * 路径
	 */
	private String url;

	/**
	 * 路径参数
	 */
	private String params="";


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params == null?"":params;
	}
	
	public String getPageStr(){
		
		//共 35 项，第 1/2 页  下一页 末页
		//url += url+"?rows="+rows;
		StringBuffer sb = new StringBuffer();
		sb.append("共 "+this.getRecordCount()+" 项, ");
		sb.append("第 "+this.getPageNumber()+"/"+this.getPageCount()+" 页  ");
		
		if(getPageNumber() == 1 && getPageCount() > getPageNumber()){
			sb.append("<a href='"+url+"?pageNumber="+(this.getPageNumber()+1)+params+"' class='pageUp'>下一页</a>");
			sb.append("<a href='"+url+"?pageNumber="+this.getPageNumber()+params+"' class='pageUp'> 末页</a>");
		}else if(getPageCount() == getPageNumber() && getPageNumber() > 1 ){
			sb.append("<a href='"+url+"?pageNumber=1"+params+"' class='pageUp'>首页 </a>");
			sb.append("<a href='"+url+"?pageNumber="+(this.getPageNumber()-1)+params+"' class='pageUp'> 上一页</a>");
		}else if(getPageNumber() > 1 && getPageCount() > getPageNumber()){
			sb.append("<a href='"+url+"?pageNumber=1"+params+"' class='pageUp'>首页</a>");
			sb.append("<a href='"+url+"?pageNumber="+(this.getPageNumber()-1)+params+"' class='pageUp'> 上一页</a>");
			sb.append("<a href='"+url+"?pageNumber="+(this.getPageNumber()+1)+params+"' class='pageUp'> 下一页</a>");
			sb.append("<a href='"+url+"?pageNumber="+this.getPageNumber()+params+"' class='pageUp'> 末页</a>");
		}
		return sb.toString();
	}

}
