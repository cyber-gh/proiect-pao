package dev.skyit.pao.database.sqlite.daos;

import dev.skyit.pao.database.sqlite.statements.CRUDSQLIface;
import dev.skyit.pao.database.sqlite.statements.CurrencyStatements;
import dev.skyit.pao.utility.Currency;

import java.sql.Connection;
import java.util.List;

public class CurrenciesDao extends DatabaseDao implements CRUD<Currency> {

    private final CRUDSQLIface<Currency> sqlBuilder = new CurrencyStatements();

    public CurrenciesDao(Connection connection) {
        super(connection);
    }


    @Override
    public void insert(Currency element) {
        String query = sqlBuilder.insertStatement(element);
        executeUpdateStatement(query);
    }

    @Override
    public List<Currency> readAll() {
        String query = sqlBuilder.readAllStatement();
        return executeSelectStatement(query, rs -> {
            Integer id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            return new Currency(id, code, name);
        });
    }

    @Override
    public void update(Currency oldElement, Currency newElement) {
        String query = sqlBuilder.updateStatement(oldElement, newElement);

        executeUpdateStatement(query);
    }

    @Override
    public void delete(Currency element) {
        String query = sqlBuilder.deleteStatement(element);
        executeUpdateStatement(query);
    }
}
