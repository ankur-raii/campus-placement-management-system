# Quick Start Guide

Follow these steps to get the Campus Placement Management System running quickly.

## Prerequisites Checklist

- [ ] Java JDK 8+ installed
- [ ] Oracle Database installed and running
- [ ] Oracle JDBC driver (ojdbc6.jar)

## Fast Setup (5 Steps)

### 1. Download Oracle JDBC Driver

**Download ojdbc6.jar:**
- Visit: https://www.oracle.com/database/technologies/jdbcdriver-ucp-downloads.html
- Or direct link: https://download.oracle.com/otn/utilities_drivers/jdbc/11204/ojdbc6.jar
- Save it to: `D:\Java Major Project\trandpl\lib\ojdbc6.jar`

**Alternative:** If you already have Oracle Database installed, find it at:
```
C:\app\oracle\product\11.2.0\dbhome_1\jdbc\lib\ojdbc6.jar
```

### 2. Setup Database (Run in SQL*Plus or SQL Developer)

```sql
-- Connect as system user
sqlplus sys as sysdba

-- Create user
CREATE USER trandpl IDENTIFIED BY trandpl;
GRANT CONNECT, RESOURCE, CREATE VIEW TO trandpl;
GRANT UNLIMITED TABLESPACE TO trandpl;

-- Connect as trandpl user
CONNECT trandpl/trandpl

-- Run the schema script
@D:\Java Major Project\trandpl\schema.sql
```

### 3. Configure Database Connection

Edit `db.properties`:
```properties
db.url=jdbc:oracle:thin:@//localhost:1521/xe
db.user=trandpl
db.password=trandpl
```

### 4. Build the Project

Double-click: `build.bat`

Or run in command prompt:
```
cd "D:\Java Major Project\trandpl"
ant clean jar
```

### 5. Run the Application

Double-click: `run.bat`

Or run in command prompt:
```
cd "D:\Java Major Project\trandpl"
java -jar dist\trandpl.jar
```

## First Login

Use these credentials to test:

**Admin:**
- Email: admin@trandpl.com
- Password: admin123
- User Type: Admin

**HR:**
- Email: hr@infosys.com
- Password: hr123
- User Type: Company HR

**Student:**
- Email: student1@college.edu
- Password: student123
- User Type: Student

## Troubleshooting

### "JDBC Driver not found"
- Download ojdbc6.jar (see Step 1)
- Place it in `lib` folder
- Rebuild the project

### "Connection failed"
- Start Oracle Database:
  - Windows Services → Start "OracleServiceXE"
  - Or run: `lsnrctl start`
- Check db.properties settings
- Verify user exists: `sqlplus trandpl/trandpl`

### "Table or view does not exist"
- Run schema.sql script (Step 2)
- Connect as trandpl user first

### Build fails
- Install JDK 8+: https://www.oracle.com/java/technologies/javase-downloads.html
- Set JAVA_HOME environment variable
- Add Java to PATH

## Next Steps

1. Read the full README.md for detailed information
2. Explore the application features
3. Customize db.properties for your setup
4. Change default passwords for security

## File Locations

```
D:\Java Major Project\trandpl\
├── lib\ojdbc6.jar          ← Place JDBC driver here
├── db.properties           ← Configure database here
├── schema.sql              ← Run this in Oracle
├── build.bat               ← Build the project
├── run.bat                 ← Run the application
└── README.md               ← Full documentation
```

---

Need help? Check README.md or the troubleshooting section.
