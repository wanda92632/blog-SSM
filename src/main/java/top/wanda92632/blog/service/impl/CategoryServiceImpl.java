package top.wanda92632.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wanda92632.blog.dao.CategoryDao;
import top.wanda92632.blog.entity.ArticleCategory;
import top.wanda92632.blog.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    /**
     * 过去全部文章
     *
     * @return
     */
    @Override
    public List<ArticleCategory> getAllCategory() {
        return categoryDao.getAllCategory();
    }
}
