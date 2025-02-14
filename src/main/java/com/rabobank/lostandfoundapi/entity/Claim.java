package com.rabobank.lostandfoundapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CLAIMS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Claim {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "LOST_ITEM_NAME", nullable = false)
    private String lostItemName;

    @Column(name = "LOST_ITEM_QUANTITY", nullable = false)
    private Integer lostItemQuantity;
}
