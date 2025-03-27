# Golf Club Tournament & Membership API

This project is a simple REST API to manage golf club members and tournaments. It uses Spring Boot, MySQL, Docker, and Postman for API testing. The API supports:
- Creating and retrieving Members and Tournaments.
- Adding a Member to a Tournament.
- Searching for Members (by name, phone number, etc.) and Tournaments (by location, start date, etc.).

# Technology used
- Java (Spring Boot)
- MySQL
- Docker & Docker Compose
- Postman (for API testing)

# Endpoints

# Members
- **GET /members** – Retrieve all members.
- **POST /members** – Create a new member.
- **GET /members/{id}** – Get a member by ID.
- **GET /members/search?name=xxx** – Search members by name (additional search parameters can be added as needed).

# Tournaments
- **GET /tournaments** – Retrieve all tournaments.
- **POST /tournaments** – Create a new tournament.
- **GET /tournaments/{id}** – Get a tournament by ID.
- **POST /tournaments/{tournamentId}/members/{memberId}** – Add a member to a tournament.
- **GET /tournaments/search?location=xxx** – Search tournaments by location.

# Running the Project with Docker

1. **Prerequisites:**  
   Ensure that Docker and Docker Compose are installed on your machine.

2. **Clone the Repository:**  
   ```bash
   git clone https://github.com/B-Penney/golf-club-tournament-api.git
   cd golf-club-tournament-api
