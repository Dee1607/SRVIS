package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DatabaseConnectionTest {

    IDatabaseConnection db;

    @BeforeEach
    void setUp() {
        db = DatabaseConnection.databaseInstance();
    }

    @AfterEach
    void tearDown() {
        db.closeConnection();
    }

    @Test
    void databaseInstance() {
        assertNotNull(DatabaseConnection.databaseInstance());
    }

    @Test
    void makeConnection() {
        assertNotNull(db.makeConnection());
    }

    @Test
    void selectQuery() {
    }

    @Test
    void updateQuery() {
    }

    @Test
    void insertQuery1() {
    }

    @Test
    void insertQuery() {
    }

    @Test
    void testInsertQuery() {
    }
}