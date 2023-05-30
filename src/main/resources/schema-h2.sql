create table STUDENT
(
    ID              int          not null AUTO_INCREMENT,
    STUDENT_NAME    varchar(100) not null,
    REGISTRATION_ID varchar(100) not null,
    COURSE_NAME     varchar(100) not null,
    ANNUAL_FEE      DOUBLE       not null,
    PAID_FEES       DOUBLE       null,
    PAYMENT_CHANNEL varchar(100) null,
    PRIMARY KEY (ID)
);

INSERT INTO STUDENT(ID,STUDENT_NAME,REGISTRATION_ID,COURSE_NAME,ANNUAL_FEE,PAID_FEES,PAYMENT_CHANNEL) VALUES ( 1,'Student test', '123/1728/2023', 'BSC ARTS', 87000.0, 0.0, null );
COMMIT ;