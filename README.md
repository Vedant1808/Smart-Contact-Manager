# 🌟 Smart Contact Manager 📇

**Smart Contact Manager** is an advanced Spring Boot-based web application designed to help users efficiently manage their contacts. With an intuitive user interface and powerful backend capabilities, it provides user authentications and full CRUD functionality to enhance productivity and streamline contact management.


# 🚀 Features

- ✅ **User Authentication** – Secure login and registration using Spring Security.
- ✅ **OAuth2 Authentication** - Login using Google or GitHub for secure and convenient access.
- ✅ **User Verification** - Verify account using email verification link.
- ✅ **CRUD Operations** – Add, update, delete, and view contacts seamlessly.
- ✅ **Search and Filter** – Quickly locate contacts using an optimized search functionality.
- ✅ **Profile Management** – Users can update their details and passwords.
- ✅ **RESTful API Integration** – Well-structured backend API for seamless communication.
- ✅ **Direct Mail** - Compose and Send emails directly from Project.
- ✅ **Profile Pircture** - Profile Picture is upload on Cloudinary Cloud.
- ✅ **Responsive UI** – Fully responsive design built using Tailwindcss and Thymeleaf.
- ✅ **Database Integration** – Secure storage using MySQL with JPA and Hibernate.
- ✅ **Theme** - Both Dark and Light Theme available.
- ✅ **Export Data** - Through single click you get data of all contact in excel sheet.
- ✅ **Extra** - Mark favourite Contact,Pagination and Feedback Support.


# 🛠️ Tech Stack

## Backend:
- 🌿 **Spring Boot**
- 🔐 **Spring Security**
- 📀 **Hibernate (JPA)**
- 💾 **MySQL**

## Frontend:
- 🌐 **HTML, CSS, JavaScript**
- 🎨 **Tailwindcss**
- 📝 **Thymeleaf**

## Other Tools & Libraries:
- 🏗️ **Maven**
- ⚡ **Lombok**
- 📂 **Spring Data JPA**
- ✉️ **Java Mail Service**
- 🎨 **Flowbite Components**

  ## Project Structure

- **src/main/java/com/example/scm**  # Backend Source Code
  - `config/`  # Spring Security Configurations
  - `controllers/`  # REST Controllers
  - `entities/`  # JPA Entity Classes
  - `repository/`  # Repository Interfaces
  - `services/`  # Business Logic Layer
  - `helper/` # Helper Classes
  - `forms/` # Form Related Classes


- **src/main/resources/**  # Resources
  - `templates/`  # Thymeleaf Templates (UI)
  - `static/`  # CSS, JS, and Images
  - `application.properties`  # Configuration File
  - 
- **pom.xml**  # Maven Dependencies
- **README.md**  # Documentation

 
   # 🏗️ Installation & Setup

1️⃣ **Clone the repository**

```bash
git clone https://github.com/yourusername/smart-contact-manager.git
```

2️⃣ **Configure the database**

Open `src/main/resources/application.properties` and update MySQL credentials:


```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
3️⃣  **Install Tailwind CSS**

To install and configure Tailwind CSS in your project, follow these steps:

1. Install Tailwind CSS using npm:

```bash
npm install -D tailwindcss
```

2. Run the build process to generate your CSS:

```bash
npx tailwindcss -i src/main/resources/static/css/input.css -o src/main/resources/static/css/output.css --watch
```

4️⃣  **Run the application**

```bash
mvn spring-boot:run
```

5️⃣**Access the application**

Visit [http://localhost:8080] in your browser.



# 📷 Screenshots

# 🤝 Contributing

💡 **Pull requests are welcome!** Feel free to open an issue for discussions or improvements.

## 📌 Guidelines:

- Follow best coding practices.
- Maintain proper documentation for new features.
- Ensure proper testing before submitting changes.


# 📧 Contact & Support

📬 For any questions, feedback, or collaboration requests, reach out at: vedantmahakal@gmail.com

# ⭐ If you find this project helpful, don’t forget to star the repository! ⭐








