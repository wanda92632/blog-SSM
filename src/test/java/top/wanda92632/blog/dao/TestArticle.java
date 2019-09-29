package top.wanda92632.blog.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.wanda92632.blog.entity.Article;
import top.wanda92632.blog.service.BaseTest;

import java.util.List;

public class TestArticle extends BaseTest {
    @Autowired
    private ArticleDao articleDao;

    @Test
    public void getAllArticleByUserID(){
        List<Article> articleList = articleDao.getAllArticleByUserID(1);
        System.out.println(articleList.size());
        System.out.println(articleList.get(0).getUser().getUserNickname());
    }

}
