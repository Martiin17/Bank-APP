package memo1.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Register {
    private long correlativeNumber;
    private long date; 
    private int hour;
    private String typeOfTransaction;
    private double amount;
    private List<Account> accounts;

    public Register(long correlativeNumber, long date, int hour, String typeOfTransaction, double amount, List<Account> accounts){
        this.correlativeNumber = correlativeNumber;
        this.date = date;
        this.hour = hour;
        this.typeOfTransaction = typeOfTransaction;
        this.amount = amount;
        this.accounts = accounts;
    }
}
