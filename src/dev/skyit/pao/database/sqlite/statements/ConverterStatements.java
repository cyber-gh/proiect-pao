package dev.skyit.pao.database.sqlite.statements;

import dev.skyit.pao.utility.CurrencyConvertRate;

public class ConverterStatements implements CRUDSQLIface<CurrencyConvertRate> {

    @Override
    public String insertStatement(CurrencyConvertRate element) {
        return String.format(
                "Insert into converters values (%d, %d, %.2f);",
                element.getSourceId(), element.getDestinationID(), element.getRate()
        );
    }

    @Override
    public String readAllStatement() {
        return String.format(
                "select * from converters"
        );
    }

    @Override
    public String updateStatement(CurrencyConvertRate oldElement, CurrencyConvertRate newElement) {
        return String.format(
                "update converters set rate=%.2f where sourceId = %d and destinationId = %d",
                newElement.getRate(), oldElement.getSourceId(), oldElement.getDestinationID()
        );
    }

    @Override
    public String deleteStatement(CurrencyConvertRate element) {
        return String.format(
                "delete from converters where sourceId = %d and destinationId = %d",
                element.getSourceId(), element.getDestinationID()
        );
    }
}

