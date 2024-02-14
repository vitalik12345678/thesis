CREATE TYPE approve_direction AS ENUM ('STUDENT','TEACHER');
ALTER TABLE teacher_student_request
    ADD approve_direction approve_direction;

CREATE TYPE approve_status AS ENUM ('APPROVED','WAITING','REJECTED');

ALTER TABLE document
    ADD approve_status approve_status DEFAULT 'WAITING';

ALTER TABLE teacher_student_request
    ADD head_approve boolean DEFAULT 'true';

CREATE TABLE teacher_stage_approve
(

    id         BIGSERIAL PRIMARY KEY,
    teacher_id BIGSERIAL REFERENCES teacher (teacher_id),
    stage_id   BIGSERIAL REFERENCES stage (stage_id)

);

CREATE TABLE comment
(
    comment_id   bigserial
        primary key,
    created_date timestamp(6),
    text         varchar(255),
    document_id  bigint
        constraint fkooerxu4oy4q0s0duwk3vtk74q
            references document,
    user_id bigint constraint comment_user_id_fk_key references users(user_id)
);

ALTER TABLE theme
    ADD COLUMN deadline_data DATE;

CREATE TABLE user_token
(

    id    BIGSERIAL PRIMARY KEY,
    email TEXT UNIQUE,
    token TEXT

)
