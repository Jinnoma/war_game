import java.io.Serializable;
import java.util.ArrayList;

public class Report extends Secretary implements Serializable {
    public Report(General general) {
        this.general = general;
        this.general.attach(this);
    }
    public Report(Soldier soldier) {
        this.soldier = soldier;
        this.soldier.attach(this);
    }


    @Override
    public void updateSoldiers() {
        System.out.println("General " + general.getName() + " buy soldier with rank " +
                general.getSoldiers().get(general.getSoldiers().size() - 1).getMilitaryRank() + " for "
                + (10 * general.getSoldiers().get(general.getSoldiers().size() - 1).getMilitaryRank().getRankValue())
                + " coins, " + general.getCoins() + " coins left");
    }

    //    @Override
//    public void updateCoins(){
//        System.out.println("General " + general.getName() + " have coins " + general.getCoins());
//    }
    @Override
    public void updateMilitaryTraining(int cost, int soldierIndexes) {
        System.out.println("General " + general.getName() + " gets military training with " + soldierIndexes + " soldiers for "
                + cost + " coins," + general.getCoins() + " coins left ");
    }

    @Override
    public void updateAttack(General general2, int coinsWonByAttacker, int coinsLostByDefender) {
        System.out.println("General " + general.getName() + " with Soldiers strength " + general.getSoldiersStrength() +
                " attack general " + general2.getName() + " with Soldiers strength " + general2.getSoldiersStrength());
        if (general.getSoldiersStrength() > general2.getSoldiersStrength()) {
            System.out.println("General " + general.getName() + " wins attack and get 1 experience for " +
                    "every soldiers. Also wins " + coinsWonByAttacker + " coins. Now he have " +
                    general.getCoins() + " coins");
            System.out.println("General " + general2.getName() + " loss attack and loss 1 experience for " +
                    "every soldiers. Also loss " + coinsWonByAttacker + " coins. Now he have " +
                    general2.getCoins() + " coins");
        } else if (general2.getSoldiersStrength() > general.getSoldiersStrength()) {
            System.out.println("General " + general2.getName() + " wins attack and get 1 experience for " +
                    "every soldiers. Also wins " + coinsLostByDefender + " coins. Now he have " +
                    general.getCoins() + " coins");
            System.out.println("General " + general.getName() + " loss attack and loss 1 experience for " +
                    "every soldiers. Also loss " + coinsLostByDefender + " coins. Now he have " +
                    general.getCoins() + " coins");
        } else if (general2.getSoldiersStrength() == general.getSoldiersStrength()) {
            System.out.println("General " + general2.getName() + " drew with General" + general.getName() +
                    ". Both lose one soldier");
        }
    }

    @Override
    public void updateRank(MilitaryRank oldRank, MilitaryRank newRank) {
        System.out.println("Soldier promote from rank" + oldRank + " to new rank" + newRank);

    }
}
