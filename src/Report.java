public class Report implements Secretary {
    private String report;

    @Override
    public void update(Object report){
        this.setCoins((String) report);
    }
}
