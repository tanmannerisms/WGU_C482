package wgu.me.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    static int partId = 1, productId = 1;

    /**
     *
     * @param part the part that gets added into the allParts ObservableList
     */
    public static void addPart(Part part) {
        allParts.add(part);
        partId++;
        System.out.println("Part " + part.getName() + " added to inventory");
    }

    /**
     *
     * @param product the product that gets added into the allProducts ObservableList
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
        productId++;
        System.out.println("Product " + product.getName() + " added to inventory");
    }

    /**
     *
     * @param partId the part ID that is being used to query the list of parts
     * @return the part object that matches the part ID passed in.
     */
    public static Part lookupPart(int partId) {
        return allParts.get(partId);
    }

    /**
     *
     * @param productId the product ID that is being used to query the list of products
     * @return the product object that matches the product ID passed in.
     */
    public static Product lookupProduct(int productId) {
        return allProducts.get(productId);
    }

    /**
     *
     * @param partName the part name that is being used to query the list of parts
     * @return the part(s) that match the name of the part name passed in.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        return allParts;
    }

    /**
     *
     * @param productName the product name that is being used to query the list of products
     * @return the product(s) that match the name of the product name passed in.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        return allProducts;
    }

    /**
     *
     * @param index the index of the part that is wanting to be updated
     * @param selectedPart the updated part that is replacing the part at the specified index.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     *
     * @param index the index of the product that is wanting to be updated
     * @param newProduct the updated product that is replacing the product at the specified index.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     *
     * @param selectedPart the part that is wanting to be removed
     * @return a boolean value that declares whether the part was successfully removed.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     *
     * @param selectedProduct the product that is wanting to be removed
     * @return a boolean value that declares whether the product was successfully removed.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     *
     * @return the list of parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @return the list of products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
