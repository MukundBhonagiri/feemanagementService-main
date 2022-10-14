# Fee Template details
-- Table: public.fees_template

-- DROP TABLE IF EXISTS public.fees_template;

CREATE TABLE IF NOT EXISTS public.fees_template
(
    fees_id integer NOT NULL,
    tenant_id uuid DEFAULT uuid_generate_v4(),
    school_id uuid DEFAULT uuid_generate_v4(),
    grade_ids character varying(200) COLLATE pg_catalog."default",
    fees_category character varying(200) COLLATE pg_catalog."default",
    fees_description character varying(200) COLLATE pg_catalog."default",
    term character varying(200) COLLATE pg_catalog."default",
    term_id uuid DEFAULT uuid_generate_v4(),
    due_date character varying COLLATE pg_catalog."default",
    to_pay double precision,
    notify_before integer,
    pay_frequency character varying(200) COLLATE pg_catalog."default",
    applicable_to character varying(200) COLLATE pg_catalog."default",
    created_by uuid DEFAULT uuid_generate_v4(),
    updated_by character varying(200) COLLATE pg_catalog."default",
    allow_partial_pay boolean,
    validity character varying COLLATE pg_catalog."default",
    currency character varying(200) COLLATE pg_catalog."default",
    is_active boolean,
    CONSTRAINT fees_template_pkey PRIMARY KEY (fees_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.fees_template
    OWNER to postgres;
# Student Fee Payment Details
-- Table: public.student_feepayment_details

-- DROP TABLE IF EXISTS public.student_feepayment_details;

CREATE TABLE IF NOT EXISTS public.student_feepayment_details
(
    id integer NOT NULL,
    academic_year character varying COLLATE pg_catalog."default",
    fees_id integer,
    fees_category character varying COLLATE pg_catalog."default",
    to_pay double precision,
    student_id uuid DEFAULT uuid_generate_v4(),
    pay_term character varying COLLATE pg_catalog."default",
    due_date character varying COLLATE pg_catalog."default",
    paid boolean,
    paid_date date,
    payment_mode character varying COLLATE pg_catalog."default",
    receipt_number character varying COLLATE pg_catalog."default",
    reference_no character varying COLLATE pg_catalog."default",
    notification_sent boolean,
    CONSTRAINT student_feepayment_details_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.student_feepayment_details
    OWNER to postgres;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";