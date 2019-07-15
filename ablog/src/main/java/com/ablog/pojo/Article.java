package com.ablog.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ARTICLE")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLE_ID")
    int id;
    @Column(name = "ARTICLE_TITLE")
    String title;
    @Column(name = "ARTICLE_COVER")
    String cover;
    @Column(name = "ARTICLE_DOC")
    String doc;
    @Column(name = "ARTICLE_HTML")
    String html;
    @Column(name = "ARTICLE_TAG")
    String tag;
    @Column(name = "ARTICLE_CATEGORY")
    String category;
    @Column(name = "ARTICLE_CREATETIME")
    Date createTime;
    @Column(name = "ARTICLE_MODIFYTIME")
    Date modifyTime;
    @Column(name = "ARTICLE_STATE")
    int state;  //-1/删除;0/草稿;1/发布
    @Column(name = "ARTICLE_HASCOMMENTS")
    int hasComments;    //0/否;1/是

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getHasComments() {
        return hasComments;
    }

    public void setHasComments(int hasComments) {
        this.hasComments = hasComments;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", doc='" + doc + '\'' +
                ", html='" + html + '\'' +
                ", tag='" + tag + '\'' +
                ", category='" + category + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", state=" + state +
                ", hasConments=" + hasComments +
                '}';
    }

}
