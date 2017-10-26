package com.wjb.model;

import java.util.Date;

public class Menu {
    private Integer id;

    private Integer parentId;

    private String menuNm;

    private String mapping;

    private Long rankSort;

    private String pictureUrl;

    private Integer dispIndex;

    private String dispKbn;

    private String remarks;

    private Integer state;

    private String creater;

    private Long createrId;

    private String operator;

    private Long operatorId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm == null ? null : menuNm.trim();
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping == null ? null : mapping.trim();
    }

    public Long getRankSort() {
        return rankSort;
    }

    public void setRankSort(Long rankSort) {
        this.rankSort = rankSort;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public Integer getDispIndex() {
        return dispIndex;
    }

    public void setDispIndex(Integer dispIndex) {
        this.dispIndex = dispIndex;
    }

    public String getDispKbn() {
        return dispKbn;
    }

    public void setDispKbn(String dispKbn) {
        this.dispKbn = dispKbn == null ? null : dispKbn.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}