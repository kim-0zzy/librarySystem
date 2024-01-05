package com.example.libraryProject.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBorrowHistory is a Querydsl query type for BorrowHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBorrowHistory extends EntityPathBase<BorrowHistory> {

    private static final long serialVersionUID = -1528404813L;

    public static final QBorrowHistory borrowHistory = new QBorrowHistory("borrowHistory");

    public final StringPath borrowedBookCode = createString("borrowedBookCode");

    public final StringPath borrowedBookName = createString("borrowedBookName");

    public final DatePath<java.time.LocalDate> borrowedDate = createDate("borrowedDate", java.time.LocalDate.class);

    public final StringPath borrowedMemberCode = createString("borrowedMemberCode");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath returnedDate = createString("returnedDate");

    public QBorrowHistory(String variable) {
        super(BorrowHistory.class, forVariable(variable));
    }

    public QBorrowHistory(Path<? extends BorrowHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBorrowHistory(PathMetadata metadata) {
        super(BorrowHistory.class, metadata);
    }

}

