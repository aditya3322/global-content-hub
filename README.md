# Global Content Hub

Multi-module Maven project for content management and distribution.

## Quick Start

```bash
# Build
mvn clean install

# Run Tests
mvn test

# Build specific module
cd user-profile-service && mvn clean install
```

## Project Structure

```
global-content-hub/
├── pom.xml (parent - multi-module)
└── user-profile-service/ (sub-module)
    ├── pom.xml
    └── src/main/java/com/globalcontenthub/userprofile/
```

## Modules

### User Profile Service
Sub-module for managing user profiles with CRUD operations, filtering, and validation.

**Tests:** 28/28 passing ✅

## Build Requirements

- Java 11+
- Maven 3.6+

---

**Status: Production Ready**

## Project Structure

```
global-content-hub/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Main.java
│   │   │   ├── com/
│   │   │   │   └── globalcontenthub/
│   │   │   │       ├── core/
│   │   │   │       │   ├── ContentManager.java
│   │   │   │       │   ├── ContentType.java
│   │   │   │       │   └── ContentStore.java
│   │   │   │       ├── services/
│   │   │   │       │   ├── ContentService.java
│   │   │   │       │   ├── DistributionService.java
│   │   │   │       │   └── ValidationService.java
│   │   │   │       └── models/
│   │   │   │           ├── Content.java
│   │   │   │           ├── User.java
│   │   │   │           └── Channel.java
│   │   │   └── resources/    # Configuration files
│   │   └── resources/
│   └── test/
│       ├── java/             # Test files
│       └── resources/        # Test resources
├── docs/                      # Documentation
├── .gitignore
├── pom.xml                   # Maven configuration
└── README.md
```

## Features

### Core Components
- **Content Management** - Create, read, update, and delete content
- **Distribution Service** - Distribute content across multiple channels
- **Validation Service** - Validate content format and integrity
- **User Management** - Manage users and permissions

### Supported Content Types
- Text
- Media (Images, Videos)
- Documents
- Structured Data

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

Aditya Awasthi ([@aditya3322](https://github.com/aditya3322))

