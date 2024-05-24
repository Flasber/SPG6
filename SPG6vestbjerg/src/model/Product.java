package model;

import java.math.BigDecimal;

public class Product {
 private String description;
 private String name;
 private String sku;
 private String barcode;
 private BigDecimal price;


 public Product(String description, String name, String sku, String barcode,BigDecimal price){
    this.description=description;
    this.name=name;
    this.sku=sku;
    this.barcode=barcode;
    this.price=price;
 }

 public String getBarcode(){
    return barcode;
 }

}
