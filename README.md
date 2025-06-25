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
- [Heroku](https://www.heroku.com/)
1. Make sure you have an heroku verified account.
STEP 1 : Need to create the heroku app (a new app within heroku environment)
```
heroku create

Creating app... done, ⬢ cryptic-journey-79303
https://cryptic-journey-79303-ca2113a2dabd.herokuapp.com/ | https://git.heroku.com/cryptic-journey-79303.git
```
It will create a new repository and provide you a secure url to access your application.
One you have done STEP 1 that means you already created a repository within heroku env.

STEP 2 : Verify the heroku repository
```
git remote 

heroku
origin
```

STEP 3 : PUSHING to heroku repository
```
git push heroku master

this will push your local repo code to heroku app you just created
and heroku itself will take care of the build and deployment of your application
```

STEP 4 : Verify your changes are available online
```
you can access your application using the url provided by heroku at the time of project creation

https://cryptic-journey-79303-ca2113a2dabd.herokuapp.com/

Then if you want to add the swagger url that also you can do
```

---

## SWAGGER CONFIG
```
http://localhost:9090/swagger-ui/index.html

https://cryptic-journey-79303-ca2113a2dabd.herokuapp.com/swagger-ui/index.html
```

---

## 📦 Build & Run Locally
```bash
# Build
mvn clean build

# Run
mvn bootRun
```

---

This implementation is **stateless**, suitable for **horizontal scaling**, and uses UUID-based suffix to ensure global uniqueness without any external state like Redis or DB.

Feel free to fork and extend with persistence, Redis, rate limiting, or logging for production setups.
