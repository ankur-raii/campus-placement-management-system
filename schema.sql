-- ============================================================================
-- Campus Placement Management System - Database Schema
-- Oracle Database Script
-- ============================================================================

-- Drop existing tables if they exist (in reverse order of dependencies)
DROP TABLE results CASCADE CONSTRAINTS;
DROP TABLE questions CASCADE CONSTRAINTS;
DROP TABLE participants CASCADE CONSTRAINTS;
DROP TABLE jobs CASCADE CONSTRAINTS;
DROP TABLE hr CASCADE CONSTRAINTS;
DROP TABLE users CASCADE CONSTRAINTS;

-- ============================================================================
-- USERS TABLE
-- Stores all users (Admin, HR, Students/Participants)
-- ============================================================================
CREATE TABLE users (
    userid VARCHAR2(50) PRIMARY KEY,
    id VARCHAR2(20) NOT NULL UNIQUE,
    name VARCHAR2(100) NOT NULL,
    password VARCHAR2(50) NOT NULL,
    type VARCHAR2(20) NOT NULL CHECK (type IN ('Admin', 'Hr', 'Participant')),
    active CHAR(1) DEFAULT 'Y' CHECK (active IN ('Y', 'N'))
);

-- ============================================================================
-- HR TABLE
-- Stores HR-specific information
-- ============================================================================
CREATE TABLE hr (
    hrid VARCHAR2(20) PRIMARY KEY,
    phone VARCHAR2(15) NOT NULL,
    companyname VARCHAR2(100) NOT NULL,
    profession VARCHAR2(100),
    CONSTRAINT fk_hr_users FOREIGN KEY (hrid) REFERENCES users(id)
);

-- ============================================================================
-- PARTICIPANTS TABLE  
-- Stores student/participant information
-- ============================================================================
CREATE TABLE participants (
    pid VARCHAR2(20) PRIMARY KEY,
    phone VARCHAR2(15) NOT NULL,
    skills VARCHAR2(500),
    qualification VARCHAR2(200) NOT NULL,
    resume BLOB,
    CONSTRAINT fk_participant_users FOREIGN KEY (pid) REFERENCES users(id)
);

-- ============================================================================
-- JOBS TABLE
-- Stores job postings by HR
-- ============================================================================
CREATE TABLE jobs (
    jobid VARCHAR2(20) PRIMARY KEY,
    jobtitle VARCHAR2(200) NOT NULL,
    hrid VARCHAR2(20) NOT NULL,
    tags VARCHAR2(500),
    status NUMBER(1) DEFAULT 0 CHECK (status IN (-1, 0, 1)),
    -- status: -1 = deleted, 0 = created (quiz not set), 1 = open (quiz set)
    CONSTRAINT fk_jobs_hr FOREIGN KEY (hrid) REFERENCES hr(hrid)
);

-- ============================================================================
-- QUESTIONS TABLE
-- Stores quiz questions for each job
-- ============================================================================
CREATE TABLE questions (
    jobid VARCHAR2(20) NOT NULL,
    qno NUMBER(3) NOT NULL,
    question VARCHAR2(1000) NOT NULL,
    option1 VARCHAR2(500) NOT NULL,
    option2 VARCHAR2(500) NOT NULL,
    option3 VARCHAR2(500) NOT NULL,
    option4 VARCHAR2(500) NOT NULL,
    correctoption NUMBER(1) NOT NULL CHECK (correctoption BETWEEN 1 AND 4),
    PRIMARY KEY (jobid, qno),
    CONSTRAINT fk_questions_jobs FOREIGN KEY (jobid) REFERENCES jobs(jobid)
);

-- ============================================================================
-- RESULTS TABLE
-- Stores participant application and quiz results
-- ============================================================================
CREATE TABLE results (
    participantid VARCHAR2(20) NOT NULL,
    jobid VARCHAR2(20) NOT NULL,
    percentage NUMBER(5,2) DEFAULT -1,
    -- percentage: -1 = applied but not attempted, >=0 = quiz completed
    selectedbyhr CHAR(1) DEFAULT 'N' CHECK (selectedbyhr IN ('Y', 'N')),
    PRIMARY KEY (participantid, jobid),
    CONSTRAINT fk_results_participant FOREIGN KEY (participantid) REFERENCES participants(pid),
    CONSTRAINT fk_results_job FOREIGN KEY (jobid) REFERENCES jobs(jobid)
);

-- ============================================================================
-- SAMPLE DATA
-- ============================================================================

-- Insert Admin User
INSERT INTO users VALUES ('admin@trandpl.com', 'ADM001', 'System Administrator', 'admin123', 'Admin', 'Y');

-- Insert Sample HR
INSERT INTO users VALUES ('hr@infosys.com', 'HR-101', 'Rahul Sharma', 'hr123', 'Hr', 'Y');
INSERT INTO hr VALUES ('HR-101', '9876543210', 'Infosys', 'Technical Recruiter');

INSERT INTO users VALUES ('hr@tcs.com', 'HR-102', 'Priya Patel', 'hr123', 'Hr', 'Y');
INSERT INTO hr VALUES ('HR-102', '9876543211', 'TCS', 'HR Manager');

INSERT INTO users VALUES ('hr@wipro.com', 'HR-103', 'Amit Kumar', 'hr123', 'Hr', 'Y');
INSERT INTO hr VALUES ('HR-103', '9876543212', 'Wipro', 'Campus Recruiter');

-- Insert Sample Students/Participants
INSERT INTO users VALUES ('student1@college.edu', 'STU-101', 'Rajesh Verma', 'student123', 'Participant', 'Y');
INSERT INTO participants VALUES ('STU-101', '8765432100', 'Java, Python, SQL, Spring Boot', 'B.Tech in Computer Science', NULL);

INSERT INTO users VALUES ('student2@college.edu', 'STU-102', 'Anita Desai', 'student123', 'Participant', 'Y');
INSERT INTO participants VALUES ('STU-102', '8765432101', 'C++, Data Structures, Algorithms', 'B.Tech in IT', NULL);

INSERT INTO users VALUES ('student3@college.edu', 'STU-103', 'Vikram Singh', 'student123', 'Participant', 'Y');
INSERT INTO participants VALUES ('STU-103', '8765432102', 'JavaScript, React, Node.js, MongoDB', 'B.Tech in CSE', NULL);

-- Insert Sample Jobs
INSERT INTO jobs VALUES ('JOB-101', 'Java Developer', 'HR-101', 'Java, Spring, Hibernate, Microservices', 0);
INSERT INTO jobs VALUES ('JOB-102', 'Software Engineer', 'HR-102', 'Python, Django, REST API, PostgreSQL', 0);
INSERT INTO jobs VALUES ('JOB-103', 'Full Stack Developer', 'HR-103', 'MEAN Stack, Angular, Express, Node.js', 0);

-- Commit all changes
COMMIT;

-- ============================================================================
-- Display table counts
-- ============================================================================
SELECT 'users' as table_name, COUNT(*) as record_count FROM users
UNION ALL
SELECT 'hr', COUNT(*) FROM hr
UNION ALL
SELECT 'participants', COUNT(*) FROM participants
UNION ALL
SELECT 'jobs', COUNT(*) FROM jobs
UNION ALL
SELECT 'questions', COUNT(*) FROM questions
UNION ALL
SELECT 'results', COUNT(*) FROM results;

-- ============================================================================
-- NOTES FOR USAGE:
-- ============================================================================
-- 1. Login Credentials:
--    Admin: admin@trandpl.com / admin123
--    HR: hr@infosys.com / hr123 (or other HR accounts)
--    Student: student1@college.edu / student123 (or other student accounts)
--
-- 2. To add a job with quiz:
--    - HR logs in and creates a job (status = 0)
--    - HR adds quiz questions for that job
--    - System automatically sets job status = 1 (open for applications)
--
-- 3. Participant workflow:
--    - Student logs in and browses open jobs (status = 1)
--    - Applies for a job (creates entry in results with percentage = -1)
--    - Takes the quiz
--    - System updates percentage in results table
--
-- ============================================================================
