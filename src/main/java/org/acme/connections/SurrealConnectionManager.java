package org.acme.connections;

import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.SyncSurrealDriver;
import org.acme.model.Campaign;

public class SurrealConnectionManager {
    private static SurrealWebSocketConnection connection;
    private static  SyncSurrealDriver driver;

    public static synchronized SyncSurrealDriver getDriverConnection() {
        if (driver == null) {
            try {
                if (connection == null) {
                    connection = new SurrealWebSocketConnection("localhost", 8000, false);
                }
                connection.connect(30);
                driver = new SyncSurrealDriver(connection);
                driver.use("test", "test");
                driver.create("campaign", new Campaign("Make the world better"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
}