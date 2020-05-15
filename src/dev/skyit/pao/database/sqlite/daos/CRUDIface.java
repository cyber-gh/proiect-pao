package dev.skyit.pao.database.sqlite.daos;


import dev.skyit.pao.client.Client;
import dev.skyit.pao.utility.Currency;

import java.util.List;

interface CRUD<T> {
    public void insert(T element);
    public List<T> readAll();
    public void update(T oldElement, T newElement);
    public void delete(T element);
}


