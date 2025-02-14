package com.rabobank.lostandfoundapi.repository;

import com.rabobank.lostandfoundapi.entity.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LostItemsRepository extends JpaRepository<LostItem, Long> {

    Optional<LostItem> findLostItemByItemNameAndPlace(String itemName, String place);
}
