package top.wanda92632.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import top.wanda92632.blog.entity.Article;
import top.wanda92632.blog.entity.ArticleCategory;
import top.wanda92632.blog.entity.Message;
import top.wanda92632.blog.entity.User;
import top.wanda92632.blog.service.ArticleService;
import top.wanda92632.blog.service.CategoryService;
import top.wanda92632.blog.service.MessageService;
import top.wanda92632.blog.service.UserService;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * @author ZhiFei
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MessageService messageService;
    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping("/registered")
    public ModelAndView registeredPage() {
        ModelAndView mav=new ModelAndView("/home/page/registered");
        //导航栏文章分类
        List<ArticleCategory> categoryList = categoryService.getAllCategory();
        mav.addObject("categoryList",categoryList);
        return mav;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/userRegister")
    public String userRegistered(User user) {
        user.setUserIcon("upload\\item\\user\\1\\2019062702025165147.jpg");
        userService.userRegistered(user);
        return "redirect:/";
    }

    /**
     * 更新用户数据
     *
     * @param user
     * @return
     */
    @RequiresRoles("user")
    @RequestMapping(value = "/upUserData", method = RequestMethod.POST)
    public String upUserData(User user) {
        //获取Session中的用户信息
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User currentUser = (User) session.getAttribute("user");
        User user1 = new User();
        user1.setUserId(currentUser.getUserId());
        user1.setUserNickname(user.getUserNickname());
        user1.setUserEmail(user.getUserEmail());
        userService.upUserData(user1);
        return "redirect:/logout";
    }

    /**
     * 更新用户头像
     *
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequiresRoles("user")
    @RequestMapping(value = "/upUserIcon", method = RequestMethod.POST)
    public ModelAndView upUserIcon(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        CommonsMultipartFile userIcon = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            userIcon = (CommonsMultipartFile) multipartHttpServletRequest.getFile("userIcon");
        }
        //获取Session中的用户信息
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");
        if (userIcon != null) {
            userService.upUserIcon(String.valueOf(user.getUserId()), userIcon.getOriginalFilename(), userIcon.getInputStream());
        }
        mav.addObject("user", user);
        mav.setViewName("redirect:/logout");
        return mav;
    }

    /**
     * 跳转留言板页面
     * @return
     */
    @RequestMapping("/messageBoard")
    public ModelAndView messageBoard(){
        ModelAndView mav = new ModelAndView("home/page/messageBoard");
        //导航栏文章分类
        List<ArticleCategory> categoryList = categoryService.getAllCategory();
        mav.addObject("categoryList",categoryList);
        List<Message> messageList = messageService.getAllMessage();
        mav.addObject("messageList",messageList);
        return mav;
    }

    /**
     * 其他用户查看用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    public ModelAndView userInfo(Integer userId){
        ModelAndView mav = new ModelAndView("home/page/userInfo");
        User user = userService.getUserData(userId);
        Integer articleSum= articleService.queryArticleSumByUserId(userId);
        List<Article> articleList = articleService.getAllArticleByUserID(userId);
        mav.addObject("user",user);
        mav.addObject("articleSum",articleSum);
        mav.addObject("articleList",articleList);
        return mav;
    }



    /**
     * 提交留言
     * @param message
     * @return
     */
    @RequestMapping(value = "/setMessage",method = RequestMethod.POST)
    public String setMessage(Message message){
        messageService.serMessage(message);
        return "redirect:/messageBoard";
    }
}
