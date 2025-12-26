CREATE TABLE member_token (
    member_token_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    member_id BIGINT NOT NULL,
    token TEXT NOT NULL,
    expired_at TIMESTAMPTZ NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_member_token_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id),

    CONSTRAINT uk_member_token_member
        UNIQUE (member_id),

    CONSTRAINT uk_member_token_token
        UNIQUE (token)
);
