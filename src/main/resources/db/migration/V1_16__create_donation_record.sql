CREATE TABLE donation_record (
    donation_record_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    donation_round_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,

    amount BIGINT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_donation_record_donation_round
        FOREIGN KEY (donation_round_id)
        REFERENCES donation_round(donation_round_id),

    CONSTRAINT fk_donation_record_member
        FOREIGN KEY (member_id)
        REFERENCES member(member_id)
);
