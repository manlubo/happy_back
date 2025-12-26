CREATE TABLE member_term (
    member_term_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    member_id BIGINT NOT NULL,
    term_id BIGINT NOT NULL,

    agreed BOOLEAN NOT NULL,
    version TEXT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ,

    CONSTRAINT fk_member_term_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id),

    CONSTRAINT fk_member_term_term
        FOREIGN KEY (term_id)
        REFERENCES term(term_id)
);
