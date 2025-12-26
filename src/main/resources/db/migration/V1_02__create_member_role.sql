CREATE TABLE member_role (
    member_id BIGINT NOT NULL,
    role TEXT NOT NULL,

    CONSTRAINT pk_member_role
        PRIMARY KEY (member_id, role),

    CONSTRAINT fk_member_role_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id)
);