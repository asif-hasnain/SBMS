package mapper;

import mapper.DBMapper.Stock;

public class IEXData {
    private Stock quote;

    public Stock getQuote() {
        return quote;
    }

    public void setQuote(Stock quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "IEXData{" +
                "quote=" + quote +
                '}';
    }
}
