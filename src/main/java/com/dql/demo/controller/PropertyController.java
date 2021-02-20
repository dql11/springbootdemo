package com.dql.demo.controller;

import cn.hutool.core.lang.Dict;
import com.dql.demo.property.ApplicationProperty;
import com.dql.demo.property.DeveloperProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "fffs-SNAPSHOT", value = "用户管理")
public class PropertyController {
    private final ApplicationProperty applicationProperty;
    private final DeveloperProperty developerProperty;
    public PropertyController(ApplicationProperty applicationProperty, DeveloperProperty developerProperty) {
        this.applicationProperty = applicationProperty;
        this.developerProperty = developerProperty;
    }

    @GetMapping("property")
    @ApiOperation(value = "添加用户（DONE）", notes = "备注")
    public Dict index(){
        return Dict.create().set("applicationProperty",applicationProperty)
                .set("developerProperty",developerProperty);
    }
}
