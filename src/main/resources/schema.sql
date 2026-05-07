CREATE TABLE student (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE teacher (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         subject VARCHAR(100) NOT NULL
);

CREATE TABLE student_teacher (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 student_id BIGINT NOT NULL,
                                 teacher_id BIGINT NOT NULL,

                                 CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(id),
                                 CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(id),
                                 CONSTRAINT uq_student_teacher UNIQUE (student_id, teacher_id)
);