CREATE TABLE product
(
    id               BIGSERIAL,
    name             VARCHAR(64),
    description      VARCHAR(256),
    price            INTEGER(8) ,
    brand            VARCHAR(64),
    version          DOUBLE,
    category_id     BIGINT,

    CONSTRAINT pk_product PRIMARY KEY (id)
);
