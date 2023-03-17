package com.osh.jntest.global.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO {

    private Integer startIndex;

    private Integer searchQuantity;

    private Integer page;

    public Integer getPage() {
        return startIndex * searchQuantity;
    }

    public PagingVO(Integer startIndex, Integer searchQuantity) {
        this.startIndex = startIndex;
        this.searchQuantity = searchQuantity;
    }
}
