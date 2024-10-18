package memo1.ejercicio1;

public class Register {
    private long correlativeNumber;
    private long date; 
    private int hour;
    private String typeOfTransaction;
    private int amount;

    public Register(long correlativeNumber, long date, int hour, String typeOfTransaction, int amount){
        this.correlativeNumber = correlativeNumber;
        this.date = date;
        this.hour = hour;
        this.typeOfTransaction = typeOfTransaction;
        this.amount = amount;
    }
}
