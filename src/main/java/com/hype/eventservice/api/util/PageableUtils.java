package com.hype.eventservice.api.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {

    public static Pageable buildPageFilter(String sortValue, String sortBy, int sizeList) {
        Sort sort = Sort.by(sortValue).ascending();

        if(sortBy.equalsIgnoreCase("desc")){
            sort = Sort.by(sortValue).descending();
        }

        return PageRequest.of(0,sizeList, sort);
    }
}
