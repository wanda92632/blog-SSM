package top.wanda92632.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wanda92632.blog.dao.RoleDao;
import top.wanda92632.blog.entity.Role;
import top.wanda92632.blog.entity.UserRole;
import top.wanda92632.blog.service.RoleService;

import java.util.List;

/**
 * @author ZhiFei
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    /**
     * 获取角色数据
     * @param userName
     * @return
     */
    @Override
    public List<Role> getRolesByUserName(String userName){
        List<Role> roleList = roleDao.getRolesByUserName(userName);
        return roleList;
    }

    /**
     * 添加用户角色
     *
     * @param userRole
     * @return
     */
    @Override
    public int setRolesByUserId(UserRole userRole) {
        return roleDao.setRolesByUserId(userRole);
    }
}
