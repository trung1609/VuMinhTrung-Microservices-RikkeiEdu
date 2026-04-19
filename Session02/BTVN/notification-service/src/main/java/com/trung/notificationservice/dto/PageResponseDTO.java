package com.trung.notificationservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseDTO<T>{
    private List<T> data;
    private Integer page;
    private Integer size;
    private Long totalElements;
}
