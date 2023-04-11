CREATE USER IF NOT EXISTS sa PASSWORD 'root';
ALTER USER sa ADMIN TRUE;

CREATE TABLE department
(
    id            BIGINT PRIMARY KEY NOT NULL,
    code          VARCHAR(64) UNIQUE NOT NULL,
    name          VARCHAR(64)        NOT NULL,
    parent_code   VARCHAR(64)        NOT NULL,
    director_code VARCHAR(64)        NOT NULL,
    register_date DATE               NOT NULL,
    version       INT DEFAULT 0      NOT NULL
);

CREATE TABLE employee
(
    id              BIGINT PRIMARY KEY NOT NULL,
    code            VARCHAR(64) UNIQUE NOT NULL,
    name            VARCHAR(64)        NOT NULL,
    gender          TINYINT(1)         NOT NULL,
    birth           DATE               NOT NULL,
    mobile          VARCHAR(32)        NOT NULL,
    email           VARCHAR(64)        NOT NULL,
    department_code VARCHAR(64)        NOT NULL,
    title           VARCHAR(64)        NOT NULL,
    salary          DECIMAL(10, 2)     NOT NULL,
    onboard_date    DATE               NOT NULL,
    departure_date  DATE               NOT NULL,
    version         INT DEFAULT 0      NOT NULL
);

CREATE TABLE ref_department_manager
(
    id            BIGINT PRIMARY KEY NOT NULL,
    department_id BIGINT             NOT NULL,
    employee_id   BIGINT             NOT NULL
);

CREATE UNIQUE INDEX ux ON ref_department_manager (department_id, employee_id);
