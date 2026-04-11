-- =============================================
-- HomeValue+ MySQL Schema
-- =============================================
-- Run this ONLY if you want to create the DB manually.
-- Spring Boot with ddl-auto=update will auto-create tables.
-- =============================================

CREATE DATABASE IF NOT EXISTS homevalueplus;
USE homevalueplus;

-- =============================================
-- Admin Table
-- =============================================
CREATE TABLE IF NOT EXISTS admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- Ideas Table
-- =============================================
CREATE TABLE IF NOT EXISTS ideas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category ENUM('INTERIOR', 'EXTERIOR', 'ENERGY', 'TECHNOLOGY', 'INVESTMENT', 'STRUCTURAL') NOT NULL,
    estimated_cost_min DOUBLE NOT NULL,
    estimated_cost_max DOUBLE NOT NULL,
    expected_value_increase DOUBLE NOT NULL,
    property_type ENUM('APARTMENT', 'INDEPENDENT_HOUSE') NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- Submissions Table
-- =============================================
CREATE TABLE IF NOT EXISTS submissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    property_type ENUM('APARTMENT', 'INDEPENDENT_HOUSE') NOT NULL,
    property_condition ENUM('POOR', 'AVERAGE', 'GOOD') NOT NULL,
    budget DOUBLE NOT NULL,
    submission_date DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- Indexes for Performance
-- =============================================
CREATE INDEX idx_ideas_category ON ideas(category);
CREATE INDEX idx_ideas_property_type ON ideas(property_type);
CREATE INDEX idx_submissions_date ON submissions(submission_date);
CREATE INDEX idx_submissions_property_type ON submissions(property_type);
