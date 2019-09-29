package top.wanda92632.blog.util;

import org.springframework.web.servlet.ModelAndView;
import top.wanda92632.blog.entity.Article;
import top.wanda92632.blog.entity.Comment;
import top.wanda92632.blog.service.ArticleService;
import top.wanda92632.blog.service.CommentService;

import java.util.List;

/**
 * @author ZhiFei
 */
public class ArticleUtil {

    /**
     * 分类获取文章(标题)
     * @param mav
     * @param articleService
     * @param title
     * @param page
     */
    public static void getArticleTitle(ModelAndView mav,ArticleService articleService,String title,String page){
        //当前页面号,若为空则默认为第一页
        if(page==null||page==""){
            page="1";
        }
        //文章总数
        int articleCount=articleService.getArticleByTitleCount(title);
        //可分为多少页
        int pageCount=articleCount/9;
        pageCount=articleCount%9==0?pageCount:pageCount+1;
        //页数
        int num = Integer.valueOf(page);
        //类别文章
        List<Article> articleList = articleService.queryArticleByTitle(title,(num-1)*9);
        //总页面数
        mav.addObject("pageCount",pageCount);
        //当前页面号
        mav.addObject("page", page);
        //文章列表
        mav.addObject("articleList", articleList);
    }

    /**
     * 分类获取文章(类别)
     * @param mav
     * @param articleService
     * @param categoryName
     * @param page
     */
    public static void getArticleCategoryName(ModelAndView mav,ArticleService articleService,String categoryName,String page){
        //当前页面号,若为空则默认为第一页
        if(page==null||page==""){
            page="1";
        }
        //文章总数
        int articleCount=articleService.getArticleByTitleCount(categoryName);
        //可分为多少页
        int pageCount=articleCount/9;
        pageCount=articleCount%9==0?pageCount:pageCount+1;
        //页数
        int num = Integer.valueOf(page);
        //类别文章
        List<Article> articleList = articleService.queryArticleByCategory(categoryName,(num-1)*9);
        //总页面数
        mav.addObject("pageCount",pageCount);
        //当前页面号
        mav.addObject("page", page);
        //文章列表
        mav.addObject("articleList", articleList);
    }


    /**
     * 获取主页文章获取
     * @param mav
     * @param articleService
     * @param page
     * @return
     */
    public static void getHomeArticle(ModelAndView mav,ArticleService articleService,String page){
        //当前页面号,若为空则默认为第一页
        if(page==null||page==""){
            page="1";
        }
        //文章总数
        int articleCount=articleService.queryArticleSum();
        //可分为多少页
        int pageCount=articleCount/9;
        pageCount=articleCount%9==0?pageCount:pageCount+1;
        //页数
        int num = Integer.valueOf(page);
        Article article=articleService.getArticle(6);
        List<Article> articleList = articleService.getHomeArticle((num-1)*9);
        articleList.add(0,article);
        //总页面数
        mav.addObject("pageCount",pageCount);
        //当前页面号
        mav.addObject("page", page);
        //文章列表
        mav.addObject("articleList", articleList);
    }

}
