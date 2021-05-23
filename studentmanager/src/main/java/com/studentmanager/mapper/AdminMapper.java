package com.studentmanager.mapper;

import com.studentmanager.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    Admin findByAdmin(Admin admin);

    Admin findByAdminByUserName(@Param("userName") String username);

    int editPassword(Admin admin);
}
