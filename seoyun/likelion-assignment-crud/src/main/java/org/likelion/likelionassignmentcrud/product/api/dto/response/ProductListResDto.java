package org.likelion.likelionassignmentcrud.product.api.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ProductListResDto(
        List<ProductInfoResDto>  products
) {
    public static ProductListResDto from(List<ProductInfoResDto> products) {
        return ProductListResDto.builder()
                .products(products)
                .build();
    }
}
