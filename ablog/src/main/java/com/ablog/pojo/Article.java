package com.ablog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @Column(name = "ARTICLE_CREATETIME")
    Date createTime;
    @Column(name = "ARTICLE_MODIFYTIME")
    Date modifyTime;
    @Column(name = "ARTICLE_STATE")
    int state;  //-1/删除;0/草稿;1/发布
    @Column(name = "ARTICLE_HASCOMMENTS")
    int hasComments;    //0/否;1/是
    @ManyToMany
    @JoinTable(name = "ARTICLE_CATEGORY",joinColumns = @JoinColumn(name = "ARTICLE_ID"),inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    //1、关系维护端，负责多对多关系的绑定和解除
    //2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(Article)
    //3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(Category)
    //4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为ARTICLE_CATEGORY
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即ARTICLE_ID
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即CATEGORY_ID
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
    private List<Category> categoryList;
    @OneToMany(mappedBy = "article",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="author"中的author是Article中的author属性
    private List<Comment> comments;

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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", doc='" + doc + '\'' +
                ", html='" + html + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", state=" + state +
                ", hasConments=" + hasComments +
                '}';
    }

}
