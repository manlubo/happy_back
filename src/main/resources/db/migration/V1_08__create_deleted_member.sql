CREATE TABLE deleted_member (
    deleted_member_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    member_id BIGINT NOT NULL,
    email TEXT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_deleted_member_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id)
);
