drop table transferencias;
drop table transacciones;
drop table operaciones;
drop table cuentas_clientes;
drop table cuentas;
drop table clientes;
drop table accesos;

create table accesos(
	id_acceso serial primary key,
	fecha_hora_inicio timestamp,
	fecha_hora_fin timestamp,
	ip varchar(128)
);

create table clientes(
	id_cliente serial primary key,
	nro_doc varchar(128),
	tipo_doc varchar(64),
	nombre varchar(255),
	fecha_nacimiento date,
	password varchar(128),
	direccion text,
	nro_telefono varchar(64),
	unique(nro_doc, tipo_doc)
);

create table cuentas(
	nro_cuenta varchar(32) primary key,
	tipo char(2),
	denominacion varchar(255),
	saldo_disponible numeric(18, 2),
	saldo_a_confirmar numeric(18, 2)
);

create table cuentas_clientes(
	id_cliente bigint,
	nro_cuenta varchar(32),
	primary key(id_cliente, nro_cuenta)
)

create table monedas(
	id_moneda serial primary key,
	nombre varchar(32)
)

create table cajeros(
	id_cajero serial primary key,
	nombre varchar(255),
	direccion text
);

create table operaciones(
	nro_operacion serial primary key,
	fecha_hora timestamp,
	id_cajero bigint references cajeros(id_cajero),
	id_acceso bigint references accesos(id_acceso),
	estado varchar(32),
	mensaje text
);


create table transacciones(
	nro_operacion bigint primary key references operaciones(nro_operacion),
	importe numeric(18, 2),
	id_moneda int references monedas(id_moneda)
);

create table transferencias(
	nro_operacion bigint primary key references transacciones(nro_operacion),
	nro_cuenta_origen varchar(32) references cuentas(nro_cuenta),
	nro_cuenta_destino varchar(32) references cuentas(nro_cuenta)
);