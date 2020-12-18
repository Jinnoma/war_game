import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        General firstGeneral = new General("First",100);
        new Report(firstGeneral);
        General secondGeneral = new General("Second",100);
        new Report(secondGeneral);
//        firstGeneral.buySoldier(MilitaryRank.MAJOR);
//        firstGeneral.buySoldier(MilitaryRank.PRIVATE);
//        secondGeneral.buySoldier(MilitaryRank.PRIVATE);
//        ArrayList<Integer> soldiers1 = new ArrayList(Arrays.asList(0,1));
//        firstGeneral.militaryTraining((soldiers1));
//        firstGeneral.militaryTraining((soldiers1));
//        firstGeneral.militaryTraining((soldiers1));
//        firstGeneral.militaryTraining((soldiers1));
//        firstGeneral.militaryTraining((soldiers1));

        System.out.println(secondGeneral.getCoins());
//        firstGeneral.attack(secondGeneral);


//        FileOutputStream fileOutputStream
//                = new FileOutputStream("war.txt");
//        ObjectOutputStream objectOutputStream
//                = new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(firstGeneral);
//        objectOutputStream.flush();
//        objectOutputStream.close();
//        firstGeneral.save();
        General general = new General();
        general.load();
        general.getCoins();


//        FileInputStream fileInputStream
//                = new FileInputStream("war.txt");
//        ObjectInputStream objectInputStream
//                = new ObjectInputStream(fileInputStream);
//        General p2 = (General) objectInputStream.readObject();
//        objectInputStream.close();
//        System.out.println(p2.getCoins());
//        System.out.println(secondGeneral.getCoins());
    }


}



