package com.t3h.projectclothes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "addresses")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(nullable = false, length = 255)
    String province;

    @Column(nullable = false, length = 255)
    String district;

    @Column(nullable = false, length = 255)
    String ward;

    @Column(nullable = false, length = 255)
    String detail;

    @Column(nullable = false)
    @Builder.Default
    Boolean isDefault = false;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    java.util.List<Order> orders = new java.util.ArrayList<>();
}

