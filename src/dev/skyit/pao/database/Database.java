package dev.skyit.pao.database;

import dev.skyit.pao.client.SimpleClient;
import dev.skyit.pao.utility.ConvercyRate;
import dev.skyit.pao.utility.Currency;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Currency> currencies = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("persistence/currencies.csv"));
            String line = reader.readLine();
            while (line != null) {
                var components = line.split(";");
                currencies.add(new Currency(Integer.parseInt(components[0]), components[1], components[2]));
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Bad currency format");
            e.printStackTrace();
        }

        return currencies;
    }

    public List<ConvercyRate> loadRates() {
        ArrayList<ConvercyRate> rates = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("persistence/rates.csv"));
            String line = reader.readLine();
            while (line != null) {
                var components = line.split(";");
                rates.add(new ConvercyRate(Integer.parseInt(components[0]),
                        Integer.parseInt(components[1]),
                        Double.parseDouble(components[2])));
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Bad rate format");
            e.printStackTrace();
        }

        return rates;
    }

//    public List<SimpleClient> loadSimpleClients() {
//
//    }
}
