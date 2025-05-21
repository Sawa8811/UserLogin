package com.Sawa.service;

import com.Sawa.entity.User;

/**
 * @author Sawa
 * @version 1.0
 */

public interface UserService {
    // 這個介面是用來定義用戶服務的接口
    boolean register(User user);
    User login(String username, String rawPassword);
    // raw 表示未加工 rawPassword 是為加密前的密碼
}
