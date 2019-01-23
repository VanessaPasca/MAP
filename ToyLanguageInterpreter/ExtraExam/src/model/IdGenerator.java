package model;

public class IdGenerator {
    private static int id=0;
    public static int generateID(){
        id+=1;
        return id;
    }

}
