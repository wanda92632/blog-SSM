package top.wanda92632.blog.service;

import top.wanda92632.blog.entity.Role;
import top.wanda92632.blog.entity.UserRole;

import java.util.List;

/**
 * @author ZhiFei
 */
public interface RoleService {

    /**
     * 获取角色数据
     * @param userName
     * @return
     */
    List<Role> getRolesByUserName(String userName);

    /**
     * 添加用户角色
     * @param userRole
     * @return
     */
    int setRolesByUserId(UserRole userRole);
}
