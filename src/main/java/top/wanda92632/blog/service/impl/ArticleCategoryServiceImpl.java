package top.wanda92632.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wanda92632.blog.dao.ArticleCategoryDao;
import top.wanda92632.blog.entity.ArticleCategory;
import top.wanda92632.blog.service.ArticleCategoryService;

/**
 * @author ZhiFei
 */
@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {
    @Autowired
    private ArticleCategoryDao articleCategoryDao;
    /**
     * 建立文章文章类型关系
     *
     * @param articleCategory
     */
    @Override
    public void setArticleCategory(ArticleCategory articleCategory) {
        articleCategoryDao.setArticleCategory(articleCategory);
    }

    /**
     * 删除关系-文章Id
     *
     * @param articleId
     */
    @Override
    public void deleteRelationByArticleId(Integer articleId) {
        articleCategoryDao.deleteRelationByArticleId(articleId);
    }


}
