CREATE TABLE payment_history (
    payment_history_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    amount BIGINT NOT NULL,

    payment_id BIGINT NOT NULL,

    receipt_url TEXT,

    type TEXT NOT NULL,
    status TEXT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_payment_history_payment
        FOREIGN KEY (payment_id)
        REFERENCES payment(payment_id),

    CONSTRAINT uk_payment_history_receipt_url
        UNIQUE (receipt_url)
);
