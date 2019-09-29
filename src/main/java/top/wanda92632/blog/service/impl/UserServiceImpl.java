package top.wanda92632.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wanda92632.blog.dao.UserDao;
import top.wanda92632.blog.entity.User;
import top.wanda92632.blog.entity.UserRole;
import top.wanda92632.blog.service.RoleService;
import top.wanda92632.blog.service.UserService;
import top.wanda92632.blog.util.ImageUtil;
import top.wanda92632.blog.util.PathUtil;

import java.io.InputStream;

/**
 * @author ZhiFei
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;
    /**
     * 查询匹配用户数
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.matchUserNumber(userName, password);
        return matchCount > 0;
    }

    /**
     * 查找用户信息根据用户名
     *
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        return user;
    }

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void userRegistered(User user) {
        int effectedNum = userDao.userRegistered(user);
        if(effectedNum>0){
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(2);
         roleService.setRolesByUserId(userRole);
        }
    }


    /**
     * 更新用户数据
     *
     * @param user
     */
    @Override
    public void upUserData(User user) {
        userDao.upUserData(user);
        return;
    }

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserData(int userId) {
        return userDao.getUserData(userId);
    }

    /**
     * 查询用户数
     *
     * @return
     */
    @Override
    public int queryUserSum() {
        return userDao.queryUserSum();
    }

    /**
     * 更新用户头像
     *
     * @param fileName
     * @param userId
     * @param inputStream
     */
    @Override
    public void upUserIcon(String userId, String fileName, InputStream inputStream) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(userId);
        String shopImgAddr = ImageUtil.generateThumbnail(inputStream, fileName, dest);
        userDao.upUserIcon(userId, shopImgAddr);
    }
}
