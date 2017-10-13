ALTER TABLE candidates ADD COLUMN cv_file bytea;
ALTER TABLE candidates ADD COLUMN cv_mime_type varchar(100);
ALTER TABLE candidates ADD COLUMN cv_extension varchar(10);