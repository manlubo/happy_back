CREATE TABLE member_provider (
    member_provider_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    member_id BIGINT NOT NULL,

    provider_name TEXT NOT NULL,
    provider_uuid TEXT NOT NULL,
    email TEXT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_member_provider_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id),

    CONSTRAINT uk_member_provider_provider_uuid
        UNIQUE (provider_uuid)
);
