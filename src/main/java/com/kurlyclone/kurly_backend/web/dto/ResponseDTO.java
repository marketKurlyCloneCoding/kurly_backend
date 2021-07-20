package com.kurlyclone.kurly_backend.web.dto;

import com.kurlyclone.kurly_backend.model.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ResponseDTO {

    private Long id;
    private String img;
    private String title;
    private String subTitle;
    private Integer price;
    private Integer dc;
    private Integer original_price;


    public ResponseDTO(Product product)
    {
        this.id=product.getId();
        this.img = product.getImg();
        this.title = product.getTitle();
        this.subTitle = product.getSubTitle();
        this.price = product.getPrice();
        this.dc = product.getDc();
        this.original_price = product.getOriginal_price();

    }
}
