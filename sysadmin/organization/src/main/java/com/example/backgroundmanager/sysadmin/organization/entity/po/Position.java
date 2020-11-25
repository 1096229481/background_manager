package com.example.backgroundmanager.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.backgroundmanager.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position extends BasePo {
    private String name;
    private String description;
    @TableLogic
    private String deleted = "N";
}
