package com.trung.userservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {
    private Integer page;
    private Integer size;
    private String sort;
    private String direction;
}
