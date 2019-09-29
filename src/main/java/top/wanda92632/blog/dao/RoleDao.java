package top.wanda92632.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.wanda92632.blog.entity.Role;
import top.wanda92632.blog.entity.UserRole;

import java.util.List;

public interface RoleDao {
    List<Role> getRolesByUserName(@Param("userName") String userName);

    int setRolesByUserId(@Param("userRole") UserRole userRole);
}