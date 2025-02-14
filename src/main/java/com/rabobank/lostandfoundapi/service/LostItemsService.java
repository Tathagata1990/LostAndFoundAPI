package com.rabobank.lostandfoundapi.service;

import com.rabobank.lostandfoundapi.entity.LostItem;

import java.util.List;

public interface LostItemsService {

    List<LostItem> getLostItems();

    void saveLostItem(LostItem lostItem);

    void saveLostItems(List<LostItem> lostItems);

    LostItem findLostItemByItemNameAndPlace(String itemName, String place);

    void deleteLostItem(LostItem lostItem);
}
