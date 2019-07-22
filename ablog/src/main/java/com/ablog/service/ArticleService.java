package com.ablog.service;

import com.ablog.dao.ArticleDAO;
import com.ablog.dao.CategoryDAO;
import com.ablog.pojo.Article;
import com.ablog.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    CategoryDAO categoryDAO;

    public Article newArticle(Article article, String[] value, String[] tagValue){

        List<Category> categories = new ArrayList<Category>();
        Category category = null;
        for (String cid :
                value) {
            category = categoryDAO.getOne(Integer.valueOf(cid));
            categories.add(category);
        }
        for (String cid :
                tagValue) {
            if (!cid.equals("0")) {
                category = categoryDAO.getOne(Integer.valueOf(cid));
                categories.add(category);
            }else {
                continue;
            }
        }
        article.setCategoryList(categories);
        return articleDAO.save(article);

    }

    public Article modifyArticle(Article article, String[] value, String[] tagValue){

        List<Category> categories = new ArrayList<Category>();
        Category category = null;
        for (String cid :
                value) {
            category = categoryDAO.getOne(Integer.valueOf(cid));
            categories.add(category);
        }
        for (String cid :
                tagValue) {
            if (!cid.equals("0")) {
                category = categoryDAO.getOne(Integer.valueOf(cid));
                categories.add(category);
            }else {
                continue;
            }
        }
        article.setCategoryList(categories);
        return articleDAO.saveAndFlush(article);

    }

    public Page<Article> articles(Pageable pageable) {

        return articleDAO.findAll(pageable);

    }

    public Page<Article> articleList(Article article,Category category,Pageable pageable){

        Specification<Article> specification = new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();
                Join<Article,Category> articleCategoryJoin = root.join("categoryList",JoinType.LEFT);
                if (article.getTitle() != null && !article.getTitle().equals("")){
                    System.out.println(1);
                    predicates.add(criteriaBuilder.like(root.get("title").as(String.class),"%" + article.getTitle() + "%"));
                }
                if (category.getId() != 0){
                    System.out.println(2);
                    predicates.add(criteriaBuilder.equal(articleCategoryJoin.get("id").as(Integer.class),category.getId()));
                }
                if (article.getState() != 100){
                    System.out.println(3);
                    predicates.add(criteriaBuilder.equal(root.get("state").as(Integer.class),article.getState()));
                }
                predicates.add(criteriaBuilder.equal(articleCategoryJoin.get("categoryType").as(Integer.class),category.getCategoryType()));
                System.out.println("list>>>" + predicates.size());
                Predicate[] predicates_ = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(predicates_));

            }
        };
        return  articleDAO.findAll(specification,pageable);

    }

    public Article findArticleById(int id) {

        return articleDAO.findById(id).get();

    }

    public void delete(int id) {

        articleDAO.deleteById(id);

    }

    public List<Article> getArticlesByCid(Category category) {

        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        List<Category> categoryList = new ArrayList<Category>();
        categoryList.add(category);
        return articleDAO.findArticlesByCategoryList(categoryList,sort);

    }
}
