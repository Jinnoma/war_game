public class Main {

    public static void main(String args[]) {

        General firstGeneral = new General(100);
        General secondGeneral = new General(100);
        firstGeneral.buySoldier(MilitaryRank.MAJOR);
        firstGeneral.buySoldier(MilitaryRank.MAJOR);
        firstGeneral.buySoldier(MilitaryRank.MAJOR);
        System.out.println(firstGeneral.getCoins());
    }


}



