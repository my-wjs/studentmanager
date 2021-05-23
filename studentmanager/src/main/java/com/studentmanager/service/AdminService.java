package com.studentmanager.service;

import com.studentmanager.entity.Admin;
import com.studentmanager.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminMapper {
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Admin findByAdmin(Admin admin) {
        return adminMapper.findByAdmin(admin);
    }

    @Override
    public Admin findByAdminByUserName(String username) {
        return adminMapper.findByAdminByUserName(username);
    }

    @Override
    public int editPassword(Admin admin) {
        return adminMapper.editPassword(admin);
    }


}
