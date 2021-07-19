package com.kurlyclone.kurly_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InnerCategory {
    // 친환경 / 고구마,감자,당근 / 시금치,쌈채소,나물
    VEGE1(1L), VEGE2(2L), VEGE3(3L),
    // 친환경 / 제철과일 / 국산과일
    FRUIT1(4L), FRUIT2(5L), FRUIT3(6L),
    // 제철, 수산 / 생선류 / 굴비 반건류
    SEAFOOD1(7L), SEAFOOD2(8L), SEAFOOD3(9L);

    private final Long id;

}
