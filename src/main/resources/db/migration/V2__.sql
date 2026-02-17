ALTER TABLE public.orders
    DROP CONSTRAINT orders_products_fk;

ALTER TABLE public.products
    DROP CONSTRAINT products_account_fk;

ALTER TABLE public.order_product
    DROP CONSTRAINT uniontable_order_fk;

ALTER TABLE public.order_product
    DROP CONSTRAINT uniontable_products_fk;

DROP TABLE public.order_product CASCADE;

DROP TABLE public.orders CASCADE;

DROP TABLE public.products CASCADE;

ALTER TABLE public.account
    ALTER COLUMN accountname TYPE TEXT USING (accountname::TEXT);

ALTER TABLE public.account
    ALTER COLUMN password TYPE TEXT USING (password::TEXT);