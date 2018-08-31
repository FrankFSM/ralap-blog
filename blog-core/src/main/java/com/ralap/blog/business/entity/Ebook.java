package com.ralap.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ralap.blog.persistence.beans.BizEbook;
import java.util.Date;

/**
 * @author: ralap
 * @date: created at 2018/8/31 15:47
 */
public class Ebook {

    private BizEbook bizEbook;

    public Ebook() {
        bizEbook = new BizEbook();
    }

    public Ebook(BizEbook bizEbook) {
        this.bizEbook = bizEbook;
    }

    public BizEbook getBizEbook() {
        return bizEbook;
    }

    public Long getId() {
        return this.bizEbook.getId();
    }

    public void setId(Long id) {
        this.bizEbook.setId(id);
    }

    public String getTitle() {
        return this.bizEbook.getTitle();
    }

    public void setTitle(String title) {
        this.bizEbook.setTitle(title);
    }

    public String getAuthor() {
        return this.bizEbook.getAuthor();
    }

    public void setAuthor(String author) {
        this.bizEbook.setAuthor(author);
    }

    public String getCoverImage() {
        return this.bizEbook.getCoverImage();
    }

    public void setCoverImage(String coverImage) {
        this.bizEbook.setCoverImage(coverImage);
    }

    public String getPath() {
        return this.bizEbook.getPath();
    }

    public void setPath(String path) {
        this.bizEbook.setPath(path);
    }

    public Boolean getStatus() {
        return this.bizEbook.getStatus();
    }

    public void setStatus(Boolean status) {
        this.bizEbook.setStatus(status);
    }

    public String getDescription() {
        return this.bizEbook.getDescription();
    }

    public void setDescription(String description) {
        this.bizEbook.setDescription(description);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.bizEbook.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizEbook.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.bizEbook.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizEbook.setUpdateTime(updateTime);
    }

}
