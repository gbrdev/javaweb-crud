/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  z1
 * Created: 20 de set de 2020
 */

create table estado (
	idEstado serial primary key,
	nomeEstado varchar(50) not null,
	siglaEstado varchar(02) not null
)

insert into estado(nomeEstado, siglaEstado) values ('São Paulo', 'SP');