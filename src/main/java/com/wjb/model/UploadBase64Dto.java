package com.wjb.model;

public class UploadBase64Dto {
	private String data;
	private String fileName;
	private String isPreView;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIsPreView() {
		return isPreView;
	}

	public void setIsPreView(String isPreView) {
		this.isPreView = isPreView;
	}

}
