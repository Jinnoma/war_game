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

    public void load() throws IOException, ClassNotFoundException {
        try {

            //Read from the stored file
            FileInputStream fileInputStream = new FileInputStream(new File(
                    "war.txt"));
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            General general = (General) input.readObject();
            System.out.println(general.getCoins());
            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}



