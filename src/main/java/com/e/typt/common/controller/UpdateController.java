package com.e.typt.common.controller;

import com.e.typt.common.bean.ResponseCode;
import com.e.typt.common.bean.ResponseResult;
import com.e.typt.common.service.UpdateService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 */
public interface UpdateController<UID,UD,S extends UpdateService<UID,UD>> {

    S getService();

    @PostMapping("/update/{id}")
    @ApiOperation(value = "更新指定ID对象的信息")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "身份认证Token",required = true)
    default ResponseResult update(@PathVariable("id") UID id, @RequestBody @Validated UD updateDTO){
        getService().update(id,updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

}
