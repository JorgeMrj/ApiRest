CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT PRIMARY KEY,
                                     name VARCHAR (50) NOT NULL,
                                     username VARCHAR (50) NOT NULL,
                                     email VARCHAR (100) NOT NULL,
                                     created_at TIMESTAMP NOT NULL,
                                     updated_at TIMESTAMP NOT NULL
);