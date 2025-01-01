package com.tukscan.tukscan.account.domain;

import com.tukscan.tukscan.security.domain.mysql.Account;
import com.tukscan.tukscan.security.domain.type.ESecurityProvider;
import com.tukscan.tukscan.security.domain.type.ESecurityRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "admins")
@PrimaryKeyJoinColumn(
        name = "account_id",
        foreignKey = @ForeignKey(name = "fk_admin_account")
)
@DiscriminatorValue("ADMIN")
@DynamicUpdate
public class Admin extends Account {

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public Admin(
            String serialId,
            String password
    ) {
        super(ESecurityProvider.DEFAULT, serialId, password, null);
    }

    @Override
    public ESecurityRole getRole() {
        return ESecurityRole.ADMIN;
    }
}
