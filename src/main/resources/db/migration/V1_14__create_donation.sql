CREATE TABLE donation (
    donation_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    member_id BIGINT NOT NULL,
    board_id BIGINT NOT NULL,

    status TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ,

    CONSTRAINT fk_donation_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id),

    CONSTRAINT fk_donation_board
        FOREIGN KEY (board_id)
        REFERENCES board(board_id)
);
