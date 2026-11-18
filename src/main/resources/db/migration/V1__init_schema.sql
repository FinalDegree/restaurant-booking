CREATE TABLE restaurant (
                            id          BIGINT       NOT NULL AUTO_INCREMENT,
                            name        VARCHAR(120) NOT NULL,
                            address     VARCHAR(255) NOT NULL,
                            open_time   TIME         NOT NULL,
                            close_time  TIME         NOT NULL,
                            created_at  DATETIME(6)  NOT NULL,
                            PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE dining_table (
                              id            BIGINT NOT NULL AUTO_INCREMENT,
                              restaurant_id BIGINT NOT NULL,
                              table_number  INT    NOT NULL,
                              capacity      INT    NOT NULL,
                              PRIMARY KEY (id),
                              CONSTRAINT uk_dining_table_restaurant_number UNIQUE (restaurant_id, table_number),
                              CONSTRAINT fk_dining_table_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
) ENGINE = InnoDB;