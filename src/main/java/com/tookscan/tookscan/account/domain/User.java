package com.tookscan.tookscan.account.domain;

import com.tookscan.tookscan.address.domain.Address;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.security.domain.mysql.Account;
import com.tookscan.tookscan.security.domain.type.ESecurityProvider;
import com.tookscan.tookscan.security.domain.type.ESecurityRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@PrimaryKeyJoinColumn(
        name = "account_id",
        foreignKey = @ForeignKey(name = "fk_user_account")
)
@DiscriminatorValue("USER")
@DynamicUpdate
public class User extends Account {

    /* -------------------------------------------- */
    /* Information Column ------------------------- */
    /* -------------------------------------------- */
    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "email", length = 320)
    private String email;

    @Column(name = "marketing_allowed", nullable = false)
    private Boolean marketingAllowed;

    @Column(name = "memo", length = 500)
    private String memo;

    /* -------------------------------------------- */
    /* Embedded Column ---------------------------- */
    /* -------------------------------------------- */
    @Embedded
    private Address address;

    /* -------------------------------------------- */
    /* One To Many Mapping ------------------------ */
    /* -------------------------------------------- */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserGroup> userGroups = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public User(
            ESecurityProvider provider,
            String serialId,
            String password,
            String name,
            String phoneNumber,
            Boolean marketingAllowed
    ) {
        super(provider, serialId, password, phoneNumber);
        this.name = name;
        this.email = null;
        this.marketingAllowed = marketingAllowed;
        this.memo = null;
        this.address = null;
    }

    @Override
    public ESecurityRole getRole() {
        return ESecurityRole.USER;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void updateAddress(Address address) {
        this.address = address;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePhone(String phoneNumber) {
        super.updatePhoneNumber(phoneNumber);
    }
}

