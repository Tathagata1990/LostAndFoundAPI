package com.rabobank.lostandfoundapi.bo;

import com.rabobank.lostandfoundapi.controller.response.LostItemsResponse;
import org.springframework.web.multipart.MultipartFile;

public interface LostItemsBO {

    /**
     * Reads a PDF document uploaded by the user and extracts and stores lost items in the database.
     * Note: This method will only work with the file format provided in the resources folder.
     * For any other format, the method needs to the adjusted to the new format
     * @param file
     */
    void processDocument(MultipartFile file);

    /**
     * Returns a list of lost items from the database
     * @return
     */
    LostItemsResponse getLostItems();
}