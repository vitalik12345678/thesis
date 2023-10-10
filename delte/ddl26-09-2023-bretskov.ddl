
CREATE TYPE approve_direction AS ENUM ('STUDENT','TEACHER');
ALTER TABLE teacher_student_request ADD approve_direction approve_direction;

ALTER TABLE comment ADD from_type approve_direction;

CREATE TYPE approve_status AS ENUM ('APPROVED','WAITING','REJECTED');

ALTER TABLE document ADD approve_status approve_status DEFAULT 'WAITING';




