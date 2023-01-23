package wgu.me.c482;

public class ProductController extends Controller{
    public ProductController() {
        super();
    }
    ProductController(String file, String title, Inventory inventory) {
        super(file, title, inventory);
    }
    @Override
    void cancel() {

    }
}
