package com.example.backgroundmanager.sysadmin.organization.entity.param;

import com.example.backgroundmanager.common.web.entity.param.BaseParam;
import com.example.backgroundmanager.sysadmin.organization.entity.po.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuQueryParam extends BaseParam<Menu> {
    private String name;
}
