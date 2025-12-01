# 🎓 Campus Placement Management System  
*A Java + Oracle-based Desktop Application*

---

## 🌟 Overview  

The **Campus Placement Management System** is a full-featured Java desktop application designed to streamline recruitment between **students, HR managers, and administrators**.  
It enables smooth handling of job postings, applications, quizzes, and results within a centralized system built using **Java Swing**, **Oracle Database**, and **Apache Ant**.

---

## 🧩 Tech Stack  

| Component | Technology Used |
|------------|-----------------|
| **Frontend (GUI)** | Java Swing |
| **Backend** | Java (Core & JDBC) |
| **Database** | Oracle Database XE (11g / 21c) |
| **Build Tool** | Apache Ant |
| **IDE** | NetBeans / IntelliJ |
| **JDBC Driver** | ojdbc6.jar / ojdbc8.jar |

---

## 🚀 Features  

✅ Admin module for managing HRs and students  
✅ HR module to post, edit, and remove job openings  
✅ Student module to apply for jobs and take quizzes  
✅ Secure authentication system for all users  
✅ Automated quiz generation and evaluation  
✅ Database-driven storage for users, jobs, and results  
✅ Smooth GUI interface with intuitive navigation  

---

## 🛠️ How to Clone & Run Locally  

### 1️⃣ Clone the Repository

```bash
cd D:\
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>/trandpl
```

---

### 2️⃣ Install Prerequisites  

1. **Java JDK 8 or above**  
   - Download: [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)  
   - Verify:  
     ```bash
     java -version
     ```

2. **Oracle Database XE (11g / 21c)**  
   - Ensure the database service is **running** (`XEPDB1` for 21c or `XE` for 11g).

3. **Apache Ant** *(recommended)*  
   - Download: [Apache Ant](https://ant.apache.org/bindownload.cgi)  
   - Verify:  
     ```bash
     ant -version
     ```

4. **Oracle JDBC Driver**  
   - Place `ojdbc6.jar` or `ojdbc8.jar` inside the project’s `lib/` folder.

   ```text
   trandpl/
     lib/
       ojdbc8.jar
       AbsoluteLayout.jar
   ```

---

### 3️⃣ Configure Oracle Database  

#### Create Database User  

For **Oracle 21c XE**:
```sql
sqlplus sys/YourSysPassword@//localhost:1521/XEPDB1 as sysdba
```

For **Oracle 11g XE**:
```sql
sqlplus sys/YourSysPassword@//localhost:1521/xe as sysdba
```

Then execute:
```sql
CREATE USER trandpl IDENTIFIED BY trandpl;
GRANT CONNECT, RESOURCE, CREATE VIEW, UNLIMITED TABLESPACE TO trandpl;
```

If the user exists:
```sql
ALTER USER trandpl IDENTIFIED BY trandpl ACCOUNT UNLOCK;
```

#### Run the Schema

```sql
CONNECT trandpl/trandpl@//localhost:1521/XEPDB1
@schema.sql
```

---

### 4️⃣ Update Database Configuration  

Edit the `db.properties` file:

#### For Oracle 21c XE:
```properties
db.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
db.user=trandpl
db.password=trandpl
```

#### For Oracle 11g XE:
```properties
db.url=jdbc:oracle:thin:@//localhost:1521/xe
db.user=trandpl
db.password=trandpl
```

---

### 5️⃣ Build the Project  

**Option A – Using Ant**
```bash
ant clean jar
```

**Option B – Using Batch File**
```bash
build.bat
```

> This will generate the executable file:  
> **`dist/trandpl.jar`**

---

### 6️⃣ Run the Application  

**Option A – Directly via JAR**
```bash
java -jar dist/trandpl.jar
```

**Option B – Using Run Script**
```bash
run.bat
```

This launches the **Campus Placement Management System** interface 🎯

---

## 👥 Default Login Credentials  

| Role | Email | Password | Type |
|------|--------|-----------|------|
| **Admin** | admin@trandpl.com | admin123 | Admin |
| **HR** | hr@infosys.com | hr123 | Company HR |
| **Student** | student1@college.edu | student123 | Student |

---

## 📂 Project Structure  

```
trandpl/
├── src/                   # Source code
│   ├── trandpl/dao/       # Data Access Objects
│   ├── trandpl/gui/       # GUI Frames (Swing)
│   ├── trandpl/pojo/      # Model classes
│   └── trandpl/dbutil/    # DB connection utilities
├── lib/                   # External libraries (JDBC, layouts)
├── dist/                  # Compiled JAR output
├── db.properties          # DB configuration
├── build.xml              # Ant build script
├── build.bat, run.bat     # Windows scripts
└── schema.sql             # Database setup script
```

---

## 💡 Screenshots (Optional Section)

> *(Add screenshots here once hosted on GitHub or local repo)*  
Example:
```
![Login Screen](screenshots/login.png)
![Admin Dashboard](screenshots/admin-dashboard.png)
```

---

## ❤️ Contributing  

Contributions are always welcome!  

1. Fork the repo  
2. Create a new branch (`feature/my-feature`)  
3. Commit your changes  
4. Push to the branch  
5. Create a **Pull Request**

---

## 📜 License  

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## ✨ Author  

👨‍💻 **Ankur Rai**  
📧 [ankur.raii@gmail.com](mailto:ankur.raii@gmail.com)  
🌐 [GitHub Profile](https://github.com/ankur-raii)
