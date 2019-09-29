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
public class SidebarArticleUtil {

    public static void getSidebarArticle(ModelAndView mav,ArticleService articleService,CommentService commentService){
        //最热文章
        List<Article> popularArticle=articleService.queryPopularArticle();
        mav.addObject("popularArticle", popularArticle);
        //最新文章
        List<Article> latestArticle = articleService.queryLatestArticle();
        mav.addObject("latestArticle", latestArticle);
        //最新评论
        List<Comment> latestComment=commentService.queryLatestComment();
        mav.addObject("latestComment", latestComment);
    }
}
