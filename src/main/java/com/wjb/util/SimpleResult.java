package com.wjb.util;

public class SimpleResult {
	private Integer errno = 0;
	private String txt = "";
	private Object data;
	
	public SimpleResult() {
		
	}
	public SimpleResult(Object data){
		this.data=data;
	}

	public SimpleResult(Integer errno, String txt) {
		this.errno = errno;
		this.txt = txt;
	}
	public SimpleResult(Integer errno, String txt, Object data) {
		this.errno = errno;
		this.txt = txt;
		this.data = data;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
