package com.ww.mall.controller;

import cn.hutool.json.JSONUtil;
import com.ww.mall.base.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;



@RestController
@Api(tags = "PmsProductController")
@Tag(name = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {

    private Logger logger = LoggerFactory.getLogger(PmsProductController.class);

    @ApiOperation("查询商品")
    @GetMapping(value = "/list")
    public CommonResult<String> getList() {
        logger.info("查询商品入参【{}】", "查询到商品....");
        return CommonResult.success("success");
    }

}
