package com.ablog.dao;

import com.ablog.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDAO extends JpaRepository<Category,Integer> {

    @Query(value = "select a.CATEGORY_ID,a.CATEGORY_NAME,a.CATEGORY_TYPE,count(b.ARTICLE_ID) from CATEGORY a left join ARTICLE_CATEGORY b on a.CATEGORY_ID = b.CATEGORY_ID group by a.CATEGORY_ID",nativeQuery = true)
    List<Object[]> selectCategories();

}
