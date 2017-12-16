-- Database: cajero

-- DROP DATABASE cajero;

CREATE DATABASE cajero
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'C'
       LC_CTYPE = 'C'
       CONNECTION LIMIT = -1;




-- DROP TABLE cajeros;

CREATE TABLE cajeros
(
  id_cajero serial NOT NULL,
  nombre character varying(255),
  direccion text,
  CONSTRAINT cajeros_pkey PRIMARY KEY (id_cajero)
);
 
-- Table: clientes

-- DROP TABLE clientes;

CREATE TABLE clientes
(
  id_cliente serial NOT NULL,
  nro_doc character varying(128),
  tipo_doc character varying(64),
  nombre character varying(255),
  fecha_nacimiento date,
  password character varying(128),
  direccion text,
  telefono character varying(64),
  CONSTRAINT clientes_pkey PRIMARY KEY (id_cliente),
  CONSTRAINT clientes_nro_doc_tipo_doc_key UNIQUE (nro_doc, tipo_doc)
);

-- Table: accesos

-- DROP TABLE accesos;

CREATE TABLE accesos
(
  id_acceso serial NOT NULL,
  fecha_hora_inicio timestamp without time zone,
  fecha_hora_fin timestamp without time zone,
  id_cajero bigint,
  id_cliente bigint,
  CONSTRAINT accesos_pkey PRIMARY KEY (id_acceso),
  CONSTRAINT accesos_id_cajero_fkey FOREIGN KEY (id_cajero)
      REFERENCES cajeros (id_cajero) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT accesos_id_cliente_fkey FOREIGN KEY (id_cliente)
      REFERENCES clientes (id_cliente) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
); 

-- Table: monedas

-- DROP TABLE monedas;

CREATE TABLE monedas
(
  id_moneda serial NOT NULL,
  nombre character varying(32),
  CONSTRAINT monedas_pkey PRIMARY KEY (id_moneda)
);
  
-- Table: cuentas

-- DROP TABLE cuentas;

CREATE TABLE cuentas
(
  nro_cuenta character varying(32) NOT NULL,
  tipo character(2),
  denominacion character varying(255),
  saldo_disponible numeric(18,2),
  saldo_a_confirmar numeric(18,2),
  id_moneda bigint,
  CONSTRAINT cuentas_pkey PRIMARY KEY (nro_cuenta),
  CONSTRAINT cuentas_id_moneda_fkey FOREIGN KEY (id_moneda)
      REFERENCES monedas (id_moneda) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
  
 Table: operaciones

-- DROP TABLE operaciones;

CREATE TABLE operaciones
(
  nro_operacion serial NOT NULL,
  fecha_hora timestamp without time zone,
  id_cajero bigint,
  id_acceso bigint,
  estado character varying(32),
  mensaje text,
  tipo character varying(64),
  CONSTRAINT operaciones_pkey PRIMARY KEY (nro_operacion),
  CONSTRAINT operaciones_id_acceso_fkey FOREIGN KEY (id_acceso)
      REFERENCES accesos (id_acceso) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT operaciones_id_cajero_fkey FOREIGN KEY (id_cajero)
      REFERENCES cajeros (id_cajero) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
); 

-- Table: transacciones

-- DROP TABLE transacciones;

CREATE TABLE transacciones
(
  nro_operacion bigint NOT NULL,
  importe numeric(18,2),
  id_moneda integer,
  CONSTRAINT transacciones_pkey PRIMARY KEY (nro_operacion),
  CONSTRAINT transacciones_id_moneda_fkey FOREIGN KEY (id_moneda)
      REFERENCES monedas (id_moneda) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT transacciones_nro_operacion_fkey FOREIGN KEY (nro_operacion)
      REFERENCES operaciones (nro_operacion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: transferencias

-- DROP TABLE transferencias;

CREATE TABLE transferencias
(
  nro_operacion bigint NOT NULL,
  nro_cuenta_origen character varying(32),
  nro_cuenta_destino character varying(32),
  CONSTRAINT transferencias_pkey PRIMARY KEY (nro_operacion),
  CONSTRAINT transferencias_nro_cuenta_destino_fkey FOREIGN KEY (nro_cuenta_destino)
      REFERENCES cuentas (nro_cuenta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT transferencias_nro_cuenta_origen_fkey FOREIGN KEY (nro_cuenta_origen)
      REFERENCES cuentas (nro_cuenta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT transferencias_nro_operacion_fkey FOREIGN KEY (nro_operacion)
      REFERENCES transacciones (nro_operacion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
););