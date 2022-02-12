INSERT INTO "oauth2_registered_client" 
(
	"id", 
	"client_id",
	"client_id_issued_at",
	"client_secret",
	"client_secret_expires_at",
	"client_name",
	"client_authentication_methods",
	"authorization_grant_types",
	"redirect_uris",
	"scopes",
	"client_settings",
	"token_settings"
)
SELECT
	'client-server',
	'oauth2-client',
	'2022-01-01 00:00:00.123456',
	'{noop}forsrc',
	NULL,
	'client-server',
	'client_secret_basic',
	'refresh_token,client_credentials,password,authorization_code',
	'http://client-server:8080/login/oauth2/code/oauth2-client-oidc,http://client-server:8080/authorized,http://client-server/login/oauth2/code/oauth2-client-oidc,http://client-server/authorized,https://client-server/login/oauth2/code/oauth2-client-oidc,https://client-server/authorized',
	'openid,api',
	'{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000]}'
WHERE
    NOT EXISTS (SELECT id FROM oauth2_registered_client WHERE id = 'client-server') 
;

INSERT INTO "oauth2_registered_client" 
(
	"id", 
	"client_id",
	"client_id_issued_at",
	"client_secret",
	"client_secret_expires_at",
	"client_name",
	"client_authentication_methods",
	"authorization_grant_types",
	"redirect_uris",
	"scopes",
	"client_settings",
	"token_settings"
)
SELECT
	'forsrc',
	'forsrc',
	'2022-01-01 00:00:00.123456',
	'$2a$10$Wzme7qZtAsJZspQpNx3ee.qTu/IqRHiTb0jORWUOXCxptAkG3kf8e',
	NULL,
	'client-server',
	'client_secret_basic',
	'refresh_token,client_credentials,password,authorization_code',
	'https://client-server:8080/authorized,http://client-server:8080/authorized,http://client-server:8080/login/oauth2/code/oauth2-client-oidc,https://client-server:8080/login/oauth2/code/oauth2-client-oidc',
	'openid,api',
	'{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000]}'
WHERE
    NOT EXISTS (SELECT id FROM oauth2_registered_client WHERE id = 'client-server') 
;


-- users
INSERT INTO users (username, password, enabled)
	SELECT *
	FROM (SELECT 'forsrc' username, '$2a$10$Wzme7qZtAsJZspQpNx3ee.qTu/IqRHiTb0jORWUOXCxptAkG3kf8e' password, 1 enabled) AS T
	WHERE NOT EXISTS(SELECT username FROM users WHERE username = 'forsrc');
INSERT INTO users (username, password, enabled)
SELECT *
FROM (SELECT 'user' username, '$2a$10$SNKOBpTBuCbWukZ3Rc5DpuIHRP585Ss02fULAIX/m1NmFpWeJ8ic2' password, 1 enabled) AS T
WHERE NOT EXISTS(SELECT username FROM users WHERE username = 'user');
INSERT INTO users (username, password, enabled)
SELECT *
FROM (SELECT 'tcc' username, '$2a$10$lFUTwK/W3S3U8NI3cnqJPeVD3cZj6udLbW2W5GMvybtJw70N4WqFC' password, 1 enabled) AS T
WHERE NOT EXISTS(SELECT username FROM users WHERE username = 'tcc');
INSERT INTO users (username, password, enabled)
SELECT *
FROM (SELECT 'test' username, '$2a$10$uCchlP6N1q7ZOEMMifeZyOEOgqpddiVEIiIrM4k/76ftgLxtBaSXq' password, 1 enabled) AS T
WHERE NOT EXISTS(SELECT username FROM users WHERE username = 'test');

-- authorities
INSERT INTO authorities (username, authority)
SELECT *
FROM (SELECT 'forsrc' username, 'ROLE_ADMIN' authorities) AS T
WHERE NOT EXISTS(SELECT username FROM authorities WHERE username = 'forsrc' and authority = 'ROLE_ADMIN');
INSERT INTO authorities (username, authority)
SELECT *
FROM (SELECT 'forsrc' username, 'ROLE_USER' authorities) AS T
WHERE NOT EXISTS(SELECT username FROM authorities WHERE username = 'forsrc' and authority = 'ROLE_USER');
INSERT INTO authorities (username, authority)
SELECT *
FROM (SELECT 'user' username, 'ROLE_USER' authorities) AS T
WHERE NOT EXISTS(SELECT username FROM authorities WHERE username = 'user');
INSERT INTO authorities (username, authority)
SELECT *
FROM (SELECT 'tcc' username, 'ROLE_TCC' authorities) AS T
WHERE NOT EXISTS(SELECT username FROM authorities WHERE username = 'tcc');
INSERT INTO authorities (username, authority)
SELECT *
FROM (SELECT 'test' username, 'ROLE_TEST' authorities) AS T
WHERE NOT EXISTS(SELECT username FROM authorities WHERE username = 'test');