ALTER TABLE tasks ADD COLUMN instructions varchar(500)NOT NULL;
ALTER TABLE tasks RENAME task_type TO task_state;
ALTER TABLE tasks RENAME hasrating TO has_rating;
