package com.e.typt.common.service;

/**
 * @see BaseService 注释配置请参见BaseService
 */
public interface UpdateService<UID,UD> {

    void update(UID id,UD updateDTO);

}
