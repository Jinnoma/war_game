import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Array;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class GeneralTests {

    private General firstGeneral;
    private General secondGeneral;

    @Before
    public void setup() {
        firstGeneral = new General("First",100);
        secondGeneral = new General("Second",100);

        firstGeneral.buySoldier(MilitaryRank.PRIVATE);
        firstGeneral.buySoldier(MilitaryRank.MAJOR);
        firstGeneral.buySoldier(MilitaryRank.PRIVATE);
        firstGeneral.buySoldier(MilitaryRank.CORPORAL);
        secondGeneral.buySoldier(MilitaryRank.PRIVATE);
        secondGeneral.buySoldier(MilitaryRank.CAPTAIN);
        secondGeneral.buySoldier(MilitaryRank.CORPORAL);
        secondGeneral.buySoldier(MilitaryRank.CORPORAL);

    }

    @Test
    public void coinsAmountAfterBuySoldiers(){
        int expected = 20;
        int result = firstGeneral.getCoins();
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEnoughMoney(){
        firstGeneral.buySoldier(MilitaryRank.MAJOR);
    }

    @Test
    public void militaryTraining(){
        ArrayList<Integer> soldiers = new ArrayList<>(Arrays.asList(0,3));
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(17, 2));
        ArrayList<Integer> result = new ArrayList<>();
        firstGeneral.militaryTraining(soldiers);
        result.add(firstGeneral.getCoins());
        result.add(firstGeneral.getSoldiers().get(0).getExperience());
        Assert.assertEquals(expected, result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void soldierNotOnTheTrainingList(){
        ArrayList<Integer> soldiers = new ArrayList<>(Arrays.asList(0,4));
        firstGeneral.militaryTraining(soldiers);
    }

    @Test
    public void draw(){
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3,3));
        ArrayList<Integer> result = new ArrayList();
        firstGeneral.attack(secondGeneral);
        result.add(firstGeneral.getSoldiers().size());
        result.add(secondGeneral.getSoldiers().size());
        Assert.assertEquals(expected, result);
    }

}
