package com.ww.mall.controller;


import cn.hutool.json.JSONUtil;
import com.ww.mall.dto.UmsAdminParam;
import com.ww.mall.service.UmsAdminService;
import com.ww.mall.base.CommonResult;
import com.ww.mall.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台管理控制层
 *
 * @author wangwei
 */
@Slf4j
@RestController
@Api(tags = "AdminController")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    @ApiOperation(value="用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam){
        log.info("用户注册入参:【{}】", JSONUtil.toJsonStr(umsAdminParam));
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        if (null == umsAdmin) {
            return CommonResult.failed("注册异常!");
        }
        return CommonResult.success(umsAdmin);
    }




}
