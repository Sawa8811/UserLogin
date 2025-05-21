package com.Sawa.service;

import com.Sawa.entity.User;
import com.Sawa.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Sawa
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService{
    // 這裡可以實現用戶服務的具體邏輯

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        // 創建一個 QueryWrapper 來查詢用戶是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        // 查詢用戶 mybatis-plus
        userQueryWrapper.eq("username", user.getUsername());
        // 查詢用戶是否存在
        // 這裡的 selectOne 方法會返回符合條件的唯一結果
        User existUser = userMapper.selectOne(userQueryWrapper);
        // 如果用戶重複
        if (existUser != null) {
            return false;
        }

        // 加密密碼
        String rawPassword = user.getPassword();
        // 本次採用Spring Security 的 BCryptPasswordEncoder 來加密密碼
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(rawPassword));// encode 加密方法

        // 加密完成 檢查正確之後 寫入數據庫
        int rows = userMapper.insert(user);
        // 至少加入一行數據 所以採用 rows > 0
        // 更嚴謹地根據結果來判斷是否加入成功
        return rows > 0;

    }

    @Override
    public User login(String username, String rawPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        // 如果用戶不存在
        if (user == null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, user.getPassword()) ? user : null;
        // 這是BCryptPasswordEncoder 的驗證方法
    }
}
