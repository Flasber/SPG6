package model;

public class Customer {
    private String customerId;
    private String name;
    private String tlf;
   
 public Customer(String customerId,String name,String tlf){
    this.customerId=customerId;
    this.name=name;
    this.tlf=tlf;
 } 

 public String getTlf(){
    return tlf;
 }

}
