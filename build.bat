@echo off
REM ============================================================================
REM Campus Placement Management System - Build Script
REM ============================================================================

echo ========================================
echo Building Campus Placement System
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install JDK 8 or higher
    pause
    exit /b 1
)

REM Check if JDBC driver exists
if not exist "lib\ojdbc6.jar" (
    echo WARNING: Oracle JDBC driver not found!
    echo Please download ojdbc6.jar and place it in the lib folder
    echo Download from: https://www.oracle.com/database/technologies/jdbcdriver-ucp-downloads.html
    echo.
    echo Press any key to continue anyway...
    pause
)

REM Check if Ant is installed
ant -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Apache Ant is not installed or not in PATH
    echo.
    echo Attempting to build with javac instead...
    echo.
    
    REM Create build directories
    if not exist "build\classes" mkdir "build\classes"
    
    REM Compile Java files
    echo Compiling Java files...
    javac -d build\classes -cp "lib\*" -sourcepath src src\trandpl\gui\LoginScreenFrame.java
    
    if %errorlevel% neq 0 (
        echo ERROR: Compilation failed
        pause
        exit /b 1
    )
    
    REM Copy resources
    echo Copying resources...
    xcopy /E /I /Y src\images build\classes\images
    xcopy /E /I /Y src\newimages build\classes\newimages
    
    REM Create JAR
    echo Creating JAR file...
    if not exist "dist" mkdir "dist"
    cd build\classes
    jar cvfe ..\..\dist\trandpl.jar trandpl.gui.LoginScreenFrame trandpl\* images\* newimages\* 2>nul
    cd ..\..
    
    echo.
    echo Build completed!
    echo JAR file created: dist\trandpl.jar
    echo.
    pause
    exit /b 0
)

REM Use Ant to build
echo Using Apache Ant to build...
call ant clean
call ant jar

if %errorlevel% equ 0 (
    echo.
    echo ========================================
    echo Build Successful!
    echo ========================================
    echo JAR file created: dist\trandpl.jar
    echo.
) else (
    echo.
    echo ========================================
    echo Build Failed!
    echo ========================================
    echo Please check the error messages above
    echo.
)

pause
