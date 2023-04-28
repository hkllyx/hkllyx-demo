CREATE USER IF NOT EXISTS sa PASSWORD 'root';
ALTER USER sa ADMIN TRUE;

CREATE TABLE department
(
    id            BIGINT        NOT NULL
        PRIMARY KEY AUTO_INCREMENT,
    code          VARCHAR(64)   NOT NULL UNIQUE,
    name          VARCHAR(64)   NOT NULL,
    parent_id     BIGINT        NULL,
    register_date DATE          NOT NULL,
    version       INT DEFAULT 0 NOT NULL
);

CREATE TABLE employee
(
    id              BIGINT         NOT NULL
        PRIMARY KEY AUTO_INCREMENT,
    no              VARCHAR(64)    NOT NULL UNIQUE,
    name            VARCHAR(64)    NOT NULL,
    gender          TINYINT(1)     NOT NULL,
    mobile          VARCHAR(32)    NOT NULL,
    email           VARCHAR(64)    NOT NULL,
    department_code VARCHAR(64)    NOT NULL,
    title           VARCHAR(64)    NOT NULL,
    birth           DATE           NOT NULL,
    introduction    VARCHAR(64)    NULL,
    blog_uri        VARCHAR(64)    NULL,
    salary          DECIMAL(10, 2) NOT NULL,
    onboard_date    DATE           NOT NULL,
    departure_date  DATE           NULL,
    version         INT DEFAULT 0  NOT NULL
);

CREATE TABLE department_manager
(
    id            BIGINT NOT NULL
        PRIMARY KEY AUTO_INCREMENT,
    department_id BIGINT NOT NULL,
    employee_id   BIGINT NOT NULL
);

CREATE UNIQUE INDEX ux_department_employee ON department_manager (department_id, employee_id);



INSERT INTO department(id, code, name, parent_id, register_date)
VALUES (1, 'D001', '营销部', NULL, '2023-01-01'),
       (2, 'D002', '华南营销分部', 1, '2023-01-01');

INSERT INTO employee(id, no, name, gender, mobile, email, department_code, title, birth, salary, onboard_date)
VALUES (1, 'N001', 'zhangsan', 0, '18870786661', 'zhangsan@email.com', 'D001', '部长', '1998-01-01', 1000000.00, '2023-01-01'),
       (2, 'N002', 'lisi', 1, '18870786662', 'lisi@email.com', 'D002', '分部长', '1999-01-01', 500000.00, '2023-01-01'),
       (3, 'N003', 'wangwu', 1, '18870786663', 'wangwu@email.com', 'D002', '销售经理', '2001-01-01', 100000.00, '2023-01-01'),
       (4, 'N004', 'zhaoliu', 0, '18870786664', 'zhaoliu@email.com', 'D002', '销售经理', '2002-01-01', 100000.00, '2023-01-01');

INSERT INTO department_manager(department_id, employee_id)
VALUES (1, 1), (2, 2);