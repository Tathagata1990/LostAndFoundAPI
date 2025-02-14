package com.rabobank.lostandfoundapi.service;

import com.rabobank.lostandfoundapi.entity.LostItem;

import java.util.List;

public interface LostItemsService {

    /**
     * Returns a list of lost items currently present in the database
     * @return
     */
    List<LostItem> getLostItems();

    /**
     * Updates the quantity of a lost item, when an item is claimed by a user
     * @param lostItem
     */
    void saveLostItem(LostItem lostItem);

    /**
     * Saves a list of lost items read from the PDF file which is uploaded by a user
     * @param lostItems
     */
    void saveLostItems(List<LostItem> lostItems);

    /**
     * Finds an item based on the name and place the item was found (The combination of the name and place is always unique)
     * Throws ItemNotFoundException if the combination is not found in the database
     * @param itemName
     * @param place
     * @return
     */
    LostItem findLostItemByItemNameAndPlace(String itemName, String place);

    /**
     * Deletes a lost item from the database when all instances of that item has been claimed. i.e. when the quantity reaches 0
     * @param lostItem
     */
    void deleteLostItem(LostItem lostItem);
}
