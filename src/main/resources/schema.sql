DROP TABLE IF EXISTS Operations;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Clients;

CREATE TABLE Clients (
                         client_id               INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY,
                         passport                VARCHAR(11) UNIQUE NOT NULL,
                         first_name              VARCHAR(30) NOT NULL,
                         last_name               VARCHAR(30) NOT NULL,
                         date_of_birth           DATE NOT NULL
);

CREATE TABLE Accounts (
                          account_id              INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY,
                          balance                 INT CHECK ( balance >= 0 ),
                          number                  INT UNIQUE NOT NULL,
                          state                   VARCHAR(15) NOT NULL,
                          opened_at               TIMESTAMP NOT NULL,
                          closed_at               TIMESTAMP,
                          client_id               INT REFERENCES Clients(client_id) ON DELETE RESTRICT NOT NULL,
                          account_type            VARCHAR(15) NOT NULL,
                          term                    INT NOT NULL,
                          prolongation_date       DATE NOT NULL
);

CREATE TABLE Operations (
                            operation_id          INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY,
                            amount                INT NOT NULL,
                            balance_before        INT NOT NULL,
                            balance_after         INT NOT NULL,
                            performed_at          TIMESTAMP NOT NULL,
                            account_id            INT REFERENCES Accounts(account_id) ON DELETE RESTRICT NOT NULL,
                            account_from_id       INT REFERENCES Accounts(account_id),
                            account_recipient_id  INT REFERENCES Accounts(account_id),
                            client_id             INT REFERENCES Clients(client_id) ON DELETE RESTRICT NOT NULL,
                            service               VARCHAR(15) NOT NULL,
                            operation_type        VARCHAR(15) NOT NULL,
                            number_in_history     INT CHECK ( number_in_history > 0 ) NOT NULL
);