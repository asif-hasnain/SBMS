package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class StockData {
    @Column(name="symbol")
    String symbol;
    @Column(name="latestPrice")
    double latestPrice;
    @Column(name="companyName")
    private String companyName;
    @Column(name = "subscribed")
    private boolean subscribed;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "symbol='" + symbol + '\'' +
                ", latestPrice='" + latestPrice + '\'' +
                ", companyName='" + companyName + '\'' +
                ", subscribed=" + subscribed +
                '}';
    }
}
