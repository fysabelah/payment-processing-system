package com.customers.management.system.customersmicroservice.util.pagination;

import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos.Dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PagedResponse<T extends Dto> {

    private Pagination page;

    private List<T> data;
}
