package dev.skyit.pao.database.sqlite.daos;

import dev.skyit.pao.client.SimpleClient;
import dev.skyit.pao.database.sqlite.statements.CRUDSQLIface;
import dev.skyit.pao.database.sqlite.statements.SimpleClientStatements;

import java.sql.Connection;
import java.util.List;

public class SimpleClientsDao extends DatabaseDao implements CRUD<SimpleClient> {

    private final CRUDSQLIface<SimpleClient> sqlHelper = new SimpleClientStatements();

    public SimpleClientsDao(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(SimpleClient element) {
        executeUpdateStatement(sqlHelper.insertStatement(element));
    }

    @Override
    public List<SimpleClient> readAll() {
        return executeSelectStatement(sqlHelper.readAllStatement(),
                rs -> {
                    Integer id = rs.getInt("id");
                    String alias = rs.getString("alias");

                    return new SimpleClient(id, alias);
                });
    }

    @Override
    public void update(SimpleClient oldElement, SimpleClient newElement) {
        executeUpdateStatement(sqlHelper.updateStatement(oldElement, newElement));
    }

    @Override
    public void delete(SimpleClient element) {
        executeUpdateStatement(sqlHelper.deleteStatement(element));
    }
}
