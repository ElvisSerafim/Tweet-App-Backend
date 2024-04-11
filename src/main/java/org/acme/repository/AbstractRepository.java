package org.acme.repository;

import com.surrealdb.driver.SyncSurrealDriver;
import com.surrealdb.driver.model.QueryResult;
import org.acme.connections.SurrealConnectionManager;

import java.util.List;
import java.util.Map;

public class AbstractRepository<T> {
    private final SyncSurrealDriver driver;
    private final String tableName;
    private final Class<T> clazz;

    public AbstractRepository(String tableName, Class<T> clazz) {
        this.driver = SurrealConnectionManager.getDriverConnection();
        this.tableName = tableName;
        this.clazz = clazz;
    }

    public T create(T entity) {
        return driver.create(tableName, entity);
    }

    public List<Map<String, String>> update(String id, Map<String, String> updates) {
        return driver.update(id, updates);
    }

    public List<T> select() {
        return driver.select(tableName, clazz);
    }

    public List<QueryResult<T>> query(String query, Map<String, String> args) {
        return driver.query(query, args, clazz);
    }

}