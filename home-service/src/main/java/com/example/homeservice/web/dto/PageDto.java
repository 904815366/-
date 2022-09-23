package com.example.homeservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {
    private Integer pageSize;
    private Integer pageNum;
    private Integer totalSize;
    private Long totalCount;
    private List<T> content;

    public PageDto<T> getPageDto(Page page, List<T> dtos) {
        PageDto<T> PageDto = new PageDto<>();
        PageDto.setPageSize(page.getSize());
        PageDto.setPageNum(page.getNumber());
        PageDto.setTotalSize(page.getTotalPages());
        PageDto.setTotalCount(page.getTotalElements());
        PageDto.setContent(dtos);
        return PageDto;
    }

}
