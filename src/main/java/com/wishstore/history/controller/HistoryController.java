package com.wishstore.history.controller;

import com.wishstore.history.dto.request.HistoryRequest;
import com.wishstore.history.dto.response.HistoryResponse;
import com.wishstore.history.service.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> findAll() {
        return ResponseEntity.ok(historyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.findById(id));
    }

    @GetMapping("/wishlist/{wishlistId}")
    public ResponseEntity<List<HistoryResponse>> findByWishlist(@PathVariable Long wishlistId) {
        return ResponseEntity.ok(historyService.findByWishlist(wishlistId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<HistoryResponse>> findByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(historyService.findByProduct(productId));
    }

    @PostMapping
    public ResponseEntity<HistoryResponse> create(@Valid @RequestBody HistoryRequest request) {
        HistoryResponse response = historyService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        historyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}