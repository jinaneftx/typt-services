package com.e.typt.common.controller;

import com.e.typt.common.bean.ResponseCode;
import com.e.typt.common.bean.ResponseResult;
import com.e.typt.common.service.BaseService;
import com.e.typt.common.service.CreateService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 */
public interface CreateController<AD,S extends CreateService<AD>> {

    S getService();

    @PostMapping("/add")
    @ApiOperation(value = "添加新增")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "身份认证Token",required = true)
    default ResponseResult add(@RequestBody @Validated AD a){
        getService().add(a);
        return ResponseResult.e(ResponseCode.OK);
    }
}
