package memo1.ejercicio1;

public class Sucursal {
    private int sucursalNumber;
    private String name;
    private String direccion;
    //Tiene cuentas tmb? Quien tiene clientes y quien cuentas?

    public Sucursal(int sucursalNumber,  String direccion){
        this.sucursalNumber = sucursalNumber;
        this.direccion = direccion;
    }

    public Sucursal(int sucursalNumber,  String direccion, String name){
        this.sucursalNumber = sucursalNumber;
        this.direccion = direccion;
        this.name = name;
    }

    private int getSucursalNumber(){
        return this.sucursalNumber;
    }

    private void setSucursalNumber(int sucursalNumber){
        this.sucursalNumber = sucursalNumber;
    }

    private String getname(){
        return this.name;
    }

    private void setName(String name){
        this.name = name;
    }

    private String getDireccion(){
        return this.direccion;
    }

    private void setDireccion(String direccion){
        this.direccion = direccion;
    }
}
