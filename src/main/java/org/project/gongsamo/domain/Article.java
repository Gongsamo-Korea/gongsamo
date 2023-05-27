package org.project.gongsamo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(exclude = "tags")
@ToString
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long articleId;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '발행 호수'")
    private String issueNumber;

    @Column(columnDefinition = "TEXT COMMENT '목차'")
    private String tableOfContent;

    @Column(nullable = false, columnDefinition = "VARCHAR(100) COMMENT '제목'")
    private String title;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '썸네일 URL'")
    private String thumbnailUrl;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT '컨텐츠'")
    private String content;

    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '조회 수'")
    private long viewCount;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleTag> tags = new ArrayList<>();

    @Column(columnDefinition = "DATETIME COMMENT '삭제일'")
    private LocalDateTime deleteDate;

    /**
     * generated column
     * delete_date 값에 따라 자동으로 계산된다.
     */
    @Generated(value = GenerationTime.ALWAYS)
    @Column(insertable = false, updatable = false, columnDefinition = "TINYINT(1) AS (CASE WHEN delete_date IS NOT NULL THEN 1 ELSE 0 END) COMMENT '삭제 여부'")
    private boolean isDeleted;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT NOW() COMMENT '발행일'")
    private LocalDateTime issueDate;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT NOW() COMMENT '등록일'")
    private LocalDateTime registerDate;
}
