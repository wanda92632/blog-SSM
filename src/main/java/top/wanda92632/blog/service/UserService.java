package top.wanda92632.blog.service;

import top.wanda92632.blog.entity.Register;
import top.wanda92632.blog.entity.User;

import java.io.InputStream;

public interface UserService {

    /**
     * 查询匹配用户数
     * @param userName
     * @param password
     * @return
     */
    boolean hasMatchUser(String userName, String password);

    /**
     * 查找用户信息根据用户名
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 用户注册
     * @param user
     */
    void userRegistered(User user);

    /**
     * 更新用户数据
     * @param user
     */
    void upUserData(User user);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    User getUserData(int userId);

    /**
     * 查询用户数
     * @return
     */
    int queryUserSum();

    /**
     * 更新用户头像
     * @param fileName
     * @param userId
     * @param inputStream
     */
    void upUserIcon(String userId,String fileName, InputStream inputStream);
}
