================================================================================
JDBC DRIVER REQUIRED - Oracle ojdbc6.jar
================================================================================

This folder should contain the Oracle JDBC driver (ojdbc6.jar) for the 
application to connect to Oracle Database.

================================================================================
HOW TO OBTAIN THE JDBC DRIVER:
================================================================================

OPTION 1: Download from Oracle Website
---------------------------------------
1. Visit: https://www.oracle.com/database/technologies/jdbcdriver-ucp-downloads.html
2. Download: ojdbc6.jar (for Java 8)
3. Place the downloaded file in this folder (lib/)

Direct download link (may require Oracle account):
https://download.oracle.com/otn/utilities_drivers/jdbc/11204/ojdbc6.jar


OPTION 2: Copy from Oracle Database Installation
-------------------------------------------------
If you have Oracle Database installed, the JDBC driver is already on your system:

Common locations:
- Windows: C:\app\oracle\product\11.2.0\dbhome_1\jdbc\lib\ojdbc6.jar
- Windows XE: D:\OracleDataBase\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar

Simply copy ojdbc6.jar from your Oracle installation to this folder.


OPTION 3: Use Maven Central Repository
---------------------------------------
Download from: https://repo1.maven.org/maven2/com/oracle/ojdbc/ojdbc6/11.2.0.4/
File: ojdbc6-11.2.0.4.jar
Rename to: ojdbc6.jar


================================================================================
AFTER DOWNLOADING:
================================================================================

1. Place ojdbc6.jar in this folder (lib/)
2. The final path should be: lib\ojdbc6.jar
3. Rebuild the project using build.bat or ant clean jar
4. Run the application

================================================================================
VERIFICATION:
================================================================================

After placing the file, your directory structure should look like:

trandpl/
├── lib/
│   ├── ojdbc6.jar        <-- Place the driver here
│   └── README.txt        <-- This file
├── src/
├── build.bat
├── run.bat
└── ...

File size: ojdbc6.jar should be approximately 2-3 MB

================================================================================
TROUBLESHOOTING:
================================================================================

If you get "ClassNotFoundException: oracle.jdbc.OracleDriver" error:
- Make sure ojdbc6.jar is in the lib/ folder
- Rebuild the project
- Check that the file is not corrupted

If you need help:
- See QUICKSTART.md for detailed setup instructions
- See README.md for full documentation

================================================================================
