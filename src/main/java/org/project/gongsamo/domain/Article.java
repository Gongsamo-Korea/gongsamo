package org.project.gongsamo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(columnDefinition = "int COMMENT '발행호수'")
    private int issueNumber;

    @Column(columnDefinition = "VARCHAR(40) COMMENT '목차'")
    private String tableOfContent;

    @Column(nullable = false, columnDefinition = "VARCHAR(20) COMMENT '제목'")
    private String title;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '썸네일 URL'")
    private String thumbnailUrl;

    @Column(nullable = false, columnDefinition = "VARCHAR(100) COMMENT '컨텐트'")
    private String content;

    @Column(columnDefinition = "bigint DEFAULT 0 COMMENT '조회 수'")
    private long viewCount;

    @Column(nullable = false, columnDefinition = "boolean DEFAULT 0 COMMENT '삭제여부'")
    private boolean isDeleted;

    @Column(nullable = false, columnDefinition = "datetime DEFAULT now() COMMENT '삭제일'")
    private LocalDateTime deleteDate;

    @Column(nullable = false, columnDefinition = "datetime DEFAULT now() COMMENT '발행일'")
    private LocalDateTime issueDate;

    @Column(nullable = false, columnDefinition = "datetime DEFAULT now() COMMENT '등록일'")
    private LocalDateTime registerDate;

}
