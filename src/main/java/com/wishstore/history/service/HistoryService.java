package com.wishstore.history.service;

import com.wishstore.history.dto.request.HistoryRequest;
import com.wishstore.history.dto.response.HistoryResponse;

import java.util.List;

public interface HistoryService {

    List<HistoryResponse> findAll();

    HistoryResponse findById(Long id);

    HistoryResponse create(HistoryRequest request);

    void delete(Long id);

    List<HistoryResponse> findByWishlist(Long wishlistId);

    List<HistoryResponse> findByProduct(Long productId);

}