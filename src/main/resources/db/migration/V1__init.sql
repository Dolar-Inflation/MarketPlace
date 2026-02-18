

CREATE SEQUENCE public.account_id_seq
    START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE public.order_product_id_seq
    START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE public.orders_id_seq
    START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE SEQUENCE public.products_id_seq
    START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


-- === TABLES ===

CREATE TABLE public.account (
                                id bigint NOT NULL DEFAULT nextval('public.account_id_seq'),
                                password text NOT NULL,
                                accountname text NOT NULL,
                                acc_order_id bigint
);

CREATE TABLE public.order_product (
                                      id bigint NOT NULL DEFAULT nextval('public.order_product_id_seq'),
                                      order_id bigint,
                                      product_id bigint
);

CREATE TABLE public.orders (
                               id bigint NOT NULL DEFAULT nextval('public.orders_id_seq'),
                               amount varchar,
                               acc_id bigint,
                               product_id bigint
);

CREATE TABLE public.products (
                                 id bigint NOT NULL DEFAULT nextval('public.products_id_seq'),
                                 product_name varchar NOT NULL,
                                 price double precision,
                                 order_id bigint
);


-- === PRIMARY KEYS ===

ALTER TABLE public.account
    ADD CONSTRAINT account_pk PRIMARY KEY (id);

ALTER TABLE public.order_product
    ADD CONSTRAINT order_product_pk PRIMARY KEY (id);

ALTER TABLE public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);

ALTER TABLE public.products
    ADD CONSTRAINT products_pk PRIMARY KEY (id);


-- === UNIQUE CONSTRAINTS ===

ALTER TABLE public.account
    ADD CONSTRAINT account_unique UNIQUE (accountname);


-- === FOREIGN KEYS ===

ALTER TABLE public.account
    ADD CONSTRAINT account_orders_fk
        FOREIGN KEY (acc_order_id) REFERENCES public.orders(id);

ALTER TABLE public.order_product
    ADD CONSTRAINT order_product_orders_fk
        FOREIGN KEY (order_id) REFERENCES public.orders(id);

ALTER TABLE public.order_product
    ADD CONSTRAINT order_product_products_fk
        FOREIGN KEY (product_id) REFERENCES public.products(id);
