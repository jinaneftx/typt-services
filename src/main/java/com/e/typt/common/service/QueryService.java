package com.e.typt.common.service;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @see BaseService 注释配置请参见BaseService
 */
public interface QueryService<E,FD> {

    Page<E> list(FD findDTO);

}
