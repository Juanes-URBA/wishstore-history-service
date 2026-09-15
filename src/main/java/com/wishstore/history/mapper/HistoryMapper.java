package com.wishstore.history.mapper;

import com.wishstore.history.dto.request.HistoryRequest;
import com.wishstore.history.dto.response.HistoryResponse;
import com.wishstore.history.entity.History;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryMapper {

    public History toEntity(HistoryRequest request) {
        return History.builder()
                .wishlistId(request.getWishlistId())
                .productId(request.getProductId())
                .action(request.getAction())
                .description(request.getDescription())
                .build();
    }

    public HistoryResponse toResponse(History entity) {
        return HistoryResponse.builder()
                .id(entity.getId())
                .wishlistId(entity.getWishlistId())
                .productId(entity.getProductId())
                .action(entity.getAction())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public List<HistoryResponse> toResponseList(List<History> entities) {
        return entities.stream()
                .map(this::toResponse)
                .toList();
    }

    public void updateEntity(History entity, HistoryRequest request) {
        entity.setWishlistId(request.getWishlistId());
        entity.setProductId(request.getProductId());
        entity.setAction(request.getAction());
        entity.setDescription(request.getDescription());
    }

}