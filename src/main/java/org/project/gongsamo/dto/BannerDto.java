package org.project.gongsamo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BannerDto {
    private String bannerText;
    private String thumbnailUrl;
    private String hyperLinkUrl;
    private Integer exposureOrder;
}
