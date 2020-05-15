package dev.skyit.pao.database.sqlite.daos;

import dev.skyit.pao.client.CompanyClient;
import dev.skyit.pao.database.sqlite.statements.CRUDSQLIface;
import dev.skyit.pao.database.sqlite.statements.CompanyClientStatements;

import java.sql.Connection;
import java.util.List;

public class CompanyClientsDao extends DatabaseDao implements CRUD<CompanyClient> {

    private final CRUDSQLIface<CompanyClient> sqlHelper = new CompanyClientStatements();

    public CompanyClientsDao(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(CompanyClient element) {
        executeUpdateStatement(sqlHelper.insertStatement(element));
    }

    @Override
    public List<CompanyClient> readAll() {
        return executeSelectStatement(sqlHelper.readAllStatement(),
                rs -> {
                    Integer id = rs.getInt("id");
                    String alias = rs.getString("alias");
                    Double commission = rs.getDouble("commission");

                    return new CompanyClient(id, alias, commission);
                });
    }

    @Override
    public void update(CompanyClient oldElement, CompanyClient newElement) {
        executeUpdateStatement(sqlHelper.updateStatement(oldElement, newElement));
    }

    @Override
    public void delete(CompanyClient element) {
        executeUpdateStatement(sqlHelper.deleteStatement(element));
    }
}
