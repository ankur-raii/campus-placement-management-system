# Campus Placement Management System

A comprehensive Java Swing-based desktop application for managing campus recruitment and placement activities. The system supports three user roles: Admin, HR (Company Recruiters), and Students (Participants).

## Features

### Admin Features
- Manage HR accounts (Add/Remove company HR representatives)
- View all registered users
- System administration

### HR Features  
- Create and manage job postings
- Design quiz questions for job assessments
- View applicant results and profiles
- Filter and shortlist candidates

### Student Features
- Register and create profile with resume upload
- Browse available job openings
- Apply for jobs
- Take online quiz assessments
- View application status and results

## System Requirements

### Prerequisites
1. **Java Development Kit (JDK) 8 or higher**
   - Download from: https://www.oracle.com/java/technologies/javase-downloads.html
   - Verify installation: `java -version`

2. **Oracle Database 11g XE or higher**
   - Download Oracle Database XE: https://www.oracle.com/database/technologies/xe-downloads.html
   - Alternative: You can use any Oracle Database version

3. **Apache Ant** (for building from command line)
   - Download from: https://ant.apache.org/bindownload.cgi
   - Or use NetBeans IDE which includes Ant

4. **Oracle JDBC Driver (ojdbc6.jar)**
   - Download from: https://www.oracle.com/database/technologies/jdbcdriver-ucp-downloads.html
   - Place in the `lib` folder of the project

## Installation & Setup

### Step 1: Install Oracle Database

1. Install Oracle Database 11g XE or higher
2. Start the database service:
   ```
   # Windows
   lsnrctl start
   
   # Or use Windows Services to start "OracleServiceXE"
   ```

### Step 2: Setup Database

1. Connect to Oracle as SYSDBA:
   ```sql
   sqlplus sys as sysdba
   ```

2. Create the application user:
   ```sql
   CREATE USER trandpl IDENTIFIED BY trandpl;
   GRANT CONNECT, RESOURCE, CREATE VIEW TO trandpl;
   GRANT UNLIMITED TABLESPACE TO trandpl;
   ```

3. Connect as the new user and run the schema script:
   ```sql
   CONNECT trandpl/trandpl
   @schema.sql
   ```
   
   Or use SQL Developer / SQL*Plus to execute the `schema.sql` file.

### Step 3: Configure Database Connection

1. Open `db.properties` file in the project root directory
2. Update the database connection settings:
   ```properties
   db.url=jdbc:oracle:thin:@//localhost:1521/xe
   db.user=trandpl
   db.password=trandpl
   ```
3. Modify these values if your Oracle setup is different

### Step 4: Add JDBC Driver

1. Download Oracle JDBC driver (ojdbc6.jar) for Java 8
2. Copy the JAR file to the `lib` folder in the project:
   ```
   trandpl/
   └── lib/
       └── ojdbc6.jar
   ```

### Step 5: Build and Run

#### Option A: Using NetBeans IDE (Recommended)
1. Open NetBeans IDE
2. Go to File → Open Project
3. Navigate to the `trandpl` folder and open it
4. Right-click on the project → Clean and Build
5. Right-click on the project → Run

#### Option B: Using Command Line
1. Open Command Prompt/PowerShell in the `trandpl` directory
2. Build the project:
   ```
   ant clean
   ant jar
   ```
3. Run the application:
   ```
   java -jar dist/trandpl.jar
   ```

#### Option C: Using Provided Scripts (Windows)
1. Double-click `build.bat` to compile the project
2. Double-click `run.bat` to run the application

## Default Login Credentials

After running `schema.sql`, use these credentials to login:

### Admin Account
- **Email:** admin@trandpl.com
- **Password:** admin123
- **User Type:** Select "Admin"

### HR Accounts
- **Email:** hr@infosys.com
- **Password:** hr123
- **User Type:** Select "Company HR"

- **Email:** hr@tcs.com
- **Password:** hr123
- **User Type:** Select "Company HR"

### Student Accounts
- **Email:** student1@college.edu
- **Password:** student123
- **User Type:** Select "Student"

- **Email:** student2@college.edu
- **Password:** student123
- **User Type:** Select "Student"

## Project Structure

```
trandpl/
├── src/
│   └── trandpl/
│       ├── dao/              # Data Access Objects
│       ├── dbutil/           # Database connection utility
│       ├── gui/              # Swing GUI frames
│       ├── pojo/             # Plain Old Java Objects (models)
│       └── images/           # Application images
├── lib/                      # External libraries (JDBC driver)
├── build/                    # Compiled classes (generated)
├── dist/                     # Distributable JAR (generated)
├── nbproject/                # NetBeans project files
├── db.properties             # Database configuration
├── schema.sql                # Database schema script
├── build.xml                 # Ant build script
└── README.md                 # This file
```

## Usage Guide

### For HR Users

1. **Login** as HR user
2. **Add New Job:**
   - Click "Add New Job"
   - Enter job title and tags (skills required)
   - Save the job

3. **Set Quiz Questions:**
   - Select the job
   - Click "Set Quiz"
   - Add 10 questions with 4 options each
   - Mark correct answers
   - Save (job status automatically changes to "Open")

4. **View Applications:**
   - Students can now see and apply for your job
   - View applicant results after they complete the quiz
   - Shortlist candidates

### For Students

1. **Register:**
   - Click "Sign Up" on login screen
   - Fill in details and upload resume
   - Complete registration

2. **Login** with your credentials

3. **Browse Jobs:**
   - View all open job positions
   - Check job requirements and tags

4. **Apply for Job:**
   - Select a job and click "Apply"
   - Take the quiz assessment
   - View your results

5. **View Status:**
   - Check application status
   - See quiz scores
   - Update profile and resume

## Troubleshooting

### Database Connection Issues

**Problem:** "Connection failed" error on startup
- **Solution:** 
  - Ensure Oracle Database is running
  - Check `db.properties` settings
  - Verify username and password
  - Test connection: `sqlplus trandpl/trandpl`

### JDBC Driver Not Found

**Problem:** "Oracle JDBC Driver not found" error
- **Solution:**
  - Download ojdbc6.jar from Oracle website
  - Place it in the `lib` folder
  - Rebuild the project

### Build Errors

**Problem:** Compilation errors
- **Solution:**
  - Ensure JDK 8 or higher is installed
  - Check JAVA_HOME environment variable
  - Clean and rebuild: `ant clean jar`

### Port Already in Use

**Problem:** Oracle listener port 1521 in use
- **Solution:**
  - Check if Oracle is already running
  - Change port in db.properties if using different port
  - Stop conflicting services

## Development Notes

- **Java Version:** Java 8 (1.8)
- **GUI Framework:** Java Swing with NetBeans AbsoluteLayout
- **Database:** Oracle Database 11g or higher
- **Build Tool:** Apache Ant
- **IDE:** NetBeans (recommended) or any Java IDE

## Database Schema Overview

### Tables
- **users:** All system users (Admin, HR, Students)
- **hr:** HR-specific information (company, phone)
- **participants:** Student profiles (skills, qualification, resume)
- **jobs:** Job postings by HR
- **questions:** Quiz questions for each job
- **results:** Application and quiz results

### Key Relationships
- Users → HR/Participants (1:1)
- HR → Jobs (1:many)
- Jobs → Questions (1:many)
- Jobs + Participants → Results (many:many)

## Security Notes

⚠️ **Important for Production Use:**
- Change all default passwords
- Implement password encryption (currently plain text)
- Add input validation and SQL injection prevention
- Enable HTTPS if deploying web version
- Regular database backups

## Support & Contact

For issues and questions:
- Check the Troubleshooting section
- Review database logs
- Verify all setup steps completed

## License

This is an educational project for campus management purposes.

---

**Version:** 1.0  
**Last Updated:** November 2025
