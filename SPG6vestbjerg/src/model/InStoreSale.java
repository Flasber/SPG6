package model;
import java.util.*;
public class InStoreSale {
    private int registerNo;
    private Employee e;
    private Customer c;
    private List<Product> products;
    //skal man også have en List<Copy> copies?

    public InStoreSale(int registerNo,Employee e){
        this.registerNo=registerNo;
        this.e=e;
        products=new ArrayList<>();
    }

    public void setCustomer(Customer c){
        this.c=c;
    }



}
