package com.tookscan.tookscan.term.domain;

import com.tookscan.tookscan.core.dto.BaseEntity;
import com.tookscan.tookscan.term.domain.type.ETermType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "terms")
public class Term extends BaseEntity {

    /* -------------------------------------------- */
    /* Default Column ----------------------------- */
    /* -------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* -------------------------------------------- */
    /* Information Column ------------------------- */
    /* -------------------------------------------- */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ETermType type;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "content", length = 2000, nullable = false)
    private String content;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired;

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public Term(ETermType type, String title, String content, boolean isRequired) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.isRequired = isRequired;
    }
}

