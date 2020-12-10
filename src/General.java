import java.util.ArrayList;

public class General {
    private ArrayList<Soldier> soldiers;
    private int coins;
    private Soldier soldier;


    public General(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void buySoldier(MilitaryRank militaryRank, double strength){
        soldiers.add(new Soldier(militaryRank, strength));
        this.coins = coins - 10 * militaryRank.getRankValue();

    }



}
