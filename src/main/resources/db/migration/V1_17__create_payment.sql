CREATE TABLE payment (
    payment_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    amount BIGINT NOT NULL,

    member_id BIGINT NOT NULL,

    imp_uuid TEXT,

    method TEXT NOT NULL,
    status TEXT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ,

    CONSTRAINT fk_payment_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id),

    CONSTRAINT uk_payment_imp_uuid
        UNIQUE (imp_uuid)
);