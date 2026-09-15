package com.wishstore.history.dto.response;

import com.wishstore.history.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse {

    private Long id;
    private Long wishlistId;
    private Long productId;
    private ActionType action;
    private String description;
    private LocalDateTime createdAt;

}