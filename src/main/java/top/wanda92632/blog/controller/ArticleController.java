package top.wanda92632.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import top.wanda92632.blog.entity.Article;
import top.wanda92632.blog.entity.ArticleCategory;
import top.wanda92632.blog.entity.Category;
import top.wanda92632.blog.entity.User;
import top.wanda92632.blog.service.ArticleCategoryService;
import top.wanda92632.blog.service.ArticleService;
import top.wanda92632.blog.service.CategoryService;
import top.wanda92632.blog.service.CommentService;
import top.wanda92632.blog.util.ArticleUtil;
import top.wanda92632.blog.util.CommentUtil;
import top.wanda92632.blog.util.SidebarArticleUtil;


import java.time.LocalDate;
import java.util.List;


/**
 * @author ZhiFei
 */
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleCategoryService articleCategoryService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 提交文章
     * @param article
     * @return
     */
    @RequiresRoles("user")
    @RequestMapping(value = "/setArticle",method = RequestMethod.POST)
    public String submitArticle(Article article,Integer categoryId) {
        //获取Session中的用户信息
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");

        article.setUser(user);
        LocalDate localTime = LocalDate.now();
        article.setCreationTime(localTime.toString());
        //截取文章
        String introduction = article.getIntroduction();
        int length= introduction.length()<300?introduction.length():300;
        introduction = introduction.substring(0,length);
        Category category=new Category();
        category.setCategoryId(categoryId);
        article.setCategory(category);
        article.setIntroduction(introduction);
        articleService.setArticle(article);
        return "index";
    }

    /**
     * 查看文章
     * @param articleId
     * @return
     */
    @RequestMapping("/getArticleById")
    public ModelAndView get(Integer articleId,String page) {
        ModelAndView mav = new ModelAndView("home/page/article");
        //添加已阅读数
        articleService.addArticleViewed(articleId);
        //获取博客文章
        Article article = articleService.getArticle(articleId);
        mav.addObject("article",article);
        //获取评论
        CommentUtil.getArticleComment(mav,articleId,page,commentService);
        //获取侧边栏文章
        SidebarArticleUtil.getSidebarArticle(mav,articleService,commentService);
        //导航栏文章分类
        List<ArticleCategory> categoryList = categoryService.getAllCategory();
        mav.addObject("categoryList",categoryList);
        return mav;
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    @RequiresRoles("user")
    @RequestMapping(value = "/deleteArticle",method = RequestMethod.POST)
    public String deleteArticle(Integer articleId){
        articleCategoryService.deleteRelationByArticleId(articleId);

        articleService.deleteArticle(articleId);
        return "redirect:admin/adminAllArticle";
    }

    /**
     * 查找文章--标题
     * @param title
     * @return
     */
    @RequestMapping(value = "/getArticleByTitle")
    public ModelAndView getArticleByTitle(String title,String page){
        ModelAndView mav = new ModelAndView("/home/page/home");
        //类别文章
        ArticleUtil.getArticleTitle(mav,articleService,title,page);
        //获取侧边栏文章
        SidebarArticleUtil.getSidebarArticle(mav,articleService,commentService);
        //导航栏文章分类
        List<ArticleCategory> categoryList = categoryService.getAllCategory();
        mav.addObject("categoryList",categoryList);
        return mav;
    }

    /**
     * 查找文章-类别
     * @param categoryName
     * @return
     */
    @RequestMapping(value = "/getArticleByCategory" ,method = RequestMethod.GET)
    public ModelAndView getArticleByCategory(String categoryName,String page){
        ModelAndView mav = new ModelAndView("/home/page/home");
        //类别文章
        ArticleUtil.getArticleCategoryName(mav,articleService,categoryName,page);
        //获取侧边栏文章
        SidebarArticleUtil.getSidebarArticle(mav,articleService,commentService);
        //导航栏文章分类
        List<ArticleCategory> categoryList = categoryService.getAllCategory();
        mav.addObject("categoryList",categoryList);
        return mav;
    }
}
