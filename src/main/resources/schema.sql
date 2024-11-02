create table course (
    id bigint generated by default as identity,
    title varchar(255) not null,
    creater_id bigint not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

create table ns_user (
    id bigint generated by default as identity,
    user_id varchar(20) not null,
    password varchar(20) not null,
    name varchar(20) not null,
    email varchar(50),
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

create table question (
    id bigint generated by default as identity,
    created_at timestamp not null,
    updated_at timestamp,
    contents clob,
    deleted boolean not null,
    title varchar(100) not null,
    writer_id bigint,
    primary key (id)
);

create table answer (
    id bigint generated by default as identity,
    created_at timestamp not null,
    updated_at timestamp,
    contents clob,
    deleted boolean not null,
    question_id bigint,
    writer_id bigint,
    primary key (id)
);

create table delete_history (
    id bigint not null,
    content_id bigint,
    content_type varchar(255),
    created_date timestamp,
    deleted_by_id bigint,
    primary key (id)
);

create table cover_image (
    id bigint generated by default as identity,
    size int not null,
    type varchar(5) not null,
    width double precision not null,
    height double precision not null,
    primary key (id)
);

create table session (
    id bigint generated by default as identity,
    image_id bigint,
    start_date timestamp not null,
    end_date timestamp not null,
    type varchar(5) not null,
    maximum_enrollment int not null,
    tuition int not null,
    status varchar(11) not null,
    course_id bigint,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id),
    foreign key (image_id) references session_image (id),
    foreign key (course_id) references course (id)
);
ALTER TABLE session ADD COLUMN recruitment_status varchar(20) not null;

create table session_registration_info (
    session_id bigint,
    user_id bigint,
    registration_status varchar(20) not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (session_id, user_id),
    foreign key (session_id) references session (id),
    foreign key (user_id) references ns_user (id)
);

create table session_cover_image (
    session_id bigint,
    cover_image_id bigint,
    primary key (session_id, cover_image_id),
    foreign key (session_id) references session (id),
    foreign key (cover_image_id) references cover_image (id)
);