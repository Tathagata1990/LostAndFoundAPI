package com.rabobank.lostandfoundapi.service;

import com.rabobank.lostandfoundapi.entity.Claim;
import com.rabobank.lostandfoundapi.repository.ClaimsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimsServiceImpl implements ClaimsService {

    private final ClaimsRepository claimsRepository;

    @Override
    public void claimItem(Claim claim) {
        claimsRepository.save(claim);
    }

    @Override
    public List<Claim> getClaimedItems() {
        return claimsRepository.findAll();
    }

}
