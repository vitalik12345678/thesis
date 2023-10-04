
CREATE TYPE approve_direction AS ENUM ('STUDENT','TEACHER');
ALTER TABLE teacher_student_request ADD approve_direction approve_direction;

ALTER TABLE comment ADD from_type approve_direction;

