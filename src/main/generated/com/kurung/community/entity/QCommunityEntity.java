package com.kurung.community.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityEntity is a Querydsl query type for CommunityEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityEntity extends EntityPathBase<CommunityEntity> {

    private static final long serialVersionUID = -1027936639L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityEntity communityEntity = new QCommunityEntity("communityEntity");

    public final com.kurung.common.entity.QBaseEntity _super = new com.kurung.common.entity.QBaseEntity(this);

    public final EnumPath<com.kurung.common.enumeration.HealthType> category = createEnum("category", com.kurung.common.enumeration.HealthType.class);

    public final ListPath<CommentEntity, QCommentEntity> comment = this.<CommentEntity, QCommentEntity>createList("comment", CommentEntity.class, QCommentEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> communityId = createNumber("communityId", Integer.class);

    public final StringPath content = createString("content");

    //inherited
    public final DatePath<java.sql.Date> createdAt = _super.createdAt;

    public final StringPath title = createString("title");

    //inherited
    public final DatePath<java.sql.Date> updatedAt = _super.updatedAt;

    public final com.kurung.user.entity.QUserEntity user;

    public QCommunityEntity(String variable) {
        this(CommunityEntity.class, forVariable(variable), INITS);
    }

    public QCommunityEntity(Path<? extends CommunityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityEntity(PathMetadata metadata, PathInits inits) {
        this(CommunityEntity.class, metadata, inits);
    }

    public QCommunityEntity(Class<? extends CommunityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

