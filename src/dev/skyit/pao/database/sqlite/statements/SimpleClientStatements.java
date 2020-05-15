package dev.skyit.pao.database.sqlite.statements;

import dev.skyit.pao.client.SimpleClient;

public class SimpleClientStatements implements CRUDSQLIface<SimpleClient> {
    @Override
    public String insertStatement(SimpleClient element) {
        return String.format(
                "insert into simple_clients values(%d, '%s')",
                element.getId(),
                element.getAlias()
        );
    }

    @Override
    public String readAllStatement() {
        return String.format(
                "select * from simple_clients"
        );
    }

    @Override
    public String updateStatement(SimpleClient oldElement, SimpleClient newElement) {
        return String.format(
                "update simple_clients set alias='%s' where id = %d",
                newElement.getAlias(),
                oldElement.getId()
        );
    }

    @Override
    public String deleteStatement(SimpleClient element) {
        return String.format(
                "delete from simple_clients where id = %d",
                element.getId()
        );
    }
}
