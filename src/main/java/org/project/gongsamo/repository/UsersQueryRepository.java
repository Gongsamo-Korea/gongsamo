package org.project.gongsamo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.project.gongsamo.domain.Users;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.project.gongsamo.domain.QUsers.users;

@Repository
@RequiredArgsConstructor
public class UsersQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public Users findOneById(Long userId) {
        return jpaQueryFactory
                .selectFrom(users)
                .where(users.userId.eq(userId))
                .fetchOne();
    }

    public Optional<Users> findByEmail(String email) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(users)
                .where(users.email.eq(email))
                .fetchOne());
    }

    public Optional<Users> findByNickName(String nickName) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(users)
                .where(users.nickname.eq(nickName))
                .fetchOne());
    }

    public Users save(Users user){
        em.persist(user);

        return user;
    }
}
