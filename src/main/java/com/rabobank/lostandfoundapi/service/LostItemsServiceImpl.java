package com.rabobank.lostandfoundapi.service;

import com.rabobank.lostandfoundapi.entity.LostItem;
import com.rabobank.lostandfoundapi.exception.ItemNotFoundException;
import com.rabobank.lostandfoundapi.repository.LostItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LostItemsServiceImpl implements LostItemsService {

    private final LostItemsRepository lostItemsRepository;

    @Override
    public List<LostItem> getLostItems() {
        return lostItemsRepository.findAll();
    }

    @Override
    public void saveLostItem(LostItem lostItem) {
        lostItemsRepository.save(lostItem);
    }

    @Override
    public void saveLostItems(List<LostItem> lostItems) {
        lostItemsRepository.saveAll(lostItems);
    }

    @Override
    public LostItem findLostItemByItemNameAndPlace(String itemName, String place) {
        return lostItemsRepository.findLostItemByItemNameAndPlace(itemName, place)
                .orElseThrow(() -> new ItemNotFoundException("Lost item not found"));
    }

    @Override
    public void deleteLostItem(LostItem lostItem) {
        lostItemsRepository.delete(lostItem);
    }
}
