package top.wanda92632.blog.dao;


import org.apache.ibatis.annotations.Param;
import top.wanda92632.blog.entity.LoginLog;

public interface LoginLogDao {
     void setLoginLog(@Param("loginLog")LoginLog setLoginLog);
}
