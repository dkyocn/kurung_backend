package com.kurung.diagnosis.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHealthQuestionEntity is a Querydsl query type for HealthQuestionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealthQuestionEntity extends EntityPathBase<HealthQuestionEntity> {

    private static final long serialVersionUID = -36507720L;

    public static final QHealthQuestionEntity healthQuestionEntity = new QHealthQuestionEntity("healthQuestionEntity");

    public final EnumPath<com.kurung.diagnosis.enumeration.Category> category = createEnum("category", com.kurung.diagnosis.enumeration.Category.class);

    public final ListPath<HealthOptionEntity, QHealthOptionEntity> healthOption = this.<HealthOptionEntity, QHealthOptionEntity>createList("healthOption", HealthOptionEntity.class, QHealthOptionEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> isMultiple = createNumber("isMultiple", Integer.class);

    public final NumberPath<Integer> questionCode = createNumber("questionCode", Integer.class);

    public final NumberPath<Integer> questionId = createNumber("questionId", Integer.class);

    public final StringPath questionText = createString("questionText");

    public QHealthQuestionEntity(String variable) {
        super(HealthQuestionEntity.class, forVariable(variable));
    }

    public QHealthQuestionEntity(Path<? extends HealthQuestionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHealthQuestionEntity(PathMetadata metadata) {
        super(HealthQuestionEntity.class, metadata);
    }

}

