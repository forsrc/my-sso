
-- DROP TABLE IF EXISTS oauth2_registered_client;
CREATE TABLE IF NOT EXISTS oauth2_registered_client
(
    id                            VARCHAR(100)                        PRIMARY KEY,
    client_id                     VARCHAR(100)                        NOT NULL,
    client_id_issued_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 VARCHAR(200)                        NULL,
    client_secret_expires_at      TIMESTAMP                           NULL,
    client_name                   VARCHAR(200)                        NOT NULL,
    client_authentication_methods VARCHAR(1000)                       NOT NULL,
    authorization_grant_types     VARCHAR(1000)                       NOT NULL,
    redirect_uris                 VARCHAR(1000)                       NULL,
    scopes                        VARCHAR(1000)                       NOT NULL,
    client_settings               VARCHAR(2000)                       NOT NULL,
    token_settings                VARCHAR(2000)                       NOT NULL
);

-- DROP TABLE IF EXISTS oauth2_authorization;
CREATE TABLE IF NOT EXISTS oauth2_authorization
(
    id                            varchar(100)  PRIMARY KEY,
    registered_client_id          varchar(100)  NOT NULL,
    principal_name                varchar(200)  NOT NULL,
    authorization_grant_type      varchar(100)  NOT NULL,
    attributes                    varchar(4000) NULL,
    state                         varchar(500)  NULL,
    authorization_code_value      bytea         NULL,
    authorization_code_issued_at  timestamp     NULL,
    authorization_code_expires_at timestamp     NULL,
    authorization_code_metadata   varchar(2000) NULL,
    access_token_value            bytea         NULL,
    access_token_issued_at        timestamp     NULL,
    access_token_expires_at       timestamp     NULL,
    access_token_metadata         varchar(2000) NULL,
    access_token_type             varchar(100)  NULL,
    access_token_scopes           varchar(1000) NULL,
    oidc_id_token_value           bytea         NULL,
    oidc_id_token_issued_at       timestamp     NULL,
    oidc_id_token_expires_at      timestamp     NULL,
    oidc_id_token_metadata        varchar(2000) NULL,
    refresh_token_value           bytea         NULL,
    refresh_token_issued_at       timestamp     NULL,
    refresh_token_expires_at      timestamp     NULL,
    refresh_token_metadata        varchar(2000) NULL
);

-- DROP TABLE IF EXISTS oauth2_authorization_consent;
CREATE TABLE IF NOT EXISTS oauth2_authorization_consent
(
    registered_client_id varchar(100)  NOT NULL,
    principal_name       varchar(200)  NOT NULL,
    authorities          varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);

-- DROP TABLE IF EXISTS ClientDetails;
CREATE TABLE IF NOT EXISTS ClientDetails (
  appId                  VARCHAR(256) PRIMARY KEY,
  resourceIds            VARCHAR(256),
  appSecret              VARCHAR(256),
  scope                  VARCHAR(256),
  grantTypes             VARCHAR(256),
  redirectUrl            VARCHAR(256),
  authorities            VARCHAR(256),
  access_token_validity  INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation  VARCHAR(4096),
  autoApproveScopes      VARCHAR(256)
);


-- DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    username  VARCHAR(50)  NOT NULL PRIMARY KEY ,
    password  VARCHAR(200) NOT NULL,
    enabled   SMALLINT     NOT NULL DEFAULT 0,
    version   INTEGER      NOT NULL DEFAULT 0,
    create_on TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_on TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- DROP TABLE IF EXISTS authorities;
CREATE TABLE IF NOT EXISTS authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    version   INTEGER     NOT NULL DEFAULT 0,
    create_on TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_on TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(username,authority)
);
