package Model;

import DAO.ProductDao;

import java.util.ArrayList;

public class Meniu {
    ArrayList<ProductDao> products = new ArrayList<ProductDao>();

     public void add (ProductDao product)
     {
         this.products.add(product);
     }
    public ArrayList<ProductDao> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductDao> products) {
        this.products = products;
    }
}
