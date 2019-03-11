package com.e.typt.core.entity.dto.ws;

import lombok.Data;

/**
 */
@Data
public abstract class SplitPageDTO {

    private Integer page = 1;

    private Integer pageSize = 10;

    private Boolean asc = false;

}
