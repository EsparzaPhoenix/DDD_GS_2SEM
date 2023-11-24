-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE


CREATE TABLE t_paciente (
    id_paciente     NUMBER(9) NOT NULL,
    nm_paciente     VARCHAR2(80) NOT NULL,
    ds_email        VARCHAR2(100) NOT NULL,
    nr_telefone     NUMBER(12),
    nr_convenio     NUMBER(12) NOT NULL,
    ds_genero       CHAR(1) NOT NULL,
    ds_faixa_etaria VARCHAR2(30) NOT NULL,
    dt_nascimento   DATE NOT NULL
);

ALTER TABLE t_paciente ADD CONSTRAINT pk_paciente PRIMARY KEY (id_paciente);
ALTER TABLE t_paciente ADD CONSTRAINT uk_telefone UNIQUE (nr_telefone);
ALTER TABLE t_paciente ADD CONSTRAINT uk_nr_convenio UNIQUE (nr_convenio);


CREATE TABLE t_consulta (
    id_consulta      NUMBER(9) NOT NULL,
    id_hospital      NUMBER(9) NOT NULL,
    id_medico        NUMBER(9) NOT NULL,
    id_paciente      NUMBER(9) NOT NULL,
    dt_hora_consulta DATE NOT NULL,
    nr_consultorio   NUMBER(5) NOT NULL,
    id_cid           NUMBER(9) NOT NULL,
    id_prescricao    NUMBER(9) NOT NULL
);

ALTER TABLE t_consulta ADD CONSTRAINT pk_consulta PRIMARY KEY (id_consulta, id_hospital,id_cid,id_prescricao);

CREATE TABLE t_cid (
    id_cid           NUMBER(9) NOT NULL,
    nm_descricao_cid VARCHAR2(1000) NOT NULL
);

ALTER TABLE t_cid ADD CONSTRAINT pk_cons_cid PRIMARY KEY (id_cid);

CREATE TABLE t_prescricao (
    id_prescricao   NUMBER(9) NOT NULL,
    id_medico       NUMBER(9) NOT NULL,
    tp_tratamento   VARCHAR2(4000) NOT NULL,
    tp_retorno      VARCHAR2(80),
    nm_dias_retorno NUMBER(38)
);

ALTER TABLE t_prescricao ADD CONSTRAINT pk_presc PRIMARY KEY (id_prescricao);

CREATE TABLE t_medico (
    id_medico        NUMBER(9) NOT NULL,
    id_hospital      NUMBER(9) NOT NULL,
    nm_medico        VARCHAR2(90) NOT NULL,
    nr_crm           NUMBER(10) NOT NULL,
    nm_especialidade VARCHAR2(60) NOT NULL
);

ALTER TABLE t_medico ADD CONSTRAINT pk_medico PRIMARY KEY (id_medico);

CREATE TABLE t_endereco_paciente (
    id_endereco    NUMBER(9) NOT NULL,
    id_paciente    NUMBER(9) NOT NULL,
    id_logradouro  NUMBER(9) NOT NULL,
    nr_logradouro  NUMBER(7),
    ds_complemento VARCHAR2(30)
);

ALTER TABLE t_endereco_paciente ADD CONSTRAINT pk_end_pac PRIMARY KEY (id_endereco);

CREATE TABLE t_hospital (
    id_hospital    NUMBER(9) NOT NULL,
    id_logradouro  NUMBER(9) NOT NULL,
    nm_hospital    VARCHAR2(80) NOT NULL,
    nr_logradouro  NUMBER(7),
    ds_complemento VARCHAR2(30)
);

ALTER TABLE t_hospital ADD CONSTRAINT pk_hosp_end PRIMARY KEY (id_hospital);

CREATE TABLE t_logradouro (
    id_logradouro NUMBER(9) NOT NULL,
    id_bairro     NUMBER(9) NOT NULL,
    nm_logradouro VARCHAR2(100) NOT NULL,
    nr_cep        NUMBER(8) NOT NULL
);

ALTER TABLE t_logradouro ADD CONSTRAINT pk_logradouro PRIMARY KEY (id_logradouro);


CREATE TABLE t_bairro (
    id_bairro      NUMBER(9) NOT NULL,
    id_cidade      NUMBER(9) NOT NULL,
    nm_bairro      VARCHAR2(45) NOT NULL,
    nm_zona_bairro VARCHAR2(20) NOT NULL
);

ALTER TABLE t_bairro ADD CONSTRAINT uk_t_pkd_bairro_zona CHECK (nm_zona_bairro IN ('CENTRO', 'ZONA LESTE', 'ZONA NORTE', 'ZONA OESTE', 'ZONA SUL'));
ALTER TABLE t_bairro ADD CONSTRAINT pk_bairro PRIMARY KEY (id_bairro);

CREATE TABLE t_cidade (
    id_cidade NUMBER(9) NOT NULL,
    id_estado NUMBER(9) NOT NULL,
    nm_cidade VARCHAR2(80) NOT NULL
);

COMMENT ON COLUMN t_cidade.id_cidade IS '';

ALTER TABLE t_cidade ADD CONSTRAINT pk_cidade PRIMARY KEY (id_cidade);

CREATE TABLE t_estado (
    id_estado NUMBER(9) NOT NULL,
    sg_estado CHAR(2) NOT NULL,
    nm_estado VARCHAR2(30) NOT NULL
);

ALTER TABLE t_estado ADD CONSTRAINT pk_estado PRIMARY KEY (id_estado);

ALTER TABLE t_logradouro ADD CONSTRAINT fk_bairro_lograd FOREIGN KEY (id_bairro) REFERENCES t_bairro (id_bairro);
ALTER TABLE t_bairro ADD CONSTRAINT fk_cidade_bairro FOREIGN KEY (id_cidade) REFERENCES t_cidade (id_cidade);
ALTER TABLE t_cidade ADD CONSTRAINT fk_estado_cidade FOREIGN KEY (id_estado) REFERENCES t_estado (id_estado);
ALTER TABLE t_consulta ADD CONSTRAINT fk_hosp_cons FOREIGN KEY (id_hospital) REFERENCES t_hospital (id_hospital);
ALTER TABLE t_endereco_paciente ADD CONSTRAINT fk_logr_end_pac FOREIGN KEY (id_logradouro) REFERENCES t_logradouro (id_logradouro);
ALTER TABLE t_hospital ADD CONSTRAINT fk_logr_hosp FOREIGN KEY (id_logradouro) REFERENCES t_logradouro (id_logradouro);
ALTER TABLE t_consulta ADD CONSTRAINT fk_med_cons FOREIGN KEY (id_medico) REFERENCES t_medico (id_medico);
ALTER TABLE t_consulta ADD CONSTRAINT fk_pac_cons FOREIGN KEY (id_paciente) REFERENCES t_paciente (id_paciente);
ALTER TABLE t_endereco_paciente ADD CONSTRAINT fk_pac_end_pac FOREIGN KEY (id_paciente) REFERENCES t_paciente (id_paciente);

        
        

-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            11
-- CREATE INDEX                             0
-- ALTER TABLE                             26
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
