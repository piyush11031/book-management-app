create table book
(
    archived           bit,
    created_by         integer     not null,
    last_modified_by   integer,
    sharable           bit,
    created_date       datetime(6) not null,
    id                 bigint      not null auto_increment,
    last_modified_date datetime(6),
    user_id            bigint,
    author_name        varchar(255),
    book_cover         varchar(255),
    isbn               varchar(255),
    synopsis           varchar(255),
    title              varchar(255),
    primary key (id)
) engine = InnoDB;
create table book_transaction_history
(
    created_by         integer     not null,
    last_modified_by   integer,
    return_approved    bit,
    returned           bit,
    book_id            bigint,
    created_date       datetime(6) not null,
    id                 bigint      not null auto_increment,
    last_modified_date datetime(6),
    user_id            bigint,
    primary key (id)
) engine = InnoDB;
create table feed_back
(
    created_by         integer     not null,
    last_modified_by   integer,
    book_id            bigint,
    created_date       datetime(6) not null,
    id                 bigint      not null auto_increment,
    last_modified_date datetime(6),
    comment            varchar(255),
    note               varchar(255),
    primary key (id)
) engine = InnoDB;
create table role
(
    id        bigint not null auto_increment,
    authority varchar(255),
    primary key (id)
) engine = InnoDB;
create table token
(
    created_at   datetime(6),
    expires_at   datetime(6),
    id           bigint not null auto_increment,
    user_id      bigint,
    validated_at datetime(6),
    token        varchar(255),
    primary key (id)
) engine = InnoDB;
create table user
(
    account_locked bit,
    date_of_birth  date,
    enabled        bit,
    id             bigint not null auto_increment,
    email          varchar(255),
    first_name     varchar(255),
    last_name      varchar(255),
    password       varchar(255),
    primary key (id)
) engine = InnoDB;
create table user_role
(
    role_id bigint not null,
    user_id bigint not null,
    primary key (role_id, user_id)
) engine = InnoDB;
alter table user
    add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);
alter table book
    add constraint FK1wxwagv6cm3vjrxqhmv884hir foreign key (user_id) references user (id);
alter table book_transaction_history
    add constraint FKetks95hi6ay47e16sj6vdv9g9 foreign key (book_id) references book (id);
alter table book_transaction_history
    add constraint FKub434lpx9i0sdwov7y4g7y09 foreign key (user_id) references user (id);
alter table feed_back
    add constraint FKe247ojst1bkwm62bcl03n7o1m foreign key (book_id) references book (id);
alter table token
    add constraint FKe32ek7ixanakfqsdaokm4q9y2 foreign key (user_id) references user (id);
alter table user_role
    add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role (id);
alter table user_role
    add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);

    create table book (
        archived bit,
        created_by integer not null,
        last_modified_by integer,
        sharable bit,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        author_name varchar(255),
        book_cover varchar(255),
        isbn varchar(255),
        synopsis varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table book_transaction_history (
        created_by integer not null,
        last_modified_by integer,
        return_approved bit,
        returned bit,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table feed_back (
        created_by integer not null,
        last_modified_by integer,
        book_id bigint,
        created_date datetime(6) not null,
        id bigint not null auto_increment,
        last_modified_date datetime(6),
        comment varchar(255),
        note varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table role (
        id bigint not null auto_increment,
        authority varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table token (
        created_at datetime(6),
        expires_at datetime(6),
        id bigint not null auto_increment,
        user_id bigint,
        validated_at datetime(6),
        token varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
        account_locked bit,
        date_of_birth date,
        enabled bit,
        id bigint not null auto_increment,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user_role (
        role_id bigint not null,
        user_id bigint not null,
        primary key (role_id, user_id)
    ) engine=InnoDB;

    alter table user 
       add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table book 
       add constraint FK1wxwagv6cm3vjrxqhmv884hir 
       foreign key (user_id) 
       references user (id);

    alter table book_transaction_history 
       add constraint FKetks95hi6ay47e16sj6vdv9g9 
       foreign key (book_id) 
       references book (id);

    alter table book_transaction_history 
       add constraint FKub434lpx9i0sdwov7y4g7y09 
       foreign key (user_id) 
       references user (id);

    alter table feed_back 
       add constraint FKe247ojst1bkwm62bcl03n7o1m 
       foreign key (book_id) 
       references book (id);

    alter table token 
       add constraint FKe32ek7ixanakfqsdaokm4q9y2 
       foreign key (user_id) 
       references user (id);

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role (id);

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user (id);
