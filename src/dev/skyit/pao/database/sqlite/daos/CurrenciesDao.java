package dev.skyit.pao.database.sqlite.daos;

import dev.skyit.pao.utility.Currency;

import java.sql.Connection;
import java.util.List;

public class CurrenciesDao extends DatabaseDao implements CRUD<Currency> {
    public CurrenciesDao(Connection connection) {
        super(connection);
    }


    @Override
    public void insert(Currency element) {
        String query = "Insert into currencies " +
                String.format("values(%d, '%s', '%s');", element.getId(), element.getCode(), element.getName());
        executeUpdateStatement(query);
    }

    @Override
    public List<Currency> readAll() {
        String query = "select * from currencies";
        return executeSelectStatement(query, rs -> {
            Integer id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            return new Currency(id, code, name);
        });
    }

    @Override
    public void update(Currency oldElement, Currency newElement) {
        String query = "update currencies " +
                String.format("set code='%s', name='%s' ", newElement.getCode(), newElement.getName()) +
                String.format("where id = %d", oldElement.getId());

        executeUpdateStatement(query);
    }

    @Override
    public void delete(Currency element) {
        String query = String.format("delete from currencies where id=%d", element.getId());
        executeUpdateStatement(query);
    }
}
