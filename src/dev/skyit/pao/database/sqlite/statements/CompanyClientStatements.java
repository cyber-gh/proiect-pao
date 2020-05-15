package dev.skyit.pao.database.sqlite.statements;

import dev.skyit.pao.client.CompanyClient;
import dev.skyit.pao.client.SimpleClient;

public class CompanyClientStatements implements CRUDSQLIface<CompanyClient> {
    @Override
    public String insertStatement(CompanyClient element) {
        return String.format(
                "insert into company_clients values(%d, '%s', %.2f)",
                element.getId(),
                element.getAlias(),
                element.getCommission()
        );
    }

    @Override
    public String readAllStatement() {
        return String.format(
                "select * from company_clients"
        );
    }

    @Override
    public String updateStatement(CompanyClient oldElement, CompanyClient newElement) {
        return String.format(
                "update company_clients set alias='%s', commission=%.2f where id = %d",
                newElement.getAlias(),
                newElement.getCommission(),
                oldElement.getId()
        );
    }

    @Override
    public String deleteStatement(CompanyClient element) {
        return String.format(
                "delete from company_clients where id = %d",
                element.getId()
        );
    }
}
