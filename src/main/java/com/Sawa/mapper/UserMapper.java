package com.Sawa.mapper;

import com.Sawa.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Sawa
 * @version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 這邊交由 MyBatis-Plus 自動生成
}
