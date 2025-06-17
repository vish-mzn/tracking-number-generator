# tracking-number-generator

This is a Spring-Boot based Restful application, implemented using Java. The focus is to create a TRACKING-NUMBER-GENERATOR API using the best coding practices.
A scalable and efficient Spring Boot API that generates unique tracking numbers for parcels.

## Project Setup

1. Clone this repository:
   ```bash
   git clone https://github.com/vish-mzn/tracking-number-generator.git
   


## 🔧 Technologies Used
- Java 17
- Spring Boot 3.x
- Maven

## 🚀 API Endpoint
```
GET /next-tracking-number
```
### Query Parameters:
| Parameter | Type | Description |
|----------|------|-------------|
| origin_country_id | string (ISO ALPHA-2) | e.g., "MY" |
| destination_country_id | string (ISO ALPHA-2) | e.g., "ID" |
| weight | double | e.g., 1.234 |
| created_at | RFC3339 timestamp | e.g., 2024-06-13T17:00:00+08:00 |
| customer_id | UUID | e.g., `de619854-b59b-425e-9db4-943979e1bd49` |
| customer_name | string | e.g., RedBox Logistics |
| customer_slug | string (kebab-case) | e.g., redbox-logistics |

### Example:
```
GET http://localhost:8080/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2024-06-13T17:00:00%2B08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics
```

## ✅ Response:
```json
{
  "tracking_number": "MYID240613A3F9B7",
  "created_at": "2024-06-13T17:10:00+08:00"
}
```

## 🚀 Deployment
You can deploy to:
- [Render](https://render.com/)
- [Railway](https://railway.app/)
- [Heroku](https://www.heroku.com/)
- [Fly.io](https://fly.io/)

---

## 📦 Build & Run Locally
```bash
# Build
./gradlew clean build

# Run
./gradlew bootRun
```

---

This implementation is **stateless**, suitable for **horizontal scaling**, and uses UUID-based suffix to ensure global uniqueness without any external state like Redis or DB.

Feel free to fork and extend with persistence, Redis, rate limiting, or logging for production setups.
