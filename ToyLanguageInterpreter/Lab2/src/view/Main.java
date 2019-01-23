package view;
import model.*;
import controller.*;
import repository.*;

public class Main {
    public static void main(String[] args) throws RepositoryException {
        MyItem f = new Flour(350);
        MyItem f2 = new Flour(145);
        MyItem s = new Sugar(445);
        MyItem s1 = new Sugar(120);
        MyItem salt = new Salt(580);

        Controller cont = new Controller();
        cont.addItem(f);
        cont.addItem(f2);
        cont.addItem(s);
        cont.addItem(s1);
        cont.addItem(salt);
        //cont.deleteItem(2);

        cont.showItemsWithGraterPrice(200);

    }
}
