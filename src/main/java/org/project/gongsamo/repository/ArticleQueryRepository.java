package org.project.gongsamo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.project.gongsamo.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.project.gongsamo.domain.QArticle.article;

@Repository
@RequiredArgsConstructor
public class ArticleQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    public Optional<Article> findById(Long articleId) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(article)
                .where(
                        article.isDeleted.eq(false),
                        article.articleId.eq(articleId)
                )
                .fetchOne());
    }

    public List<Article> search(String keyword, Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(article)
                .where(article.isDeleted.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public Article save(Article article) {
        entityManager.persist(article);
        return article;
    }
}
