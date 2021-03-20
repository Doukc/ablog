package com.ablog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private int id;
    @Column(name = "COMMENT_COMMENTATORNAME")
    private String commentatorName;
    @Column(name = "COMMENT_COMMENTATOREMAIL")
    private String commentatorEmail;
    @Column(name = "COMMENT_COMMENTATORWEBSITE")
    private String commentatorWebsite;
    @Column(name = "COMMENT_CONTENT")
    private String content;
    @Column(name = "COMMENT_TIME")
    private Date time;
    @Column(name = "COMMENT_STATE")
    private int state;
    @Column(name = "COMMENT_PARENTID")
    private int parentId;
    @Column(name = "COMMENT_COMMENTATORIP")
    private String commentatorIP;
    @JsonIgnore
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="ARTICLE_ID")//设置在article表中的关联字段(外键)
    private Article article;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentatorName() {
        return commentatorName;
    }

    public void setCommentatorName(String commentatorName) {
        this.commentatorName = commentatorName;
    }

    public String getCommentatorEmail() {
        return commentatorEmail;
    }

    public void setCommentatorEmail(String commentatorEmail) {
        this.commentatorEmail = commentatorEmail;
    }

    public String getCommentatorWebsite() {
        return commentatorWebsite;
    }

    public void setCommentatorWebsite(String commentatorWebsite) {
        this.commentatorWebsite = commentatorWebsite;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCommentatorIP() {
        return commentatorIP;
    }

    public void setCommentatorIP(String commentatorIP) {
        this.commentatorIP = commentatorIP;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentatorName='" + commentatorName + '\'' +
                ", commentatorEmail='" + commentatorEmail + '\'' +
                ", commentatorWebsite='" + commentatorWebsite + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", state=" + state +
                ", parentId=" + parentId +
                ", article=" + article.toString() +
                '}';
    }

}
