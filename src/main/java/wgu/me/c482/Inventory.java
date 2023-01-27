package wgu.me.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;
    int partId = 1, productId = 1;

    Inventory() {
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }

    /**
     *
     * @param part the part that gets added into the allParts ObservableList
     */
    public void addPart(Part part) {
        this.allParts.add(part);
        System.out.println("Part " + part.getName() + " added to inventory");
    }

    /**
     *
     * @param product the product that gets added into the allProducts ObservableList
     */
    public void addProduct(Product product) {
        this.allProducts.add(product);
        System.out.println("Product " + product.getName() + " added to inventory");
    }

    /**
     *
     * @param partId the part ID that is being used to query the list of parts
     * @return the part object that matches the part ID passed in.
     */
    public Part lookupPart(int partId) {
        return this.allParts.get(partId);
    }

    /**
     *
     * @param productId the product ID that is being used to query the list of products
     * @return the product object that matches the product ID passed in.
     */
    public Product lookupProduct(int productId) {
        return this.allProducts.get(productId);
    }

    /**
     *
     * @param partName the part name that is being used to query the list of parts
     * @return the part(s) that match the name of the part name passed in.
     */
    public ObservableList<Part> lookupPart(String partName) {
        return this.allParts;
    }

    /**
     *
     * @param productName the product name that is being used to query the list of products
     * @return the product(s) that match the name of the product name passed in.
     */
    public ObservableList<Product> lookupProduct(String productName) {
        return this.allProducts;
    }

    /**
     *
     * @param index the index of the part that is wanting to be updated
     * @param selectedPart the updated part that is replacing the part at the specified index.
     */
    public void updatePart(int index, Part selectedPart) {
        this.allParts.set(index, selectedPart);
    }

    /**
     *
     * @param index the index of the product that is wanting to be updated
     * @param newProduct the updated product that is replacing the product at the specified index.
     */
    public void updateProduct(int index, Product newProduct) {
        this.allProducts.set(index, newProduct);
    }

    /**
     *
     * @param selectedPart the part that is wanting to be removed
     * @return a boolean value that declares whether the part was successfully removed.
     */
    public boolean deletePart(Part selectedPart) {
        return this.allParts.remove(selectedPart);
    }

    /**
     *
     * @param selectedProduct the product that is wanting to be removed
     * @return a boolean value that declares whether the product was successfully removed.
     */
    public boolean deleteProduct(Product selectedProduct) {
        return this.allProducts.remove(selectedProduct);
    }

    /**
     *
     * @return the list of parts
     */
    public ObservableList<Part> getAllParts() {
        return this.allParts;
    }

    /**
     *
     * @return the list of products
     */
    public ObservableList<Product> getAllProducts() {
        return this.allProducts;
    }
}
