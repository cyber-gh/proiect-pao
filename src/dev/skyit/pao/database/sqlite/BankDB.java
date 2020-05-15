package dev.skyit.pao.database.sqlite;

import dev.skyit.pao.client.Client;
import dev.skyit.pao.database.sqlite.daos.CRUD;
import dev.skyit.pao.database.sqlite.daos.ConvertersDao;
import dev.skyit.pao.database.sqlite.daos.CurrenciesDao;
import dev.skyit.pao.utility.Currency;
import dev.skyit.pao.utility.CurrencyConvertRate;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BankDB{

    private static final BankDB INSTANCE = new BankDB();

    public static BankDB getInstance() {
        return INSTANCE;
    }

    private final Connection connection = connect();

    private final CurrenciesDao currenciesDao = new CurrenciesDao(connection);
    private final ConvertersDao convertersDao = new ConvertersDao(connection);

    private Connection connect() {
        try {
            // Change path to match your setup
            String databaseURL = "jdbc:sqlite:/Users/soltan/Programs/proiect-pao/bank-db";
            return DriverManager.getConnection(databaseURL);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public CRUD<Currency> getCurrenciesDao() {
        return currenciesDao;
    }

    public CRUD<CurrencyConvertRate> getConvertersDao(){
        return convertersDao;
    }
}
