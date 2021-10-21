create table organization
(
    id            bigint auto_increment primary key,
    about_us_text varchar(255) null,
    address       varchar(255) null,
    created_at    datetime(6)  null,
    deleted       bit          null,
    email         varchar(255) null,
    image         varchar(255) null,
    modified_at   datetime(6)  null,
    deleted_at    datetime(6)  null,
    name          varchar(255) null,
    phone         varchar(255) null,
    welcome_text  varchar(255) null,
    facebook_url  varchar(255) null,
    instagram_url varchar(255) null,
    twitter_url   varchar(255) null,
    linkedin_url varchar(255) null
);

create table category
(
    id          bigint auto_increment primary key,
    created_at  datetime(6)  null,
    deleted     bit          not null,
    deleted_at  datetime(6)  null,
    description varchar(255) not null,
    image       varchar(255) not null,
    modified_at datetime(6)  null,
    name        varchar(255) not null,
    organization_id bigint   not null,
    constraint fk_cat_organization_id
        foreign key (organization_id) references organization (id)
);

create table member
(
    id            bigint auto_increment primary key,
    created_at    datetime(6)  null,
    deleted       bit          null,
    deleted_at    datetime(6)  null,
    description   varchar(255) null,
    facebook_url  varchar(255) null,
    image         varchar(255) not null,
    instagram_url varchar(255) null,
    linkedin_url  varchar(255) null,
    modified_at   datetime(6)  null,
    name          varchar(255) not null,
    organization_id bigint   not null,
    constraint fk_organization_id_members
        foreign key (organization_id) references organization (id)
);

create table news
(
    id          bigint auto_increment primary key,
    content     longtext     not null,
    created_at  datetime(6)  null,
    deleted     bit          not null,
    deleted_at  datetime(6)  null,
    image       varchar(255) not null,
    modified_at datetime(6)  null,
    name        varchar(255) not null,
    category_id bigint       not null,
    organization_id bigint   not null,
    constraint FKl1qu8ha8360rhlfljtujicwu1
        foreign key (category_id) references category (id),
    constraint fk_organization_id
        foreign key (organization_id) references organization (id)
);


create table role
(
    id          bigint auto_increment primary key,
    created_at  datetime(6)  null,
    description varchar(255) null,
    modified_at datetime(6)  null,
    deleted_at  datetime(6)  null,
    name        varchar(255) null
);

create table slide
(
    id              bigint auto_increment primary key,
    image_url       varchar(255) null,
    `order`         int          null,
    text            varchar(255) null,
    organization_id bigint       not null,
    constraint FKbyn9nhchmcqlyxf9jjekp0mwk
        foreign key (organization_id) references organization (id)
);

create table testimonial
(
    id          bigint auto_increment primary key,
    content     varchar(255) null,
    created_at  datetime(6)  null,
    deleted_at  datetime(6)  null,
    image       varchar(255) null,
    deleted     bit          null,
    modified_at datetime(6)  null,
    name        varchar(255) null
);

create table user
(
    id              bigint auto_increment primary key,
    created_at      datetime(6)  null,
    deleted         bit          not null,
    deleted_at      datetime(6)  null,
    email           varchar(255) not null unique,
    first_name      varchar(255) not null,
    last_name       varchar(255) not null,
    modified_at     datetime(6)  null,
    password        varchar(255) not null,
    photo           varchar(255) null,
    role_id         bigint       not null,
    organization_id bigint   not null,
    constraint FK9usot4gododq1u90duvulb92d
        foreign key (role_id) references role (id),
    constraint fk_organization_id_user
        foreign key (organization_id) references organization (id)
);

create table activity
(
    id            bigint auto_increment primary key,
    content       varchar(255) not null,
    create_date   datetime(6)  null,
    deleted_at    datetime(6)  null,
    deleted       bit          not null,
    image         varchar(255) not null,
    modified_date datetime(6)  null,
    name          varchar(255) not null,
    organization_id bigint   not null,
    constraint fk_organization_id_activity
        foreign key (organization_id) references organization (id)
);
create table contact
(
    id          bigint auto_increment primary key,
    created_at  datetime(6)  null,
    deleted     bit          DEFAULT 0 not null,
    deleted_at  datetime(6)  null,
    name varchar(255) not null,
    phone       varchar(255) not null,
    modified_at datetime(6)  null,
    message        varchar(255) not null,
    email        varchar(255) not null,
    organization_id bigint   not null,
    constraint fk_contact_organization_id
        foreign key (organization_id) references organization (id)
);
