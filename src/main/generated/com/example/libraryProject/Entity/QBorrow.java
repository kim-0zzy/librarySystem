package com.example.libraryProject.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBorrow is a Querydsl query type for Borrow
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBorrow extends EntityPathBase<Borrow> {

    private static final long serialVersionUID = 110448801L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBorrow borrow = new QBorrow("borrow");

    public final QBook book;

    public final DatePath<java.time.LocalDate> borrowDate = createDate("borrowDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> limitDate = createDate("limitDate", java.time.LocalDate.class);

    public final QMember member;

    public QBorrow(String variable) {
        this(Borrow.class, forVariable(variable), INITS);
    }

    public QBorrow(Path<? extends Borrow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBorrow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBorrow(PathMetadata metadata, PathInits inits) {
        this(Borrow.class, metadata, inits);
    }

    public QBorrow(Class<? extends Borrow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

