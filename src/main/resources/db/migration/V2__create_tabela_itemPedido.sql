CREATE TABLE item_pedido (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    quantidade BIGINT(20) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    pedido_id BIGINT(20) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
) 
