package wethinkcode.Model;

public class MarketModel
{
    private int Id;
    private int MarketId;
    private String Market;
    private String MarketType;
    private String Intrument;


    public int getId() {
        return Id;
    }

    public int getMarketId() {
        return MarketId;
    }

    public void setMarketId(int marketId) {
        MarketId = marketId;
    }

    public String getMarket() {
        return Market;
    }

    public void setMarket(String market) {
        Market = market;
    }

    public String getMarketType() {
        return MarketType;
    }

    public void setMarketType(String marketType) {
        MarketType = marketType;
    }

    public String getIntrument() {
        return Intrument;
    }

    public void setIntrument(String intrument) {
        Intrument = intrument;
    }
}
