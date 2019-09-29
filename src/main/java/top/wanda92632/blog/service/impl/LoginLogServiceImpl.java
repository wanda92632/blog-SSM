package top.wanda92632.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wanda92632.blog.dao.LoginLogDao;
import top.wanda92632.blog.entity.LoginLog;
import top.wanda92632.blog.service.LoginLogService;

/**
 * @author ZhiFei
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    LoginLogDao loginLogDao;

    /**
     * 写入登录日志
     * @param loginLog
     */
    @Override
    public void setLoginLog(LoginLog loginLog)
    {
        loginLogDao.setLoginLog(loginLog);
        return;
    }
}
