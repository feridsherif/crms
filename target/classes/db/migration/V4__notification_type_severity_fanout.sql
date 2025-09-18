ALTER TABLE notifications 
ADD COLUMN type VARCHAR(32) DEFAULT 'INFO',
ADD COLUMN severity VARCHAR(32) DEFAULT 'LOW';
-- 'role' column is now deprecated, will be ignored by new logic
