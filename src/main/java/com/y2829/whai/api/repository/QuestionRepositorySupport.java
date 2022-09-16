package com.y2829.whai.api.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.y2829.whai.api.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    private final QQuestion qQuestion = QQuestion.question;

    public Page<Question> findByConditions(List<String> categories, String title, String content, String userName, Pageable pageable) {
        List<Question> list = jpaQueryFactory
                .selectFrom(qQuestion)
                .where(
                        inCategories(categories),
                        containsTitle(title),
                        containsContent(content),
                        eqUserName(userName)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(sortQuestionList(pageable))
                .fetch();

        return new PageImpl<>(list);
    }

    private BooleanExpression inCategories(List<String> categories) {
        return categories.isEmpty() ? null : qQuestion.categories.any().category.subject.in(categories);
    }

    private BooleanExpression containsTitle(String title) {
        return title == null ? null : qQuestion.title.contains(title);
    }

    private BooleanExpression containsContent(String content) {
        return content == null ? null : qQuestion.content.contains(content);
    }

    private BooleanExpression eqUserName(String userName) {
        return userName == null ? null : qQuestion.user.name.eq(userName);
    }

    private OrderSpecifier<?> sortQuestionList(Pageable pageable) {
//        정렬 기준 추가시 적용
//        if (!pageable.getSort().isEmpty()) {
//            for (Sort.Order order : pageable.getSort()) {
//                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
//
//                switch (order.getProperty()) {
//                    case "create"
//                }
//            }
//        }
        return new OrderSpecifier<>(Order.DESC, qQuestion.createAt);
    }

}
