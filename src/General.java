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
    public ArrayList<Soldier> getSoldiers(){
        return soldiers;
    }
    public Soldier getSoldier(){
        return soldier;
    }

    public double getSoldiersStrength(){
        double soldiersStrength = 0;
        for(Soldier s : soldiers) {
            soldiersStrength += s.getStrength();
        }
        return soldiersStrength;
    }

    public void attack(General general){
        if (getSoldiersStrength() > general.getSoldiersStrength()){
            for(Soldier s : general.getSoldiers()){
                s.setStrength(s.getStrength()-1);
            }
        }
        }
    };



}
