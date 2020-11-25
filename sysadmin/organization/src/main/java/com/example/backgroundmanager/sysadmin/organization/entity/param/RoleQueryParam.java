package com.example.backgroundmanager.sysadmin.organization.entity.param;

import com.example.backgroundmanager.common.web.entity.param.BaseParam;
import com.example.backgroundmanager.sysadmin.organization.entity.po.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQueryParam extends BaseParam<Role> {
    private String code;
    private String name;
}
