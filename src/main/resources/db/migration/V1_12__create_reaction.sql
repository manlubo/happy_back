CREATE TABLE reaction (
    reaction_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    member_id BIGINT NOT NULL,
    board_id BIGINT,
    reply_id BIGINT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_reaction_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id),

    CONSTRAINT fk_reaction_board
        FOREIGN KEY (board_id)
        REFERENCES board(board_id),

    CONSTRAINT fk_reaction_reply
        FOREIGN KEY (reply_id)
        REFERENCES reply(reply_id),

    CONSTRAINT chk_reaction_target
        CHECK (
            (board_id IS NOT NULL AND reply_id IS NULL)
            OR (board_id IS NULL AND reply_id IS NOT NULL)
        )
);
