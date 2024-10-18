package memo1.ejercicio1;

public class Client {
    private long DNI;
    private String name;
    private String surname;
    private String direction;
    private long bornDate;

    public Client(long DNI,String name,String surname,String direction, long bornDate){
        this.DNI = DNI;
        this.name = name;
        this.surname = surname;
        this.direction = direction;
        this.bornDate = bornDate;
    }
}
