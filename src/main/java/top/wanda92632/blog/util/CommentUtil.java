package top.wanda92632.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import top.wanda92632.blog.entity.Article;
import top.wanda92632.blog.entity.Comment;
import top.wanda92632.blog.service.CommentService;

import java.util.List;

/**
 * @author ZhiFei
 */
public class CommentUtil {

    @Autowired
    CommentService commentService;

    /**
     * 获取文章评论
     * @param articleId
     * @param page
     */
    public static void getArticleComment(ModelAndView mav,Integer articleId, String page,CommentService commentService){
        //当前页面号,若为空则默认为第一页
        if(page==null||page==""){
            page="1";
        }
        //评论总数
        int commentCount=commentService.getCommentByArticleId(articleId);
        //可分为多少页
        int pageCount=commentCount/9;
        pageCount=commentCount%9==0?pageCount:pageCount+1;
        //页数
        int num = Integer.valueOf(page);
        List<Comment> commentList = commentService.getComment(articleId,(num-1)*9);
        //总页面数
        mav.addObject("pageCount",pageCount);
        //当前页面
        mav.addObject("page", page);
        //评论列表
        mav.addObject("commentList", commentList);
    }
}
