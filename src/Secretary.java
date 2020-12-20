public abstract class Secretary {
    protected General general;

    public abstract void updateSoldiers();

    public abstract void updateMilitaryTraining(int cost, int soldierIndexes);

    public abstract void updateAttack(General general, int coinsWinByAttacking, int coinsLostByDefender);
}
