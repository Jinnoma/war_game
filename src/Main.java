import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        General firstGeneral = new General("First",100);
        new Report(firstGeneral);
        General secondGeneral = new General("Second",100);
        new Report(secondGeneral);
        firstGeneral.buySoldier(MilitaryRank.MAJOR);
        firstGeneral.buySoldier(MilitaryRank.PRIVATE);
        secondGeneral.buySoldier(MilitaryRank.PRIVATE);
        ArrayList<Integer> soldiers1 = new ArrayList(Arrays.asList(0,1));
        firstGeneral.militaryTraining((soldiers1));
        firstGeneral.militaryTraining((soldiers1));
        firstGeneral.militaryTraining((soldiers1));
        firstGeneral.militaryTraining((soldiers1));
        firstGeneral.militaryTraining((soldiers1));

        System.out.println(secondGeneral.getCoins());
        firstGeneral.attack(secondGeneral);



        firstGeneral.save();
        General general1 = new General("war.txt");
        System.out.println(general1.getCoins());

    }
}



