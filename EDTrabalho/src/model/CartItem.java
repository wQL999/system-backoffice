package model;

public class CartItem {
    private Produto product;
    private int quantity;

    public CartItem(Produto product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Produto getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getValor() * quantity;
    }

    @Override
    public String toString() {
        return product.getNome() + " x " + quantity + " - $" + getTotalPrice();
    }
}