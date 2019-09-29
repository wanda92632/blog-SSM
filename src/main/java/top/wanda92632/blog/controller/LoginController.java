package top.wanda92632.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import top.wanda92632.blog.entity.*;
import top.wanda92632.blog.service.*;
import top.wanda92632.blog.util.ArticleUtil;
import top.wanda92632.blog.util.SidebarArticleUtil;
import top.wanda92632.blog.vo.LoginData;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ZhiFei
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 默认页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String loginPage(HttpServletRequest request) {
        String ip = getIpAddress(request);
        String time = LocalDateTime.now().toString();
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(-1);
        loginLog.setIp(ip);
        loginLog.setVisit(time);
        loginLogService.setLoginLog(loginLog);
        return "redirect:/";
    }

    /**
     * 跳转主页
     *
     * @param request
     * @return
     */
    @RequestMapping("/")
    public ModelAndView homePage(HttpServletRequest request,String page) {
        String ip = getIpAddress(request);
        String time = LocalDateTime.now().toString();
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(3);
        loginLog.setIp(ip);
        loginLog.setVisit(time);
        loginLogService.setLoginLog(loginLog);
        ModelAndView mav = new ModelAndView("/home/page/home");
        ArticleUtil.getHomeArticle(mav,articleService,page);
        //导航栏文章分类
        List<ArticleCategory> categoryList = categoryService.getAllCategory();
        mav.addObject("categoryList",categoryList);
        System.out.println(categoryList);
        //获取侧边栏文章
        SidebarArticleUtil.getSidebarArticle(mav,articleService,commentService);
        return mav;
    }

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping("/loginPage")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/home/page/login");
        //导航栏文章分类
        List<ArticleCategory> categoryList = categoryService.getAllCategory();
        mav.addObject("categoryList",categoryList);
        return mav;
    }


    /**
     * 用户登录
     *
     * @param request
     * @param loginData
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView userLogin(HttpServletRequest request, LoginData loginData) {
        ModelAndView mav = new ModelAndView("redirect:/");
        //创建Subject实例对象
        Subject subject = SecurityUtils.getSubject();
        //判断当前用户是否已登录
        if (subject.isAuthenticated() == false) {
            UsernamePasswordToken token = new UsernamePasswordToken(loginData.getUserName(), loginData.getPassword());
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                System.out.println("登陆失败");
                mav.setViewName("/home/page/login");
                mav.addObject("state","用户不存在或密码错误");
                return mav;
            }
        } else {
            System.out.println("用户已经登录");
        }
        //查询用户信息
        User user = userService.findUserByUserName(loginData.getUserName());
        //更新用户信息
        String ip = getIpAddress(request);
        String time = LocalDateTime.now().toString();
        user.setLastIP(ip);
        user.setLastVisit(time);
        //添加登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(ip);
        loginLog.setVisit(time);
        loginLogService.setLoginLog(loginLog);
        //存入session
        Session session = subject.getSession();
        session.setAttribute("user", user);
        userService.upUserData(user);
        return mav;
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        if ("127.0.0.1".equals(ip)) {
            //根据网卡取本机配置的IP
            InetAddress inet = null;
            try {
                inet = InetAddress.getLocalHost();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ip = inet.getHostAddress();
        }
        return ip;
    }
}

