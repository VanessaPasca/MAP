package repository;
import model.*;

public class Repository {
    MyItem items[];
    int position;
    public Repository(int size){
        items = new MyItem[size];
    }

    public void add(MyItem item) throws RepositoryException{
        if (this.position >= items.length) {
            throw new RepositoryException("Repository full!");
        }

        items[position] = item;
        position++;
    }

    public void delete(int pos) throws RepositoryException {
        if (position == 0){
            throw new RepositoryException("Repository empty!");
        }

        if (pos>=position) {
            throw new RepositoryException("Position not in the array");
        }

        if (pos==position-1){
            items[pos]=null;
            position--;

        }else{
            for (int i=pos;i<position-2;i++){
                items[i]=items[i+1];
                items[position-1]=null;
                position--;
            }
        }



    }

    public MyItem[] getAll() {
        MyItem actualItems[]=new MyItem[position];
        for (int i=0;i<position;i++){
            actualItems[i]=items[i];
        }
        return actualItems;
    }
}
