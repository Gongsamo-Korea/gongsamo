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
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long bannerId;

    @Column(name = "banner_text", nullable = false, columnDefinition = "TEXT")
    private String bannerText;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(name = "hyperlink_url", columnDefinition = "TEXT")
    private String hyperLinkUrl;

    @Column(name = "start_dt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDt;

    @Column(name = "end_dt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDt;

    @Column(name = "use_yn", nullable = false)
    private boolean useYn;

    @Column(name = "exposure_order")
    private Integer exposureOrder;

    @Column(name = "register_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerDate;

    @Column(name = "view_count")
    private Long viewCount;


}
