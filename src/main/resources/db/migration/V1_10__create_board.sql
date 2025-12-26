CREATE TABLE board (
    board_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    title TEXT NOT NULL,
    content TEXT NOT NULL,
    thumbnail_url TEXT,

    view_count INTEGER NOT NULL DEFAULT 0,
    status TEXT NOT NULL,

    category_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ,

    CONSTRAINT fk_board_category
        FOREIGN KEY (category_id)
        REFERENCES category(category_id),

    CONSTRAINT fk_board_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id)
);
