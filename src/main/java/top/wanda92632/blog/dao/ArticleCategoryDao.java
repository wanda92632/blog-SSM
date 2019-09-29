package top.wanda92632.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.wanda92632.blog.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryDao {

    /**
     * 添加文章&文章类别关系
     */
    void setArticleCategory(@Param("articleCategory")ArticleCategory articleCategory);

    /**
     * 删除关系-文章Id
     * @param articleId
     */
    void deleteRelationByArticleId(@Param("articleId")Integer articleId);

}
