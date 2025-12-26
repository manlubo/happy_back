CREATE TABLE attach (
    attach_uuid UUID PRIMARY KEY,

    path TEXT,
    image BOOLEAN,
    origin TEXT,

    member_id BIGINT,
    board_id BIGINT,

    is_thumbnail BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_attach_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id),

    CONSTRAINT fk_attach_board
        FOREIGN KEY (board_id)
        REFERENCES board(board_id),
    
    CONSTRAINT chk_attach_target
        CHECK (
            (member_id IS NOT NULL AND board_id IS NULL)
            OR (member_id IS NULL AND board_id IS NOT NULL)
        )
);
