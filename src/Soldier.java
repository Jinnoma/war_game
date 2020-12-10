public class Soldier {
    private MilitaryRank militaryRank;
    private double experience;
    private double strength;

    public Soldier(MilitaryRank militaryRank, double strength) {
        setMilitaryRank(militaryRank);
        setStrength(strength);
        this.experience = 1;
    }

    public MilitaryRank getMilitaryRank() {
        return militaryRank;
    }

    public double getExperience() {
        return experience;
    }

    public double getStrength() {
        return strength;
    }

    public void setMilitaryRank(MilitaryRank militaryRank) {
        if (militaryRank != MilitaryRank.CAPTAIN && militaryRank != MilitaryRank.PRIVATE
                && militaryRank != MilitaryRank.CORPORAL && militaryRank != MilitaryRank.MAJOR) {
            throw new IllegalArgumentException("Incorrect military rank value");
        }

        this.militaryRank = militaryRank;
    }

    public void setStrength(double strength) {
        this.strength = this.experience * this.militaryRank.getRankValue();
    }

    public void promote(){
        if (this.experience == 5 * this.militaryRank.getRankValue()) {
            this.militaryRank = militaryRank.getRankValue() + 1;
        }
    }

}
