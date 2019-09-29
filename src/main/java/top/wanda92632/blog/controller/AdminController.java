package top.wanda92632.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.wanda92632.blog.entity.Article;
import top.wanda92632.blog.entity.ArticleCategory;
import top.wanda92632.blog.entity.User;
import top.wanda92632.blog.service.*;
import top.wanda92632.blog.service.impl.ArticleServiceImpl;
import top.wanda92632.blog.service.impl.CommentServiceImpl;
import top.wanda92632.blog.service.impl.UserServiceImpl;

import java.util.List;

/**
 * @author ZhiFei
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 进入用户界面
     *
     * @return
     */
    @RequiresRoles("user")
    @RequiresAuthentication
    @RequestMapping("/admin/index")
    public ModelAndView adminIndex() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/index");
        int articleSum = articleService.queryArticleSum();
        int commentSum=commentService.queryCommentSum();
        int userSum=userService.queryUserSum();
        mav.addObject("articleSum",articleSum);
        mav.addObject("commentSum",commentSum);
        mav.addObject("userSum",userSum);
        return mav;
    }

    /**
     * 查看用户信息
     */
    @RequiresRoles("user")
    @RequiresAuthentication
    @RequestMapping("/admin/adminPersonal")
    public ModelAndView adminPersonal() {
        ModelAndView mav = new ModelAndView();
        //获取Session中的用户信息
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");

        mav.addObject("user", user);
        mav.setViewName("/admin/adminPersonal");
        return mav;
    }

    /**
     * 查看提交的全部文章
     *
     * @return
     */
    @RequiresRoles("user")
    @RequiresAuthentication
    @RequestMapping("/admin/adminAllArticle")
    public ModelAndView adminAllArticle() {
        ModelAndView mav = new ModelAndView("admin/adminAllArticle");
        //获取Session中的用户信息
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");
        List<Article> articleList = articleService.getAllArticleByUserID(user.getUserId());
        mav.addObject("articleList", articleList);
        return mav;
    }

    /**
     * 写文章
     *
     * @return
     */
    @RequiresRoles("user")
    @RequiresAuthentication
    @RequestMapping("/admin/writeArticle")
    public ModelAndView writeArticle() {
        ModelAndView mav = new ModelAndView("admin/writeArticle");
        List<ArticleCategory> categoryList = categoryService.getAllCategory();
        mav.addObject("categoryList",categoryList);
        return mav;
    }
}
