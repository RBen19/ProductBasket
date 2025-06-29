# ProduitPanier

ProduitPanier is a Java application designed for managing products, shopping baskets (paniers), and related e-commerce entities. It utilizes Java with Maven for project management, JPA (Hibernate) for object-relational mapping, and PostgreSQL as the database backend.

## Core Functionalities

*   **Product Management**: Create and manage products, including their codes, prices, and names. Products can be associated with categories.
*   **Basket Management**: Create and manage shopping baskets.
*   **Product-Basket Association**: Link products to baskets, specifying quantities for each item (via the `ProductBasket` entity).
*   **Client and Order Management**: Manage client information and their respective orders (`Commandes`).

The application currently features a basic command-line interface (CLI) in `src/main/java/org/edu/isi/Main.java` for performing initial operations such as creating products, baskets, and linking them.

Logging is implemented using Log4j2, and error reporting is integrated with Sentry.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 21 or higher.
*   Apache Maven.
*   PostgreSQL database server.

### Database Setup

1.  Ensure you have a PostgreSQL instance running.
2.  Create a database for the application.
3.  Database connection details are configured within the application, likely in `src/main/java/org/edu/isi/services/JpaUtils.java` or a persistence configuration file (e.g., `persistence.xml` typically found in `src/main/resources/META-INF/`). You may need to adjust these settings to match your environment.

### How to Run

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd ProduitPanier
    ```
2.  **Build the project using Maven:**
    ```bash
    mvn clean install
    ```
3.  **Run the application:**
    The `Main.java` class contains a `main` method with a simple CLI. You can run this class from your IDE (e.g., IntelliJ IDEA, Eclipse) or package it into a JAR and run it (though specific packaging for a runnable JAR might need further configuration in `pom.xml`).

    To run from an IDE:
    *   Open the project in your IDE.
    *   Locate `src/main/java/org/edu/isi/Main.java`.
    *   Run the `main` method.

## Project Structure

*   `pom.xml`: Maven project configuration, including dependencies.
*   `src/main/java/org/edu/isi/`: Main Java source code.
    *   `Main.java`: Entry point with a CLI for basic operations.
    *   `entities/`: JPA entity classes (`Product`, `Basket`, `Client`, `Commandes`, `Categorie`, `ProductBasket`).
    *   `services/`: Service classes, including `JpaUtils.java` for JPA helper functions.
*   `src/main/resources/`: Resource files.
    *   `log4j2.xml`: Configuration for Log4j2.
    *   `META-INF/persistence.xml`: (If present, standard location for JPA configuration)

## Contributing

Details on contributing to this project will be added here in the future.
