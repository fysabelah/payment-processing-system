package com.payment.system.customer_service.util.pagination;

import com.payment.system.customer_service.interfaceadapters.presenters.dtos.Dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PagedResponse<T extends Dto> {

    private Pagination page;

    private List<T> data;
}
