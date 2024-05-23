package model;
import java.util.*;

public class BillableContainer {
private static BillableContainer instance;
private List<Billable> billables; 

private BillableContainer(){
    billables=new ArrayList<>();
}

public static BillableContainer getInstance(){
    if(instance==null){
        instance=new BillableContainer();
    }
    return instance;
}

public boolean addSale(Billable s){
    boolean addToSale=false;
    if(s!=null){
        billables.add(s);
        addToSale=true;
    }
    return addToSale;
}

}
