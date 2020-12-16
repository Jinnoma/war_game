public abstract class Secretary {
    protected General general;
    protected Soldier soldier;

    public abstract void updateSoldiers();

    public abstract void updateMilitaryTraining(int cost, int soldierIndexes);

    public abstract void updateAttack(General general, int coinsWinByAttacking, int coinsLostByDefender);

    public abstract void updateRank(MilitaryRank oldRank, MilitaryRank newRank);
}
