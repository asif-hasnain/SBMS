package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "stock")
public class Stock implements Serializable {
    @Column(name="symbol")
    String symbol;
    @Column(name="companyName")
    String companyName;
    @Column(name="description")
    String description;
    @Column(name="open")
    double open;
    @Column(name="close")
    double close;
    @Column(name="high")
    double high;
    @Column(name="highTime")
    long highTime;
    @Column(name="low")
    double low;
    @Column(name="lowTime")
    long lowTime;
    @Column(name="latestPrice")
    double latestPrice;
    @Column(name="latestUpdate")
    long latestUpdate;
    @Column(name="latestVolume")
    long latestVolume;
    @Column(name="volume")
    long volume;
    @Column(name="week52High")
    double week52High;
    @Column(name="week52Low")
    double week52Low;
    @Column(name="updated_date")
    private Date updated_date;
    @Column(name="created_date")
    private Date created_date;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public long getHighTime() {
        return highTime;
    }

    public void setHighTime(long highTime) {
        this.highTime = highTime;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public long getLowTime() {
        return lowTime;
    }

    public void setLowTime(long lowTime) {
        this.lowTime = lowTime;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public long getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public long getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(long latestVolume) {
        this.latestVolume = latestVolume;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getWeek52High() {
        return week52High;
    }

    public void setWeek52High(double week52High) {
        this.week52High = week52High;
    }

    public double getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(double week52Low) {
        this.week52Low = week52Low;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", description='" + description + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", highTime=" + highTime +
                ", low=" + low +
                ", lowTime=" + lowTime +
                ", latestPrice=" + latestPrice +
                ", latestUpdate=" + latestUpdate +
                ", latestVolume=" + latestVolume +
                ", volume=" + volume +
                ", week52High=" + week52High +
                ", week52Low=" + week52Low +
                ", updated_date=" + updated_date +
                ", created_date=" + created_date +
                '}';
    }
}
