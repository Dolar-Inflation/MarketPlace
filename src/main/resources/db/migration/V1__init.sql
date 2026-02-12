--
-- PostgreSQL database cluster dump
--

-- Started on 2026-02-12 15:44:50

\restrict PEeP7I8VMzgM8cT0lf7Q4Wc7PWluCkuwD7OhfBOgoLPYzcHaKyyt1mDtwcBJfO4

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








\unrestrict PEeP7I8VMzgM8cT0lf7Q4Wc7PWluCkuwD7OhfBOgoLPYzcHaKyyt1mDtwcBJfO4

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

\restrict lH1CeG5vAQsMbpRbb12I0tLco1JSOd8UpO3C47FC7ccV1NczkM9fWxHvZnOhz6v

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2026-02-12 15:44:51

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

-- Completed on 2026-02-12 15:44:51

--
-- PostgreSQL database dump complete
--

\unrestrict lH1CeG5vAQsMbpRbb12I0tLco1JSOd8UpO3C47FC7ccV1NczkM9fWxHvZnOhz6v

--
-- Database "marketplacedb" dump
--

--
-- PostgreSQL database dump
--

\restrict XOowOLUw7VO3wW9zri9FHzd1iM8QMbMYr9OPh0crONGweRa3lVGPucBx8PUcdJa

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2026-02-12 15:44:51

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
-- TOC entry 4977 (class 1262 OID 72290)
-- Name: marketplacedb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE marketplacedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE marketplacedb OWNER TO postgres;

\unrestrict XOowOLUw7VO3wW9zri9FHzd1iM8QMbMYr9OPh0crONGweRa3lVGPucBx8PUcdJa
\connect marketplacedb
\restrict XOowOLUw7VO3wW9zri9FHzd1iM8QMbMYr9OPh0crONGweRa3lVGPucBx8PUcdJa

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
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    id bigint NOT NULL,
    password character varying NOT NULL,
    accountname character varying NOT NULL
);


ALTER TABLE public.account OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 72291)
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
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
-- TOC entry 223 (class 1259 OID 80491)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    amountprice money NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 80490)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
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
-- TOC entry 221 (class 1259 OID 80487)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 4820 (class 2606 OID 72301)
-- Name: account account_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pk PRIMARY KEY (id);


--
-- TOC entry 4822 (class 2606 OID 80485)
-- Name: account account_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_unique UNIQUE (accountname);


--
-- TOC entry 4824 (class 2606 OID 80497)
-- Name: orders orders_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);


-- Completed on 2026-02-12 15:44:51

--
-- PostgreSQL database dump complete
--

\unrestrict XOowOLUw7VO3wW9zri9FHzd1iM8QMbMYr9OPh0crONGweRa3lVGPucBx8PUcdJa

-- Completed on 2026-02-12 15:44:51

--
-- PostgreSQL database cluster dump complete
--

