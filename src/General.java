import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

public class General {
    private ArrayList<Soldier> soldiers = new ArrayList<>();
    private int coins;
    private Soldier soldier;
    private PropertyChangeSupport support;


    public General(int coins) {
        this.coins = coins;
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        if (coins < 0) {
            throw new IllegalArgumentException("Not enough money");
        }
        support.firePropertyChange("cois", this.coins, coins);
        this.coins = coins;

    }

    public void buySoldier(MilitaryRank militaryRank) {
        soldier = new Soldier(militaryRank);
        soldiers.add(soldier);
        setCoins(this.coins - 10 * militaryRank.getRankValue());
    }

    public ArrayList<MilitaryRank> getSoldiersRank() {
        ArrayList<MilitaryRank> soldiersRank = new ArrayList<>();
        for (Soldier s : soldiers) {
            soldiersRank.add(s.getMilitaryRank());
        }
        return soldiersRank;
    }

    public void militaryTraining(ArrayList<Integer> soldierIndexes) {
        int cost = 0;
        for (Integer i : soldierIndexes) {
            if (i > soldiers.size() - 1) {
                throw new IllegalArgumentException("Invalid soldier index");
            } else {
                soldiers.get(i).setExperience(soldiers.get(i).getExperience() + 1);
            }
            cost += soldiers.get(i).getMilitaryRank().getRankValue();
        }
        setCoins(getCoins() - cost);
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public int getSoldiersStrength() {
        int soldiersStrength = 0;
        for (Soldier s : soldiers) {
            soldiersStrength += s.getStrength();
        }
        return soldiersStrength;
    }

    public void attack(General general) {
        Random rand = new Random();
        ArrayList<Soldier> toRemove = new ArrayList<>();

        int rand_int1 = rand.nextInt(getSoldiers().size());
        int rand_int2 = rand.nextInt(general.getSoldiers().size());
        if (getSoldiersStrength() > general.getSoldiersStrength()) {
            for (Soldier s : general.getSoldiers()) {
                s.expDecrement();
                if (s.getExperience() < 1) {
                    toRemove.add(s);

                }
            }
            soldiers.removeAll(toRemove);
        } else if (getSoldiersStrength() < general.getSoldiersStrength()) {
            for (Soldier s : getSoldiers()) {
                s.expDecrement();
                if (s.getExperience() < 1) {
                    toRemove.add(s);
                }
            }
            soldiers.removeAll(toRemove);
        } else {
            getSoldiers().remove(rand_int1);
            general.getSoldiers().remove(rand_int2);
        }
    }

}

