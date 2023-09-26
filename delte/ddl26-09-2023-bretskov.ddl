
CREATE TYPE approve_direction AS ENUM ('STUDENT','TEACHER');
ALTER TABLE teacher_student_request ADD approve_direction approve_direction;


