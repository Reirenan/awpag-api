CREATE TABLE parcelamento (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              cliente_id BIGINT NOT NULL,
                              descricao VARCHAR(20) NOT NULL,
                              valor_total DECIMAL(10, 2) NOT NULL,
                              quantidade_parcelas TINYINT,
                              data_criacao DATETIME NOT NULL,
                              PRIMARY KEY (id)
);

ALTER TABLE parcelamento
    ADD CONSTRAINT fk_parcelamento_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id);
