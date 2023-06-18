package org.project.gongsamo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.gongsamo.dto.BannerDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.project.gongsamo.domain.QBanner.banner;

@Repository
@RequiredArgsConstructor
public class BannerQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<BannerDto> findAllBanners() {
        LocalDateTime now = LocalDateTime.now();
        return jpaQueryFactory
                .select(Projections.constructor(BannerDto.class, banner.bannerText, banner.thumbnailUrl, banner.hyperLinkUrl, banner.exposureOrder))
                .from(banner)
                .where(banner.startDate.loe(now), banner.endDate.goe(now)
                        .and(banner.isDisplayed.isTrue()))
                .orderBy(banner.exposureOrder.asc())
                .fetch();
    }



}
