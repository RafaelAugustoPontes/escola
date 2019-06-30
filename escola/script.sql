
INSERT INTO `escola`.`pessoa`
(`IDPESSOA`,
`NOME`,
`CPF`,
`MATRICULA`,
`DATANASCIMENTO`
`PERFIL`)
VALUES
(1,
'Rafael',
'08350193905',
1,
'1993-11-09',
'ADMINISTRADOR');

INSERT INTO `escola`.`unidade`
(`IDUNIDADE`,
`NOME`)
VALUES
(1,
'Cajuru');

INSERT INTO `escola`.`curso`
(`IDCURSO`,
`NOME`)
VALUES
(1,
'Inglês');



INSERT INTO `escola`.`usuario`
(`IDUSUARIO`,
`LOGIN`,
`SENHA`,
IDPESSOA)
VALUES
(1,
'adm',
'123',1);