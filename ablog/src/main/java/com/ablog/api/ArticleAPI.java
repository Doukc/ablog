package com.ablog.api;

import com.ablog.pojo.Article;
import com.ablog.pojo.Category;
import com.ablog.result.Result;
import com.ablog.result.ResultFactory;
import com.ablog.service.ArticleService;
import com.ablog.service.LogService;
import com.ablog.utils.IPUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ArticleAPI {

    private static final Logger loggger = LoggerFactory.getLogger(ArticleAPI.class);

    @Autowired
    ArticleService articleService;
    @Autowired
    LogService logService;

    @PostMapping("/api/article")
    public Article article(@RequestBody Article article){

        return articleService.findArticleById(article.getId());

    }

    @PostMapping("/api/article/new/{value}/{tagValue}")
    public Result newArticle(HttpServletRequest request,@RequestBody Article article, @PathVariable("value") String[] value, @PathVariable("tagValue") String[] tagValue ){

        Article newArticle = articleService.newArticle(article,value,tagValue);
        if (newArticle != null){
            logService.log(IPUtil.getClientAddress(request),"文章管理","新增文章");
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("添加失败");

    }

    @PostMapping("/api/article/modify/{value}/{tagValue}")
    public Result modifyArticle(HttpServletRequest request,@RequestBody Article article,@PathVariable("value") String[] value,@PathVariable("tagValue") String[] tagValue){

        Article newArticle = articleService.newArticle(article,value,tagValue);
        if (newArticle != null){
            logService.log(IPUtil.getClientAddress(request),"文章管理","修改文章");
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("添加失败");

    }

    @PostMapping("/api/article/articles/{currentPage}/{pageSize}")
    public Page<Article> articleList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){

        Pageable pageable = new PageRequest(currentPage - 1,pageSize,Sort.Direction.DESC,"createTime");
        Page<Article> articlePage = articleService.articles(pageable);
        return articlePage;

    }

    @PostMapping("/api/article/list/{currentPage}/{pageSize}/{categoryId}")
    public Page<Article> articleList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize,@PathVariable("categoryId") int categoryId, @RequestBody Article article){

        Category category = new Category();
        if (categoryId != 0){
            category.setId(categoryId);
        }
        Pageable pageable = new PageRequest(currentPage - 1,pageSize,Sort.Direction.DESC,"createTime");
        Page<Article> articlePage = articleService.articleList(article,category,pageable);
        return articlePage;

    }

    @GetMapping("/api/article/delete/{id}")
    public void delete(HttpServletRequest request,@PathVariable("id") int id){

        articleService.delete(id);
        logService.log(IPUtil.getClientAddress(request),"文章管理","删除文章");

    }

    @PostMapping("/api/article/getArticlesByCid")
    public List<Article> getArticlesByCid(@RequestBody Category category){

        return articleService.getArticlesByCid(category);

    }

    @GetMapping("/api/article/allArticle")
    public List<Article> allArticle(){

        return  articleService.allArticle();

    }

}
