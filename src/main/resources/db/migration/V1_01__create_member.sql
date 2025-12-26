CREATE TABLE member (
    member_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    email TEXT NOT NULL UNIQUE,
    password TEXT,
    tel TEXT UNIQUE,
    name TEXT NOT NULL,
    address TEXT NOT NULL,
    status TEXT NOT NULL,
    profile TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ
);