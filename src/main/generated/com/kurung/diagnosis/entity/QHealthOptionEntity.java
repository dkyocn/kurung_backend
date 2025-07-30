package com.kurung.diagnosis.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHealthOptionEntity is a Querydsl query type for HealthOptionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealthOptionEntity extends EntityPathBase<HealthOptionEntity> {

    private static final long serialVersionUID = 1656298695L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHealthOptionEntity healthOptionEntity = new QHealthOptionEntity("healthOptionEntity");

    public final ListPath<HealthAnswerEntity, QHealthAnswerEntity> answers = this.<HealthAnswerEntity, QHealthAnswerEntity>createList("answers", HealthAnswerEntity.class, QHealthAnswerEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> optionCode = createNumber("optionCode", Integer.class);

    public final NumberPath<Integer> optionId = createNumber("optionId", Integer.class);

    public final StringPath optionText = createString("optionText");

    public final QHealthQuestionEntity question;

    public final NumberPath<Integer> textOption = createNumber("textOption", Integer.class);

    public QHealthOptionEntity(String variable) {
        this(HealthOptionEntity.class, forVariable(variable), INITS);
    }

    public QHealthOptionEntity(Path<? extends HealthOptionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHealthOptionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHealthOptionEntity(PathMetadata metadata, PathInits inits) {
        this(HealthOptionEntity.class, metadata, inits);
    }

    public QHealthOptionEntity(Class<? extends HealthOptionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new QHealthQuestionEntity(forProperty("question")) : null;
    }

}

