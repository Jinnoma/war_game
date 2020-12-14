import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SoldierTests {

    private Soldier soldier;

    @Before
    public void setup() {
        soldier = new Soldier(MilitaryRank.CAPTAIN);
    }

    @Test
    public void promote(){
        soldier.setExperience(15);
        MilitaryRank result = soldier.getMilitaryRank();
        MilitaryRank expected = MilitaryRank.MAJOR;
        assertEquals(expected, result);
    }
}
