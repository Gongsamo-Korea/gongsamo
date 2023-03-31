package org.project.gongsamo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.gongsamo.domain.Users;
import org.springframework.stereotype.Repository;

import static org.project.gongsamo.domain.QUsers.users;

@Repository
@RequiredArgsConstructor
public class UsersQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Users findOneById(Long userId) {
        return jpaQueryFactory
                .selectFrom(users)
                .where(users.userId.eq(userId))
                .fetchOne();
    }
}
