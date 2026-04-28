# stayhub

## Getting Started

### Prerequisites
- Docker & Docker Compose
- Java 21+

### Environment Variables

This project uses a `.env` file for local configuration. Copy the example and fill in your values:

```bash
cp .env.example .env
```

The `.env` file is **not committed** to version control. See `.env.example` for all required variables.

### IDE Configuration

Configure your IDE to load environment variables from `.env` (IntelliJ: Run Configuration → Environment variables → load from file).

### Run the database

```bash
docker-compose up -d
```

### Run the application

```bash
./gradlew bootRun
```
