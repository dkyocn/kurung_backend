package com.kurung.chatbot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatbotEntity is a Querydsl query type for ChatbotEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatbotEntity extends EntityPathBase<ChatbotEntity> {

    private static final long serialVersionUID = 156564929L;

    public static final QChatbotEntity chatbotEntity = new QChatbotEntity("chatbotEntity");

    public final BooleanPath answer = createBoolean("answer");

    public final NumberPath<Integer> chatbotId = createNumber("chatbotId", Integer.class);

    public final DateTimePath<java.util.Date> conversationTime = createDateTime("conversationTime", java.util.Date.class);

    public final BooleanPath question = createBoolean("question");

    public final StringPath userUuid = createString("userUuid");

    public QChatbotEntity(String variable) {
        super(ChatbotEntity.class, forVariable(variable));
    }

    public QChatbotEntity(Path<? extends ChatbotEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatbotEntity(PathMetadata metadata) {
        super(ChatbotEntity.class, metadata);
    }

}

