package org.jeonfeel.http_practice;

import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("market")
    private String market;
    @SerializedName("trade_date")
    private String trade_date;
    @SerializedName("trade_price")
    private Double trade_price;
    @SerializedName("highest_52_week_price")
    private Double highest_52_week_price;

    public Model(String market, String trade_date, Double trade_price, Double highest_52_week_price) {
        this.market = market;
        this.trade_date = trade_date;
        this.trade_price = trade_price;
        this.highest_52_week_price = highest_52_week_price;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(String trade_date) {
        this.trade_date = trade_date;
    }

    public Double getTrade_price() {
        return trade_price;
    }

    public void setTrade_price(Double trade_price) {
        this.trade_price = trade_price;
    }

    public Double getHighest_52_week_price() {
        return highest_52_week_price;
    }

    public void setHighest_52_week_price(Double highest_52_week_price) {
        this.highest_52_week_price = highest_52_week_price;
    }

    public String makeString(){
        return this.market + "\n" + this.trade_date + "\n" +this.trade_price + "\n" +this.highest_52_week_price;
    }

}
