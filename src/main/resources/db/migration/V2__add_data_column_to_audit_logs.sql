-- Migration: Add 'data' column to audit_logs table for entity state logging
ALTER TABLE audit_logs ADD COLUMN data TEXT;