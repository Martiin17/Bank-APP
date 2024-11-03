package memo1.ejercicio1;

public class MarriageRegister {
    private Client client1;
    private Client client2;
    private long marriageDate;

    public MarriageRegister(Client client1, Client client2, long marriageDate){
        this.client1 = client1;
        this.client2 = client2;
        this.marriageDate = marriageDate;
    }

    public boolean containsDNI(long DNI){
        if(DNI == client1.getDNI() || DNI == client2.getDNI()){
            return true;
        }else{
            return false;
        }
    }

    public long getMarriageDate(){
        return this.marriageDate;
    }
}
