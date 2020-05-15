package dev.skyit.pao.api;

import dev.skyit.pao.client.Client;
import dev.skyit.pao.client.transfers.Transfer;
import dev.skyit.pao.exceptions.TransferException;

import java.util.List;

public interface ServiceIFace {

    void addClient(Client newClient);
    void removeClient(Integer clientId);
    Client getClientById(Integer id);

    void registerAccountForClient(Integer clientId,
                                  Integer currencyId);
    void makeTransferForClient(Integer clientId,
                               Integer sourceId,
                               Integer targetId,
                               Double amount) throws TransferException;

    Transfer getLastTransfer(Integer clientId);
    Double getTotalBalanceInCurrency(Integer clientId,
                                     Integer currencyId);

    List<Transfer> getTransfersBiggerThan(Integer clientId,
                                          Double amount);

    List<Client> getAllClients();
}
