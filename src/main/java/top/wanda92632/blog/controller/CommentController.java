package top.wanda92632.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.wanda92632.blog.entity.Article;
import top.wanda92632.blog.entity.Comment;
import top.wanda92632.blog.entity.User;
import top.wanda92632.blog.service.ArticleService;
import top.wanda92632.blog.service.CommentService;
import top.wanda92632.blog.util.CommentUtil;

import java.time.LocalDate;


/**
 * @author ZhiFei
 */
@Controller
public class CommentController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    /**
     * 提交评论
     * @param comment
     * @return
     */
    @RequiresAuthentication
    @RequestMapping("/setComment")
    public ModelAndView setComment(Comment comment){
        ModelAndView mav=new ModelAndView("home/page/article");
        //获取Session中的用户信息
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");

        comment.setUserId(user.getUserId());
        comment.setUserNickname(user.getUserNickname());
        comment.setUserIcon(user.getUserIcon());
        LocalDate localTime = LocalDate.now();
        comment.setCommentCreationTime(localTime.toString());
        commentService.setComment(comment);
        //获取博客文章
        Article article = articleService.getArticle(comment.getArticleId());
        mav.addObject("article",article);
        //获取评论
        CommentUtil.getArticleComment(mav,comment.getArticleId(),null,commentService);
        return mav;
    }
}
