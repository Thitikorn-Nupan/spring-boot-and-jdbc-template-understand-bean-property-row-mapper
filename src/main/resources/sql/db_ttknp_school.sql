create table STUDENTS
(
    SID       BIGINT auto_increment
        primary key,
    FULL_NAME CHARACTER VARYING(255)
        constraint STUDENTS_PK
            unique,
    BIRTHDAY  DATE,
    LEVEL     CHARACTER VARYING(2)
);

create table TEACHERS
(
    TID       BIGINT auto_increment,
    FULL_NAME CHARACTER VARYING(255),
    BIRTHDAY  DATE,
    CLASS_ID  CHARACTER VARYING(10),
    constraint TEACHERS_PK
        primary key (TID)
);

