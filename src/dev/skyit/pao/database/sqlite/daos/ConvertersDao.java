package dev.skyit.pao.database.sqlite.daos;

import dev.skyit.pao.database.sqlite.statements.CRUDSQLIface;
import dev.skyit.pao.database.sqlite.statements.ConverterStatements;
import dev.skyit.pao.utility.CurrencyConvertRate;

import java.sql.Connection;
import java.util.List;

public class ConvertersDao extends DatabaseDao implements CRUD<CurrencyConvertRate> {

    private final static CRUDSQLIface<CurrencyConvertRate> sqlHelper = new ConverterStatements();

    public ConvertersDao(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(CurrencyConvertRate element) {
        executeUpdateStatement(sqlHelper.insertStatement(element));
    }

    @Override
    public List<CurrencyConvertRate> readAll() {
        return executeSelectStatement(sqlHelper.readAllStatement(),
                rs -> {
                    Integer sourceId = rs.getInt("sourceId");
                    Integer destinationId = rs.getInt("destinationId");
                    Double rate = rs.getDouble("rate");
                    return new CurrencyConvertRate(sourceId, destinationId, rate);
                });
    }

    @Override
    public void update(CurrencyConvertRate oldElement, CurrencyConvertRate newElement) {
        executeUpdateStatement(sqlHelper.updateStatement(oldElement, newElement));
    }

    @Override
    public void delete(CurrencyConvertRate element) {
        executeUpdateStatement(sqlHelper.deleteStatement(element));
    }
}
