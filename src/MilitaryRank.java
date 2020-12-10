public enum MilitaryRank {
    PRIVATE(1),
    CORPORAL(2),
    CAPTAIN(3),
    MAJOR(4);
    private final int rank;

    MilitaryRank(int rank) {
        this.rank = rank;
    }

    public int getRankValue(){
        return this.rank;
    }
}
