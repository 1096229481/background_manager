package com.example.backgroundmanager.sysadmin.organization.entity.param;

import com.example.backgroundmanager.common.web.entity.param.BaseParam;
import com.example.backgroundmanager.sysadmin.organization.entity.po.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupQueryParam extends BaseParam<Group> {
    private String name;
}
