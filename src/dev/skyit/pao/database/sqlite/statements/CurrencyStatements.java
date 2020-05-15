package dev.skyit.pao.database.sqlite.statements;

import dev.skyit.pao.utility.Currency;

public class CurrencyStatements implements CRUDSQLIface<Currency> {
    @Override
    public String insertStatement(Currency element) {
        return String.format(
                "Insert into currencies values (%d, '%s', '%s');",
                element.getId(), element.getCode(), element.getName()
        );
    }

    @Override
    public String readAllStatement() {
        return String.format(
                "select * from currencies"
        );
    }

    @Override
    public String updateStatement(Currency oldElement, Currency newElement) {
        return String.format(
                "update currencies set code='%s', name='%s' where id = %d",
                newElement.getCode(), newElement.getName(), oldElement.getId()
        );
    }

    @Override
    public String deleteStatement(Currency element) {
        return String.format(
                "delete from currencies where id=%d",
                element.getId());
    }
}
