package dev.skyit.pao.database.csv;

import dev.skyit.pao.client.CompanyClient;
import dev.skyit.pao.client.SimpleClient;
import dev.skyit.pao.utility.CurrencyConvertRate;
import dev.skyit.pao.utility.Currency;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface ModelConverter<T>{
    public T transform(String[] components);
}

public class Database {

    public static final Database shared = new Database();

    private Database(){
    }

    private<T> List<T> read(String filename, ModelConverter<T> map) {
        ArrayList<T> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                var components = line.split(";");
                list.add(map.transform(components));
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Bad format");
            e.printStackTrace();
        }

        return list;
    }

    public List<Currency> loadCurrencies() {
        return read("persistence/currencies.csv", components -> {
            return new Currency(Integer.parseInt(components[0]),
                    components[1],
                    components[2]);
        });
    }

    public List<CurrencyConvertRate> loadRates() {
        return read("persistence/rates.csv", components -> {
            return new CurrencyConvertRate(Integer.parseInt(components[0]),
                    Integer.parseInt(components[1]),
                    Double.parseDouble(components[2]));
        });
    }

    public List<SimpleClient> loadSimpleClients() {
        return read("persistence/clients.csv", components -> {
            return new SimpleClient(Integer.parseInt(components[0]),
                    components[1],
                    null);
        });
    }

    public List<CompanyClient> loadCompanyClients() {
        return read("persistence/companies.csv", components -> {
            return new CompanyClient(Integer.parseInt(components[0]),
                    components[1],
                    Double.parseDouble(components[2]),
                    null);
        });
    }
}
