package com.rabobank.lostandfoundapi.bo;

import com.rabobank.lostandfoundapi.controller.request.ClaimDTO;
import com.rabobank.lostandfoundapi.controller.request.ClaimRequest;
import com.rabobank.lostandfoundapi.controller.response.ClaimedItemDTO;
import com.rabobank.lostandfoundapi.controller.response.ClaimedItemsResponse;
import com.rabobank.lostandfoundapi.controller.response.UserDTO;
import com.rabobank.lostandfoundapi.entity.Claim;
import com.rabobank.lostandfoundapi.entity.LostItem;
import com.rabobank.lostandfoundapi.service.ClaimsService;
import com.rabobank.lostandfoundapi.service.LostItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClaimsBOImpl implements ClaimsBO {

    private final ClaimsService claimsService;

    private final LostItemsService lostItemsService;

    @Override
    public void claimLostItem(ClaimRequest claimRequest) {

        ClaimDTO claimItem = claimRequest.getClaimItem();

        // find the lost item from the LostItems table
        LostItem lostItem = lostItemsService.findLostItemByItemNameAndPlace(claimItem.getLostItemName(), claimItem.getLostItemPlace());

        // if the lost item is found, decrease the quantity of the lost item by 1 or delete the lost item if the quantity reaches 0
        decreaseLostItemQuantity(lostItem, claimItem.getLostItemQuantity());

        Claim claim = Claim.builder()
                .userId(claimRequest.getUserId())
                .lostItemName(claimItem.getLostItemName())
                .lostItemQuantity(claimItem.getLostItemQuantity())
                .build();

        // update Claims with the user and the name and quantity of the claimed item
        claimsService.claimItem(claim);
    }

    @Override
    public ClaimedItemsResponse getClaimedItems() {
        List<Claim> claimedItems = claimsService.getClaimedItems();
        return ClaimedItemsResponse.builder()
                .claimedItems(toClaimedItemDTOs(claimedItems))
                .build();
    }

    private void decreaseLostItemQuantity(LostItem lostItem, Integer quantity) {
        if (lostItem.getQuantity() < quantity) {
            throw new IllegalArgumentException("The quantity requested by the user exceeds the maximum number of the requested lost item.");
        }
        lostItem.setQuantity(lostItem.getQuantity() - quantity);
        if (lostItem.getQuantity() == 0) {
            lostItemsService.deleteLostItem(lostItem);
        } else {
            lostItemsService.saveLostItem(lostItem);
        }
    }

    private List<ClaimedItemDTO> toClaimedItemDTOs(List<Claim> claimedItems) {
        return claimedItems.stream()
                .collect(Collectors.groupingBy(Claim::getLostItemName))
                .entrySet().stream()
                .map(e -> ClaimedItemDTO.builder().lostItem(e.getKey()).users(toUserDTOs(e.getValue())).build())
                .toList();
    }

    private List<UserDTO> toUserDTOs(List<Claim> claims) {
        return claims.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    private UserDTO toUserDTO(Claim claim) {
        return UserDTO.builder()
                .id(claim.getUserId())
                .name(claim.getUserName())
                .build();
    }
}
