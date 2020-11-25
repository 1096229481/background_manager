package com.example.backgroundmanager.sysadmin.organization.entity.param;

import com.example.backgroundmanager.common.web.entity.param.BaseParam;
import com.example.backgroundmanager.sysadmin.organization.entity.po.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionQueryParam extends BaseParam<Position> {
    private String name;
}
