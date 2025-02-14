package com.rabobank.lostandfoundapi.bo;

import com.rabobank.lostandfoundapi.controller.response.LostItemsResponse;
import org.springframework.web.multipart.MultipartFile;

public interface LostItemsBO {

    void processDocument(MultipartFile file);

    LostItemsResponse getLostItems();
}