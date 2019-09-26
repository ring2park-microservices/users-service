drop table T_USER if exists;

create table T_USER (ID bigint identity primary key, USERNAME varchar(255) not null,
                        EMAIL varchar(255) default null, ENABLED boolean not null,
                        MOBILE varchar(255) default null, NAME varchar(255) default null,
                        PASSWORD varchar(255) default null, VERIFY_CODE varchar(255) default null,
                        unique(USERNAME), unique(EMAIL), unique(MOBILE));
