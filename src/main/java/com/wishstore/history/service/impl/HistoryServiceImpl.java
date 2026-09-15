package com.wishstore.history.service.impl;

import com.wishstore.history.dto.request.HistoryRequest;
import com.wishstore.history.dto.response.HistoryResponse;
import com.wishstore.history.entity.History;
import com.wishstore.history.exception.ResourceNotFoundException;
import com.wishstore.history.mapper.HistoryMapper;
import com.wishstore.history.repository.HistoryRepository;
import com.wishstore.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    @Override
    public List<HistoryResponse> findAll() {
        List<History> historyList = historyRepository.findAll();
        return historyMapper.toResponseList(historyList);
    }

    @Override
    public HistoryResponse findById(Long id) {
        History history = historyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró historial con id: " + id));

        return historyMapper.toResponse(history);
    }

    @Override
    public HistoryResponse create(HistoryRequest request) {
        History history = historyMapper.toEntity(request);
        history.setCreatedAt(LocalDateTime.now());

        History savedHistory = historyRepository.save(history);

        return historyMapper.toResponse(savedHistory);
    }

    @Override
    public void delete(Long id) {
        History history = historyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró historial con id: " + id));

        historyRepository.delete(history);
    }

    @Override
    public List<HistoryResponse> findByWishlist(Long wishlistId) {
        List<History> historyList = historyRepository.findByWishlistIdOrderByCreatedAtDesc(wishlistId);
        return historyMapper.toResponseList(historyList);
    }

    @Override
    public List<HistoryResponse> findByProduct(Long productId) {
        List<History> historyList = historyRepository.findByProductId(productId);
        return historyMapper.toResponseList(historyList);
    }

}