package com.rabobank.lostandfoundapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LOST_ITEMS", uniqueConstraints = {@UniqueConstraint(columnNames = {"ITEM_NAME", "PLACE"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LostItem {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ITEM_NAME", nullable = false)
    private String itemName;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "PLACE", nullable = false)
    private String place;
}
