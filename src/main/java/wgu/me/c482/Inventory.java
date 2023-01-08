package wgu.me.c482;

import javafx.collections.ObservableList;

import java.util.*;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part part) {
        this.allParts.add(part);
    }

    public void addProduct(Product product) {
        this.allProducts.add(product);
    }

    public Part lookupPart(int partId) {

}

    public Product lookupProduct(int productId) {

    }

    public ObservableList<Part> lookupPart(String partName) {

    }

    public ObservableList<Product> lookupProduct(String productName) {

    }

    public void updatePart(int index, Part selectedPart) {
        this.allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        this.allProducts.set(index, newProduct);
    }

    public boolean deletePart(Part selectedPart) {
        return this.allParts.remove(selectedPart);
    }

    public boolean deleteProduct(Product selectedProduct) {
        return this.allProducts.remove(selectedProduct);
    }

    public ObservableList<Part> getAllParts() {
        return this.allParts;
    }
    public ObservableList<Product> getAllProducts() {
        return this.allProducts;
    }
}
