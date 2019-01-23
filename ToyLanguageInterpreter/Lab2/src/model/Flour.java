package model;

public class Flour implements MyItem{
    int price;
    public Flour(int price) {
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
        return "Flour{" +
                "price=" + price +
                '}';
    }
}
