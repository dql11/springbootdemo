package com.dql.demo.controller;

import cn.hutool.core.lang.Dict;
import com.dql.demo.property.ApplicationProperty;
import com.dql.demo.property.DeveloperProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {
    private final ApplicationProperty applicationProperty;
    private final DeveloperProperty developerProperty;
    public PropertyController(ApplicationProperty applicationProperty, DeveloperProperty developerProperty) {
        this.applicationProperty = applicationProperty;
        this.developerProperty = developerProperty;
    }

    @GetMapping("property")
    public Dict index(){
        return Dict.create().set("applicationProperty",applicationProperty)
                .set("developerProperty",developerProperty);
    }
}
