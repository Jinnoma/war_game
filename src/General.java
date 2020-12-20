import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class General implements Serializable {
    private ArrayList<Soldier> soldiers = new ArrayList<>();
    private ArrayList<Secretary> secretary = new ArrayList<>();
    private int coins;
    private Soldier soldier;
    private String name;
    private String filename;

    public General(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream input = new ObjectInputStream(fileInputStream);
        General general = (General) input.readObject();
        this.soldiers = general.soldiers;
        this.coins = general.coins;
        this.name = general.name;
    }

    public General(String name, int coins) {
        this.coins = coins;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        if (coins < 0) {
            throw new IllegalArgumentException("Not enough money");
        }
        this.coins = coins;
    }

    public void attach(Secretary s) {
        secretary.add(s);
    }

    public void buySoldier(MilitaryRank militaryRank) {
        soldier = new Soldier(militaryRank);
        soldiers.add(soldier);
        setCoins(this.coins - 10 * militaryRank.getRankValue());
        for (Secretary s : secretary) {
            s.updateSoldiers();
        }
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
        for (Secretary s : secretary) {
            s.updateMilitaryTraining(cost, soldierIndexes.size());
        }
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

    public void killRandom() {
        Random rand = new Random();
        int rand_sold = rand.nextInt(soldiers.size());
        getSoldiers().remove(rand_sold);
    }

    public void expDecrement() {
        for (Soldier s : getSoldiers()) {
            s.expDecrement();
        }
    }

    public void remove() {
        ArrayList<Soldier> toRemove = new ArrayList<>();
        for (Soldier s : getSoldiers()) {
            if (s.getExperience() < 1) {
                toRemove.add(s);
            }
        }
        soldiers.removeAll(toRemove);
    }


    public void attack(General general) {
        int attackerCoinsWon = general.getCoins() * 10 / 100;
        int defenderCoinsWon = getCoins() * 10 / 100;
        if (getSoldiersStrength() > general.getSoldiersStrength()) {
            setCoins(getCoins() + attackerCoinsWon);
            general.setCoins(general.getCoins() - attackerCoinsWon);
            general.expDecrement();
            general.remove();
        } else if (getSoldiersStrength() < general.getSoldiersStrength()) {
            general.setCoins(general.getCoins() + defenderCoinsWon);
            setCoins(getCoins() - defenderCoinsWon);
            this.expDecrement();
            this.remove();
        } else {
            this.killRandom();
            general.killRandom();
        }
        for (Secretary s : secretary) {
            s.updateAttack(general, attackerCoinsWon, defenderCoinsWon);
        }
    }

    public void save() throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("war.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}



