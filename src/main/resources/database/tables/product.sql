CREATE TABLE product
(
    id                BIGSERIAL,
    name              VARCHAR(256),
    category_id       BIGINT NOT NULL REFERENCES category (id),
    description       VARCHAR(256),
    price             BIGINT,
    brand             VARCHAR(256),
    version           DOUBLE PRECISION,

    CONSTRAINT pk_product PRIMARY KEY (id)
);
