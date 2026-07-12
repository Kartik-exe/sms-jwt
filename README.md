# 🔐 SMS JWT Service

A lightweight, reusable JWT (JSON Web Token) library built with **Spring Boot 3** and **Java 21**. This project provides secure JWT generation, validation, and claim extraction that can be integrated into any Spring Boot application.

> Designed as a modular authentication library for enterprise applications.

---

## ✨ Features

- ✅ Generate Access Tokens
- ✅ Generate Refresh Tokens
- ✅ Generate Email Verification Tokens
- ✅ Generate Password Reset Tokens
- ✅ Validate JWT Tokens
- ✅ Extract Username and Claims
- ✅ Extract Token Expiration
- ✅ Token Expiry Validation
- ✅ HS256 Signature Support
- ✅ Spring Boot 3.x Compatible
- ✅ Java 21 Compatible
- ✅ Lightweight & Reusable

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Security
- JJWT (Java JWT)
- Maven
- Lombok

---

## 📂 Project Structure

```
src
 ├── config
 ├── constants
 ├── dto
 ├── exception
 ├── provider
 ├── service
 ├── util
 └── resources
```

---

## 🚀 Installation

Clone the repository

```bash
git clone https://github.com/Kartik-exe/sms-jwt.git
```

Build the project

```bash
mvn clean install
```

---

## ⚙️ Configuration

Configure your JWT properties.

```properties
jwt.secret=YOUR_SECRET_KEY

jwt.access-token-validity=900000

jwt.refresh-token-validity=28800000

jwt.email-token-validity=300000

jwt.issuer=sms-auth
```

---

## 📖 Usage

### Generate Access Token

```java
String token = jwtService.generateAccessToken(username);
```

### Generate Refresh Token

```java
String refreshToken = jwtService.generateRefreshToken(username);
```

### Validate Token

```java
boolean valid = jwtService.validateToken(token, username);
```

### Extract Username

```java
String username = jwtService.extractUsername(token);
```

### Extract Expiration

```java
Date expiration = jwtService.extractExpiration(token);
```

---

## 📌 Current Features

- Access Token Generation
- Refresh Token Generation
- Email Verification Token
- Password Reset Token
- Username Extraction
- Claims Extraction
- Token Validation
- Expiration Validation

---

## 🚧 Planned Features

- Role-Based Claims
- Custom Claims Support
- RSA / EC Signature Support
- Key Rotation
- Token Blacklisting
- Refresh Token Manager
- OAuth2 Integration
- Multi-Tenant Support
- Token Revocation API
- MFA Token Support

---

## 🔒 Security

- HS256 Signature
- Configurable Secret Key
- Configurable Token Expiry
- Secure Claim Validation
- Stateless Authentication Support

---

## 📦 Maven Dependency

Coming Soon

```xml
<dependency>
    <groupId>com.sms</groupId>
    <artifactId>jwt-service</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## 🤝 Contributing

Contributions are welcome!

Feel free to fork the repository, open issues, or submit pull requests.

---

## 📄 License

This project is licensed under the MIT License.