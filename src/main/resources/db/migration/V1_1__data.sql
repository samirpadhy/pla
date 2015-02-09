INSERT INTO security_permission (ID, DESCRIPTION, PERMISSION_ID ) VALUES (3,'ROLE_ADMIN','ROLE_ADMIN' );
INSERT INTO security_group (ID, NAME ) VALUES (3,'ADMIN' );
INSERT INTO security_group_security_permissions (security_group_id, security_permissions_id )VALUES (3,3);
INSERT INTO user_login (ID, IS_ENABLED, NUMBER_OF_FAILED_LOGIN_ATTEMPTS,PASSWORD, USERNAME,IS_ACCOUNT_NON_LOCKED )
VALUES (1,true,0,'ef050f33cbc7381f0d1a5086c3ef880dd3b4eec2','admin',true);
INSERT INTO user_login_security_groups (user_login_id, security_groups_id ) VALUES (1,3);