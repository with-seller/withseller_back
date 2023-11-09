package com.withseller.domain.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {
    private String status;
    private String message;
}
