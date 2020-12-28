package com.example.backgroundmanager.mybatis.plus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class User extends Model<User> {
    private String name;

    private Integer age;
}
