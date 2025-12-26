CREATE TABLE donation_round (
    donation_round_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    donation_id BIGINT NOT NULL,

    round INTEGER NOT NULL,
    current_amount BIGINT NOT NULL DEFAULT 0,
    target_amount BIGINT NOT NULL,

    expired_at TIMESTAMPTZ,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_donation_round_donation
        FOREIGN KEY (donation_id)
        REFERENCES donation(donation_id)
);
