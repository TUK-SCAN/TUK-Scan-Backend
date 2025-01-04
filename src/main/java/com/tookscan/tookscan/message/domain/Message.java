package com.tookscan.tookscan.message.domain;

import com.tookscan.tookscan.core.dto.BaseEntity;
import com.tookscan.tookscan.message.domain.type.EMessageStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "messages")
public class Message extends BaseEntity {

    /* -------------------------------------------- */
    /* Default Column ----------------------------- */
    /* -------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* -------------------------------------------- */
    /* Information Column ------------------------- */
    /* -------------------------------------------- */
    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 2500, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EMessageStatus status;

    /* -------------------------------------------- */
    /* One To Many Mapping ------------------------ */
    /* -------------------------------------------- */
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageGroup> messageGroups = new ArrayList<>();

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public Message(String imgUrl, String title, String content, EMessageStatus status) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.content = content;
        this.status = status;
    }
}
