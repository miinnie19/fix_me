package wethinkcode.FixMessages;

public class SellMessages {
    private int BrokerId;
    private int MarketId;
    private int Price;
    private int Quantity;
    private String Market;
    private String Instrument;


    public SellMessages(int brokerId, int marketId, String market, String instrument, int quantity, int price){

    }

    public int getBrokerId() {
        return BrokerId;
    }

    public int getMarketId() {
        return MarketId;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getMarket() {
        return Market;
    }

    public void setMarket(String market) {
        Market = market;
    }

    public String getInstrument() {
        return Instrument;
    }

    public void setInstrument(String instrument) {
        Instrument = instrument;
    }
}
