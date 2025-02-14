package com.rabobank.lostandfoundapi.repository;

import com.rabobank.lostandfoundapi.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimsRepository extends JpaRepository<Claim, Long> {
}
