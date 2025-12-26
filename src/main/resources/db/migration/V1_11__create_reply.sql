CREATE TABLE reply (
    reply_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    content TEXT NOT NULL,

    parent_reply_id BIGINT,
    board_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ,

    CONSTRAINT fk_reply_parent
        FOREIGN KEY (parent_reply_id)
        REFERENCES reply(reply_id),

    CONSTRAINT fk_reply_board
        FOREIGN KEY (board_id)
        REFERENCES board(board_id),

    CONSTRAINT fk_reply_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id)
);
