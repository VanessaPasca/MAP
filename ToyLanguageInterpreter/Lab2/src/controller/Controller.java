package controller;
import repository.Repository;
import model.*;
import repository.RepositoryException;

public class Controller {
    Repository repo;
//    public Controller(Repository r) {
//        this.repo = r;
//    }
    public Controller(){
        this(7);
    }

    private Controller(int size) {
        repo=new Repository(size);
    }

    public void addItem(MyItem item) throws RepositoryException {
        this.repo.add(item);
    }

    public void showItemsWithGraterPrice(int price) {
        MyItem allItems[] = repo.getAll();
        for (MyItem item: allItems){
            if (item.getPrice() > price ){
                System.out.println(item.toString());
            }
        }
    }

    public void deleteItem(int position) throws RepositoryException{
        repo.delete(position);
    }
}
