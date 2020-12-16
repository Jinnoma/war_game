import java.io.Serializable;
import java.util.ArrayList;

public class Soldier implements Serializable {
    private MilitaryRank militaryRank;
    private ArrayList<Secretary> secretary = new ArrayList<>();
    private int experience;
    private int strength;

    public Soldier(MilitaryRank militaryRank) {
        setMilitaryRank(militaryRank);
        setExperience(1);
        setStrength();
    }

    public MilitaryRank getMilitaryRank() {
        return militaryRank;
    }

    public void setExperience(int experience){
        this.experience = experience;
        if (this.experience >= 5 * this.militaryRank.getRankValue() && militaryRank != MilitaryRank.MAJOR) {
            promote();
        }
    }

    public int getExperience() {
        return experience;
    }

    public int getStrength() {
        return strength;
    }

    public void setMilitaryRank(MilitaryRank militaryRank) {
        if (militaryRank != MilitaryRank.CAPTAIN && militaryRank != MilitaryRank.PRIVATE
                && militaryRank != MilitaryRank.CORPORAL && militaryRank != MilitaryRank.MAJOR) {
            throw new IllegalArgumentException("Incorrect military rank value");
        }

        this.militaryRank = militaryRank;
    }

    public void setStrength() {
        this.strength = this.experience * this.militaryRank.getRankValue();
    }

    public void promote(){
        MilitaryRank oldRank = getMilitaryRank();
        MilitaryRank newRank = getMilitaryRank().getNextRank();
        setMilitaryRank(getMilitaryRank().getNextRank());
        for (Secretary s : secretary) {
            s.updateRank(oldRank, newRank);
        }
        }

    public void expDecrement(){
        this.experience = experience - 1;
    }

    public void attach(Secretary s) {
        secretary.add(s);
    }
}
