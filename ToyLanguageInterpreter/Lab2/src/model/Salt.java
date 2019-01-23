package model;

public class Salt implements MyItem{
    int price;

    public Salt(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Salt{" +
                "price=" + price +
                '}';
    }
}
