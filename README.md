# Flight Booking API

A Spring Boot REST API for managing flights and booking tickets. This project facilitates flight creation, seat monitoring, and ticket booking using thread-safe mechanisms to handle concurrent requests.

---

## 🛠 Technologies Used

* **Java:** 17
* **Framework:** Spring Boot 4.0.5
* **Build Tool:** Maven
* **Testing:** JUnit 5, Mockito
* **Utilities:** Lombok, Jackson, Jakarta Validation

---

## ✨ Features

* **Flight Initialization:** Create new flights with specific seat capacities.
* **Seat Management:** Real-time seat availability validation.
* **Concurrency Support:** Thread-safe reservations using `AtomicInteger`.
* **Error Handling:** Global exception handling for "Flight Not Found" and "No Seats Available."

---

## 🚀 API Endpoints

### 1. Create Flight
Registers a new flight into the system.

* **URL:** `/flights`
* **Method:** `POST`
* **Payload:**
    ```json
    {
      "flightNumber": "FL123",
      "totalSeats": 10
    }
    ```
* **Response:**
    ```json
    {
      "flightNumber": "FL123",
      "totalSeats": 10,
      "availableSeats": 10
    }
    ```

### 2. Book Ticket
Reserves a seat for a passenger on a specific flight.

* **URL:** `/bookings`
* **Method:** `POST`
* **Payload:**
    ```json
    {
      "flightNumber": "FL123",
      "passengerName": "John Doe"
    }
    ```
* **Response:**
    ```json
    {
      "bookingId": 1,
      "message": "Booking successful"
    }
    ```

---

## 📦 How to Run

### Option 1: Using Maven Wrapper
```bash
./mvnw clean install
./mvnw spring-boot:run
```

### Option 2: Using Maven Wrapper
```bash
mvn clean install
mvn spring-boot:run
```

Note: The API will be accessible at http://localhost:8080
## 🧪 Running Tests
Validate the application logic (Controller, Service, and Exception layers) by running:

```Bash
./mvnw test
```

## 🧪 Future improvements: 
* Could add more detailed error response examples, flight search or destination logic, DB intergation, integration tests, and API authentication for production readiness.
### 📝 Technical Notes
* **Thread Safety**: Designed to prevent overbooking during high-traffic periods using AtomicInteger.

* **Validation**: All inputs are validated via jakarta.validation to ensure data integrity.

* **IDs**: Booking IDs are automatically generated upon successful reservation.