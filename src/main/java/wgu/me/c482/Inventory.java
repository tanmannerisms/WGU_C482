package wgu.me.c482;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class Inventory implements ObservableList<Part>{
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part part) {
        this.allParts.add(part);
    }

    public void addProduct(Product product) {
        this.allProducts.add(product);
    }

    public Part lookupPart(int partId) {
        return this.allParts.get(partId);
    }

    public Product lookupProduct(int productId) {
        return this.allProducts.get(productId);
    }

    public ObservableList<Part> lookupPart(String partName) {
        return this.allParts;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return this.allProducts;
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

    @Override
    public void addListener(ListChangeListener<? super Part> listChangeListener) {

    }

    @Override
    public void removeListener(ListChangeListener<? super Part> listChangeListener) {

    }

    @Override
    public boolean addAll(Part... parts) {
        return false;
    }

    @Override
    public boolean setAll(Part... parts) {
        return false;
    }

    @Override
    public boolean setAll(Collection<? extends Part> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Part... parts) {
        return false;
    }

    @Override
    public boolean retainAll(Part... parts) {
        return false;
    }

    @Override
    public void remove(int i, int i1) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Part> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Part part) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Part> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Part> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Part get(int index) {
        return null;
    }

    @Override
    public Part set(int index, Part element) {
        return null;
    }

    @Override
    public void add(int index, Part element) {

    }

    @Override
    public Part remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Part> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Part> listIterator(int index) {
        return null;
    }

    @Override
    public List<Part> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
