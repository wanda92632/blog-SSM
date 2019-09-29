package top.wanda92632.blog.service;

import top.wanda92632.blog.entity.LoginLog;

public interface LoginLogService {

    /*
     * 写入登录日志
     * */
    void setLoginLog(LoginLog loginLog);
}
