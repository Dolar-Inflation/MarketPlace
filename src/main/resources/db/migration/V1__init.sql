--
-- PostgreSQL database cluster dump
--

-- Started on 2026-02-18 13:41:14

\restrict lBEAZD8lh26Ub5XMkxEJI0dlud7wuymmMIhm4x3vDIcJUY7t8rsaygmJwD1gE3u

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;

--
-- User Configurations
--






\unrestrict lBEAZD8lh26Ub5XMkxEJI0dlud7wuymmMIhm4x3vDIcJUY7t8rsaygmJwD1gE3u

--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

\restrict 4mkZeBEQQ8aIHoiyGMXjvA5RoAVFN8OIEBPbIiR3bb2vBUrgjv4jcQxZrAk4Nbc

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2026-02-18 13:41:14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2026-02-18 13:41:15

--
-- PostgreSQL database dump complete
--

\unrestrict 4mkZeBEQQ8aIHoiyGMXjvA5RoAVFN8OIEBPbIiR3bb2vBUrgjv4jcQxZrAk4Nbc

--
-- Database "marketplacedb" dump
--

--
-- PostgreSQL database dump
--

\restrict UOhbXNX7KeJ1VPXrOTWzwZAvD4vroLCDrXgUn4LOpYlUM2WtZB0RWCWKRZ5YEdf

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2026-02-18 13:41:15

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4990 (class 1262 OID 72290)
-- Name: marketplacedb; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE marketplacedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


\unrestrict UOhbXNX7KeJ1VPXrOTWzwZAvD4vroLCDrXgUn4LOpYlUM2WtZB0RWCWKRZ5YEdf
\connect marketplacedb
\restrict UOhbXNX7KeJ1VPXrOTWzwZAvD4vroLCDrXgUn4LOpYlUM2WtZB0RWCWKRZ5YEdf

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 72292)
-- Name: account; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.account (
    id bigint NOT NULL,
    password text NOT NULL,
    accountname text NOT NULL,
    acc_order_id bigint
);


--
-- TOC entry 219 (class 1259 OID 72291)
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 221 (class 1259 OID 80571)
-- Name: order_product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.order_product (
    id bigint NOT NULL,
    order_id bigint,
    product_id bigint
);


--
-- TOC entry 226 (class 1259 OID 80604)
-- Name: order_product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.order_product ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.order_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 223 (class 1259 OID 80577)
-- Name: orders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    amount character varying,
    acc_id bigint,
    product_id bigint
);


--
-- TOC entry 224 (class 1259 OID 80580)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.orders ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 80574)
-- Name: products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.products (
    id bigint NOT NULL,
    product_name character varying NOT NULL,
    price double precision,
    order_id bigint
);


--
-- TOC entry 225 (class 1259 OID 80594)
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.products ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4826 (class 2606 OID 72301)
-- Name: account account_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pk PRIMARY KEY (id);


--
-- TOC entry 4828 (class 2606 OID 80568)
-- Name: account account_unique; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_unique UNIQUE (accountname);


--
-- TOC entry 4830 (class 2606 OID 80610)
-- Name: order_product order_product_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_product
    ADD CONSTRAINT order_product_pk PRIMARY KEY (id);


--
-- TOC entry 4834 (class 2606 OID 80586)
-- Name: orders orders_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);


--
-- TOC entry 4832 (class 2606 OID 80600)
-- Name: products products_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pk PRIMARY KEY (id);


--
-- TOC entry 4835 (class 2606 OID 80589)
-- Name: account account_orders_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_orders_fk FOREIGN KEY (acc_order_id) REFERENCES public.orders(id);


--
-- TOC entry 4836 (class 2606 OID 80611)
-- Name: order_product order_product_orders_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_product
    ADD CONSTRAINT order_product_orders_fk FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 4837 (class 2606 OID 80616)
-- Name: order_product order_product_products_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_product
    ADD CONSTRAINT order_product_products_fk FOREIGN KEY (product_id) REFERENCES public.products(id);


-- Completed on 2026-02-18 13:41:15

--
-- PostgreSQL database dump complete
--

\unrestrict UOhbXNX7KeJ1VPXrOTWzwZAvD4vroLCDrXgUn4LOpYlUM2WtZB0RWCWKRZ5YEdf

-- Completed on 2026-02-18 13:41:15

--
-- PostgreSQL database cluster dump complete
--

