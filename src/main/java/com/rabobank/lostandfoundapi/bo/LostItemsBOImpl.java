package com.rabobank.lostandfoundapi.bo;

import com.rabobank.lostandfoundapi.controller.response.LostItemDTO;
import com.rabobank.lostandfoundapi.controller.response.LostItemsResponse;
import com.rabobank.lostandfoundapi.entity.LostItem;
import com.rabobank.lostandfoundapi.exception.UploadException;
import com.rabobank.lostandfoundapi.service.LostItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LostItemsBOImpl implements LostItemsBO {

    private static final String ITEM_SEPARATOR = "\n";
    private static final String ITEM_ATTRIBUTE_SEPARATOR = ",";

    private final LostItemsService lostItemsService;

    @Override
    public void processDocument(MultipartFile file) {
        List<LostItem> lostItems = readLostItemsFromDocument(file);
        lostItemsService.saveLostItems(lostItems);
    }

    @Override
    public LostItemsResponse getLostItems() {
        List<LostItemDTO> lostItems = lostItemsService.getLostItems()
                .stream()
                .map(this::toLostItemDTO)
                .toList();
        return LostItemsResponse.builder()
                .lostItems(lostItems)
                .build();
    }

    private List<LostItem> readLostItemsFromDocument(MultipartFile file) {
        List<LostItem> lostItems = new ArrayList<>();

        try (PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            String content = pdfStripper.getText(document);
            String[] items = content.split(ITEM_SEPARATOR);
            for (String item : items) {
                String[] attributes = item.split(ITEM_ATTRIBUTE_SEPARATOR);
                if (attributes.length == 3) {
                    LostItem lostItem = LostItem.builder()
                            .itemName(attributes[0].trim())
                            .quantity(Integer.parseInt(attributes[1].trim()))
                            .place(attributes[2].trim())
                            .build();
                    lostItems.add(lostItem);
                } else throw new IllegalArgumentException("Invalid item format");
            }
        } catch (IOException e) {
            log.error("Failed to read lost items from document", e);
            throw new UploadException("Failed to upload lost items from document");
        }
        return lostItems;
    }

    private LostItemDTO toLostItemDTO(LostItem lostItem) {
        return LostItemDTO.builder()
                .itemName(lostItem.getItemName())
                .quantity(lostItem.getQuantity())
                .place(lostItem.getPlace())
                .build();
    }
}