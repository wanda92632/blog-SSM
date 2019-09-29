package top.wanda92632.blog.service;

import top.wanda92632.blog.entity.ArticleCategory;

import java.util.List;

public interface CategoryService {
    /**
     * 过去全部文章
     * @return
     */
    public List<ArticleCategory> getAllCategory();
}
