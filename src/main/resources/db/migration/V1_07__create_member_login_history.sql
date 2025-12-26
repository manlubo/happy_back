CREATE TABLE member_login_history (
    login_history_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    member_id BIGINT NOT NULL,

    ip TEXT NOT NULL,
    device TEXT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_member_login_history_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id)
);
