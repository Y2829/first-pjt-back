package com.y2829.whai.api.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.y2829.whai.api.entity.QCategory;
import com.y2829.whai.api.entity.QQuestion;
import com.y2829.whai.api.entity.QQuestionCategory;
import com.y2829.whai.api.entity.Question;
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
    private final QCategory qCategory = QCategory.category;
    private final QQuestionCategory qQuestionCategory = QQuestionCategory.questionCategory;

    public Page<Question> findByCategoriesAndTitleLike(List<String> categories, String title, Pageable pageable) {
        List<Question> list = jpaQueryFactory
                .selectFrom(qQuestion)
                .leftJoin(qQuestionCategory).fetchJoin()
                .leftJoin(qCategory).fetchJoin()
                .where(
                        qCategory.subject.in(categories),
                        qQuestion.title.contains(title)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
//                .orderBy()
                .fetch();

        return new PageImpl<>(list);
    }

    public Page<Question> findByCategoriesAndContentLike(List<String> categories, String content, Pageable pageable) {
        List<Question> list = jpaQueryFactory
                .selectFrom(qQuestion)
                .leftJoin(qQuestionCategory).fetchJoin()
                .leftJoin(qCategory).fetchJoin()
                .where(
                        qCategory.subject.in(categories),
                        qQuestion.content.contains(content)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
//                .orderBy()
                .fetch();

        return new PageImpl<>(list);
    }

    public Page<Question> findByCategoriesAndUserNameLike(List<String> categories, String userName, Pageable pageable) {
        List<Question> list = jpaQueryFactory
                .selectFrom(qQuestion)
                .leftJoin(qQuestionCategory).fetchJoin()
                .leftJoin(qCategory).fetchJoin()
                .where(
                        qCategory.subject.in(categories),
                        qQuestion.user.name.eq(userName)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
//                .orderBy()
                .fetch();

        return new PageImpl<>(list);
    }

    // 전체 카테고리 처리 필요 ! 정렬도 !
    public Page<Question> findByCategories(List<String> categories, Pageable pageable) {
        List<Question> list = jpaQueryFactory
                .selectFrom(qQuestion)
                .leftJoin(qQuestionCategory).fetchJoin()
                .leftJoin(qCategory).fetchJoin()
                .where(qCategory.subject.in(categories))
                .offset(pageable.getOffset())
                .limit(pageable.getPageNumber())
//                .orderBy()
                .fetch();

        return new PageImpl<>(list);
    }

}
