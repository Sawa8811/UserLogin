package com.Sawa.controller;

import com.Sawa.entity.User;
import com.Sawa.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sawa
 * @version 1.0
 */

//@RestController
//@RequestMapping("/api")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
//
//    @PostMapping("/register")
//    public String register(@RequestBody User user) {
//        // 這裡是用來判斷用戶註冊請求是否成功
//        boolean result = userService.register(user);
//        if (result) {
//            return "註冊成功";
//        } else {
//            return "用戶已存在，註冊失敗";
//        }
//    }
    // 顯示註冊頁面請求
    @GetMapping("/register")
    public String showRegisterForm() {
        // 顯示註冊表單頁面
        return "register";
    }
    // 處理註冊請求
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        // 這裡是用來判斷用戶註冊請求是否成功
        boolean result = userService.register(user);
        if (result) {
            model.addAttribute("message", "註冊成功");
        } else {
            model.addAttribute("message", "用戶已存在，註冊失敗");
        }
        return "register";
        // 返回註冊頁面
    }

    // 顯示登入頁面請求
    @GetMapping("/login")
    public String showLoginForm() {
        // 顯示登入表單頁面
        return "login";
    }
    // 處理登入請求
    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        // 判斷用戶是否存在
        if (loginUser != null) {
            // 登入成功，將用戶信息存入 session
            session.setAttribute("user", loginUser);
            return "redirect:/home"; // 登入成功後重定向到首頁
        } else {
            model.addAttribute("message", "登入失敗，請檢查用戶名和密碼");
            return "login"; // 登入失敗，返回登入頁面
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
