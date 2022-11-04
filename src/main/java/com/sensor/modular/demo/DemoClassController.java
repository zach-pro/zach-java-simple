package com.sensor.modular.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by apple
 * @Classname DemoClassController
 * @Description TODO
 * @Date 2022/11/3 15:42
 */
@Tag(name = "demo_url", description = "demo模块")
@RestController
@RequestMapping("/demo_url/Demo")
@Transactional(rollbackFor = Exception.class)
public class DemoClassController {

    // @Hidden
    @GetMapping("/getDemo")
    @Operation(summary = "查询demo")
    @Parameters(
            @Parameter(name = "id", description = "测试Str", required = true)
    )
    public String getDemo(String id) {
        return id;
    }

    // @Hidden
    @PostMapping("/saveDemo")
    @Operation(summary = "保存demo")
    public DemoClass saveDemo(DemoClass demo) {
        return demo;
    }
}