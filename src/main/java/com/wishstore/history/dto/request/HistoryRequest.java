package com.wishstore.history.dto.request;

import com.wishstore.history.enums.ActionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequest {

    @NotNull(message = "El campo wishlistId es obligatorio")
    private Long wishlistId;

    @NotNull(message = "El campo productId es obligatorio")
    private Long productId;

    @NotNull(message = "El campo action es obligatorio")
    private ActionType action;

    @Size(max = 255, message = "El campo description no puede superar los 255 caracteres")
    private String description;

}