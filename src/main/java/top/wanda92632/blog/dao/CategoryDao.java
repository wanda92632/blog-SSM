package top.wanda92632.blog.dao;

import top.wanda92632.blog.entity.ArticleCategory;

import java.util.List;

public interface CategoryDao {

    /**
     * 获取所有类别
     */
    List<ArticleCategory> getAllCategory();
}
