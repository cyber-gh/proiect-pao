package dev.skyit.pao.database.sqlite.daos;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface DatabaseElementConverter<T> {
    public T convert(ResultSet rs) throws SQLException;
}

public class DatabaseDao {

    private final Connection connection;

    public DatabaseDao(Connection connection) {
        this.connection = connection;
    }

    protected void executeUpdateStatement(String query) {
        try  {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected<T> List<T> executeSelectStatement(String query, DatabaseElementConverter<T> converter) {
        List<T> elements = new ArrayList<>();
        try( Statement stm = connection.createStatement()){
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                elements.add(converter.convert(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return elements;
    }
}
