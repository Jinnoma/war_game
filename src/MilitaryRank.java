public enum MilitaryRank {
    PRIVATE(1),
    CORPORAL(2),
    CAPTAIN(3),
    MAJOR(4);
    private final int rank;

    MilitaryRank(int rank) {
        this.rank = rank;
    }

    public int getRankValue() {
        return this.rank;
    }

    public MilitaryRank getNextRank() {
        return this.ordinal() < MilitaryRank.values().length - 1
                ? MilitaryRank.values()[this.ordinal() + 1]
                : null;
    }
}

