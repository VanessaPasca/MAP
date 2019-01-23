package model;

public class Sugar implements MyItem{
    int price;

    public Sugar(int price) {
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
        return "Sugar{" +
                "price=" + price +
                '}';
    }
}
