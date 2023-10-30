package com.spring.community.likeboard.likeBoardVO;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Component;

@Component
public class LikeBoardVO {
	
	private int no;
	private String title;
	private String context;
	private Data data;
	private String name;
	private int v_count;
	private int l_count;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getV_count() {
		return v_count;
	}
	public void setV_count(int v_count) {
		this.v_count = v_count;
	}
	public int getL_count() {
		return l_count;
	}
	public void setL_count(int l_count) {
		this.l_count = l_count;
	}
	
	
	
}
