package com.wishstore.history.repository;

import com.wishstore.history.entity.History;
import com.wishstore.history.enums.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByWishlistId(Long wishlistId);

    List<History> findByProductId(Long productId);

    List<History> findByAction(ActionType action);

    List<History> findByWishlistIdOrderByCreatedAtDesc(Long wishlistId);

}