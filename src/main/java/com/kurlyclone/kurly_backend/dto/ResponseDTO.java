package com.kurlyclone.kurly_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ResponseDTO {
    private boolean ok;
    private Object result;
}
