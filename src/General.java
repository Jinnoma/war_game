import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class General implements Serializable {
    private ArrayList<Soldier> soldiers = new ArrayList<>();
    private ArrayList<Secretary> secretary = new ArrayList<>();
    private int coins;
    private Soldier soldier;
    private String name;

    public General() {
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

    public void attack(General general) {
        Random rand = new Random();
        ArrayList<Soldier> toRemove = new ArrayList<>();
        int attackerCoinsWon = general.getCoins() * 10 / 100;
        int defenderCoinsWon = getCoins() * 10 / 100;
        int rand_int1 = rand.nextInt(getSoldiers().size());
        int rand_int2 = rand.nextInt(general.getSoldiers().size());
        if (getSoldiersStrength() > general.getSoldiersStrength()) {
            setCoins(getCoins() + attackerCoinsWon);
            general.setCoins(general.getCoins() - attackerCoinsWon);
            for (Soldier s : general.getSoldiers()) {
                s.expDecrement();
                if (s.getExperience() < 1) {
                    toRemove.add(s);
                }
            }
            soldiers.removeAll(toRemove);
        } else if (getSoldiersStrength() < general.getSoldiersStrength()) {
            general.setCoins(general.getCoins() + defenderCoinsWon);
            setCoins(getCoins() - defenderCoinsWon);
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
        for (Secretary s : secretary) {
            s.updateAttack(general,attackerCoinsWon, defenderCoinsWon);
        }
    }

    public void save() throws IOException {
        ObjectOutputStream objectOutputStream;
        try (FileOutputStream fileOutputStream = new FileOutputStream("war.txt")) {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        }
        objectOutputStream.writeObject();
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public void load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream
                = new FileInputStream("war.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        general = (General) objectInputStream.readObject();
        objectInputStream.close();
    }
}



