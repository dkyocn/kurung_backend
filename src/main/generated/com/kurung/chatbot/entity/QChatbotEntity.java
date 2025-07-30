package com.kurung.chatbot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatbotEntity is a Querydsl query type for ChatbotEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatbotEntity extends EntityPathBase<ChatbotEntity> {

    private static final long serialVersionUID = 156564929L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatbotEntity chatbotEntity = new QChatbotEntity("chatbotEntity");

    public final StringPath answer = createString("answer");

    public final NumberPath<Integer> chatbotId = createNumber("chatbotId", Integer.class);

    public final DatePath<java.sql.Date> conversationTime = createDate("conversationTime", java.sql.Date.class);

    public final StringPath question = createString("question");

    public final com.kurung.user.entity.QUserEntity user;

    public QChatbotEntity(String variable) {
        this(ChatbotEntity.class, forVariable(variable), INITS);
    }

    public QChatbotEntity(Path<? extends ChatbotEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatbotEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatbotEntity(PathMetadata metadata, PathInits inits) {
        this(ChatbotEntity.class, metadata, inits);
    }

    public QChatbotEntity(Class<? extends ChatbotEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

