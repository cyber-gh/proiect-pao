package dev.skyit.pao.database.sqlite.statements;

import java.util.List;

public interface CRUDSQLIface<T> {
    public String insertStatement(T element);
    public String readAllStatement();
    public String updateStatement(T oldElement, T newElement);
    public String deleteStatement(T element);
}
