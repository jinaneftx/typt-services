package com.e.typt.common.controller;

import com.e.typt.common.bean.ResponseCode;
import com.e.typt.common.bean.ResponseResult;
import com.e.typt.common.service.DeleteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 */
public interface DeleteController<UID,S extends DeleteService<UID>> {

    S getService();

    @PostMapping("/remove/{id}")
    @ApiOperation(value = "删除指定ID的对象")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "身份认证Token",required = true)
    default ResponseResult remove(@PathVariable("id") UID id){
        getService().remove(id);
        return ResponseResult.e(ResponseCode.OK);
    }

}
