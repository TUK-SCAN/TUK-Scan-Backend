package com.tookscan.tookscan.term.domain;

import com.tookscan.tookscan.core.dto.BaseEntity;
import com.tookscan.tookscan.term.domain.type.ETermType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "terms")
@SQLDelete(sql = "UPDATE terms SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
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
    private Boolean isRequired;

    @Column(name = "is_visible", nullable = false)
    private Boolean isVisible = true;

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public Term(ETermType type, String title, String content, Boolean isRequired, Boolean isVisible) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.isRequired = isRequired;
        this.isVisible = isVisible;
    }
}

