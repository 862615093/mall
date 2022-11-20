package com.ww.mall.controller;

import com.ww.mall.base.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;



@RestController
@Api(tags = "PmsProductController")
@Tag(name = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {

    @ApiOperation("查询商品")
    @GetMapping(value = "/list")
    public CommonResult<String> getList() {
        System.out.println("进入了查询商品方法...");
        return CommonResult.success("success");
    }

}
