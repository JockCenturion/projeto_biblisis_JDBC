create database dbbiblisis;

use dbbiblisis;

show tables;


create table tbeditora(
		codEdit int primary key auto_increment,
		nomeEdit varchar(60) not null			
);


create table tbobra(
		codObra int primary key auto_increment,
		tituloObra varchar(100) not null,   
        categoriaObra varchar(60) not null, 
        autorObra varchar(60),			
        dataPublicao date,
        codEdit int not null,
        foreign key (codEdit) references tbeditora(codEdit)
);


create table tblivro(
		isbn varchar(20) not null,
		codObra int not null,
        foreign key(codObra) references tbobra(codObra),
        primary key(isbn, codObra)
);


create table tbperiodico(
		issn varchar(20) not null,
		codObra int not null,
        foreign key(codObra) references tbobra(codObra),
        primary key(issn, codObra)
);


create table tbexemplar(
		codEx int auto_increment,
		codObra int not null,
        isEmprestado bool,							
        foreign key(codObra) references tbobra(codObra),
        primary key(codEx, codObra)
);


create table tbcargo(
		codCargo int primary key,
		nomeCargo varchar(80) not null				
);


create table tbfuncionario(
		loginFun varchar(15) not null unique primary key,
		senhaFun varchar(15) not null,
		nomeFun varchar(60) not null,	
        codCargo int not null references tbcargo(codCargo) /*Ele não adicionou o relacionamento a cargo*/
);



create table tbusuario(
		loginUser varchar(15) not null unique primary key,
        senhaUser varchar(15) not null,
		nomeUser varchar(60) not null,
        statusUser bool
);


create table tbreserva(
		loginUser varchar(15) not null,
		codObra int not null,
		codEx int not null,
		dataReserva timestamp default current_timestamp, /*dataTime*/
		dataRetirada dateTime, /*YYYY-MM-DD HH:MM:SS*/
		foreign key(loginUser) references tbusuario(loginUser),
		foreign key(codObra) references tbobra(codObra),
		foreign key(codEx) references tbexemplar(codEx),
		primary key(loginUser, codObra, codEx)
);


create table tbemprestimo(
		loginFun varchar(15) not null,
        loginUser varchar(15) not null,
        codEx int not null,
        codObra int not null,
        dataEmprestimo timestamp default current_timestamp,
        dataDevolucao dateTime not null,
        foreign key(loginFun) references tbfuncionario(LoginFun),
        foreign key(loginUser) references tbusuario(loginUser),
        foreign key(codEx) references tbexemplar(codEx),
        foreign key(codObra) references tbobra(codObra),
        primary key(loginFun, loginUser, codEx, codObra)
);


create table tbdevolucao (
		codDevolucao int auto_increment primary key,
		loginFun varchar(15) not null,
        codEx int not null,
        codObra int not null,
        dataDevolucao dateTime not null,
        foreign key(loginFun) references tbfuncionario(loginFun),
        foreign key(codEx) references tbexemplar(codEx),
        foreign key(codObra) references tbobra(codObra)
);


create table tbrenovacao (
		codRenovacao int auto_increment primary key,
		loginFun varchar(15) not null,
        codEx int not null,
        codObra int not null,
        dataRenovacao dateTime not null,
        foreign key(loginFun) references tbfuncionario(loginFun),
        foreign key(codEx) references tbexemplar(codEx),
        foreign key(codObra) references tbobra(codObra)
);



/*Inserindo Cargos, por hora somente 2*/
insert into tbcargo values(1, 'ATENDENTE'), (2, 'ADM');

/*Inserindo Funcionarios*/
insert into tbfuncionario values ('admin', 'admin', 'jock', 1);
insert into tbfuncionario values ('carllosveiga', '12456', 'carlos', 2), ('pedriHG', '9999', 'Pedro', 2);
insert into tbfuncionario values ('root', 'admin', 'administrador', 1);


/*Inserindo Usuarios*/
insert into tbusuario values ('jockfss', '343536', 'Jock', false);
insert into tbusuario values ('luquete', '8745', 'lucas', false);
insert into tbusuario values ('joao978', '4861556', 'João Carlos', false);
insert into tbusuario values ('pedrinho@10', 'ped789', 'Pedro', false);  
insert into tbusuario values ('ivavia#14', 'iva123', 'Ivan', false);
insert into tbusuario values ('ters@15', '89712', 'Terezina', false);


/*Inserindo as Principais Editoras*/
insert into tbeditora values (null, 'Veja'), (null, 'Escute'), (null, 'Ande');
insert into tbeditora values (null, 'PC&CIA');


/*Inserindo Obra*/
insert into tbobra values (null, 'As Aventuras de Pedro', 'Aventura', 'Jorge', '2018-03-02', 1);	
insert into tbobra values (null, 'Algebra Linear - XXXX', 'Matemáica', 'Carlos', '2018-03-02', 2);
insert into tbobra values (null, 'Zip - ED de compactacao', 'Informatica', 'José Fino', '2018-02-01', 3);
insert into tbobra values (null, 'Raspeberry Pi', 'Informatica', 'PC&Cia', '2018-02-01', 4);


/*Inserindo Livros*/
insert into tblivro values ('ISBN388053101-3', 1);
insert into tblivro values ('ISBN012345689-0', 2);
insert into tblivro values ('ISBN095530100-9', 4);

/*Inserindo um Periodo*/
insert into tbperiodico values ('ISSN1511-3707', 5);


/*Inserindo Exemplares*/

-- exemplares das As Aventuras de Pedro
insert into tbexemplar values (1, 1, 0);
insert into tbexemplar values (2, 1, 0);
insert into tbexemplar values (3, 1, 0);
insert into tbexemplar values (4, 1, 0);
insert into tbexemplar values (5, 1, 0);

-- exemplares de Algebra Linear
insert into tbexemplar values (1, 2, 0);
insert into tbexemplar values (2, 2, 0);
insert into tbexemplar values (3, 2, 0);
insert into tbexemplar values (4, 2, 0);

-- exemplares de Zip - ED de compactacao
insert into tbexemplar values (1, 4, 0);
insert into tbexemplar values (2, 4, 0);
insert into tbexemplar values (3, 4, 0);
insert into tbexemplar values (4, 4, 0);

-- exemplares de Raspeberry - PI*/
insert into tbexemplar values (1, 5, 0);
insert into tbexemplar values (2, 5, 0);
insert into tbexemplar values (3, 5, 0);
insert into tbexemplar values (4, 5, 0);



/*************** Funções do Programa ***************************/


/******************* Reserva ***********************************/
/*Usuario solicita Reserva  CREATE */
insert into tbreserva values ('jockfss', 1, 1, null, null);
insert into tbreserva values ('joao978', 1, 2, null, null);
insert into tbreserva values ('ivavia#14', 1, 3, null, null);
insert into tbreserva values ('ivavia#14', 1, 4, null, null);

/*Usuario Cancela uma Reseva - Delete*/
 -- delete from tbreserva where loginUser = 'jockfss' and codObra = 1 and codEx = 1;

/*Consulta Reserva - Search*/
select * from tbreserva where loginUser = 'jockfss' and codObra = 1 and codEx = 1;

/*Atualiza uma Reserva - UPDATE (Quem Faz Isso e o Funcionario)*/
update tbreserva set dataRetirada = '2018-02-2 14:39:15' where loginUser = 'jockfss' and codObra = 1 and codEx = 1; 

/*Visualisa Hitorico de Reserva*/
select * from tbreserva where loginUser = 'ivavia#14';

/*Reserva em Aberto?*/
select * from tbreserva where dataRetirada is null;




/*******Realizar um emprestimo************/
update tbexemplar set isEmprestado = true where codObra = 1 and codEx = 1;
update tbreserva set dataRetirada = '2018-02-2 14:39:15' where loginUser = 'jockfss' and codObra = 1 and codEx = 1; 
insert into tbemprestimo values ('carllosveiga', 'jockfss', 1, 1, null, '2018-02-27 14:39:15'); /*Gera uma data de 7 dias pra devolucao*/

update tbexemplar set isEmprestado = true where codObra = 1 and codEx = 4;
update tbreserva set dataRetirada = '2018-02-27 14:39:15' where loginUser = 'ivavia#14' and codObra = 1 and codEx = 4;
insert into tbemprestimo values ('carllosveiga', 'ivavia#14', 1, 4, null, '2018-02-27 14:39:15'); /*Gera uma data de 7 dias pra devolucao*/

/*Visualisa Hitorico de Emprestimo*/
select * from tbemprestimo where loginUser = 'ivavia#14';


/*Consulta Acervo de Obras*/
select * from tbobra;

/*Consultar Situacao do Aluno/Professor*/
select * from tbusuario where loginUser = 'jockfss';



/***************/
select * from tbemprestimo;
select * from tbusuario;
select * from tbreserva;
select * from tbexemplar order by codObra, codEx;


select * from tbexemplar order by codObra, codEx;
select * from tbusuario;
/***************/

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
