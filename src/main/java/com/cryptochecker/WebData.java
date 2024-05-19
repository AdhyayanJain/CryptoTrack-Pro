package com.cryptochecker;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JOptionPane;
import com.google.gson.Gson; // fetching json data
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class WebData {
    public ArrayList<Coin> coin;
    public ArrayList<ArrayList<Coin>> portfolio;
    public ArrayList<String> portfolio_names = new ArrayList<String>();
    public int portfolio_nr = 0;
    public Global_Data global_data;

    public WebData() throws Exception {
        if (coin == null && global_data == null) {
            this.deserialize();
        }
    }

    public void fetch() throws Exception {
        try {
            // Sample Data
            this.createSampleData();

            // SERIALIZATION
            FileOutputStream file = new FileOutputStream(Main.dataSerLocation);
            BufferedOutputStream buffer = new BufferedOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(buffer);
            out.writeObject(global_data);
            out.writeObject(coin);
            out.close();
            Debug.log("Serialized Data To " + Main.dataSerLocation);
        } catch(IOException i) {
            Debug.log("EXCEPTION: WebData.java - fetch()");
            i.printStackTrace();
        }
    }

    private void createSampleData() {
        // Sample Data Creation
        coin = new ArrayList<>();

        Coin btc = new Coin();
        btc.rank = 1;
        btc.name = "Bitcoin";
        btc.symbol = "BTC";
        btc.price = 64003.12;
        btc.percent_change_1h = 0.2;
        btc.percent_change_24h = 0.7;
        btc.percent_change_7d = 1.4;
        btc._24h_volume = 28015110470L;
        btc.market_cap = 1260253198038L;
        coin.add(btc);

        Coin eth = new Coin();
        eth.rank = 2;
        eth.name = "Ethereum";
        eth.symbol = "ETH";
        eth.price = 3130.01;
        eth.percent_change_1h = 0.0;
        eth.percent_change_24h = 0.7;
        eth.percent_change_7d = 0.9;
        eth._24h_volume = 12263036809L;
        eth.market_cap = 381984055068L;
        coin.add(eth);

        Coin usdt = new Coin();
        usdt.rank = 3;
        usdt.name = "Tether";
        usdt.symbol = "USDT";
        usdt.price = 0.9995;
        usdt.percent_change_1h = 0.1;
        usdt.percent_change_24h = 0.0;
        usdt.percent_change_7d = 0.2;
        usdt._24h_volume = 43536276747L;
        usdt.market_cap = 109657237902L;
        coin.add(usdt);

        Coin bnb = new Coin();
        bnb.rank = 4;
        bnb.name = "Binance Coin";
        bnb.symbol = "BNB";
        bnb.price = 600.97;
        bnb.percent_change_1h = 0.0;
        bnb.percent_change_24h = 0.7;
        bnb.percent_change_7d = 7.0;
        bnb._24h_volume = 1119788830L;
        bnb.market_cap = 92272540228L;
        coin.add(bnb);

        Coin sol = new Coin();
        sol.rank = 5;
        sol.name = "Solana";
        sol.symbol = "SOL";
        sol.price = 136.33;
        sol.percent_change_1h = 0.5;
        sol.percent_change_24h = 5.2;
        sol.percent_change_7d = 0.6;
        sol._24h_volume = 3360852457L;
        sol.market_cap = 64279924286L;
        coin.add(sol);

        Coin usdc = new Coin();
        usdc.rank = 6;
        usdc.name = "USDC";
        usdc.symbol = "USDC";
        usdc.price = 1.00;
        usdc.percent_change_1h = 0.5;
        usdc.percent_change_24h = 0.0;
        usdc.percent_change_7d = 0.3;
        usdc._24h_volume = 7991423960L;
        usdc.market_cap = 33212372744L;
        coin.add(usdc);

        Coin steth = new Coin();
        steth.rank = 7;
        steth.name = "Lido Staked Ether";
        steth.symbol = "STETH";
        steth.price = 3120.47;
        steth.percent_change_1h = 0.1;
        steth.percent_change_24h = 0.8;
        steth.percent_change_7d = 1.0;
        steth._24h_volume = 77478264L;
        steth.market_cap = 29188973502L;
        coin.add(steth);

        Coin xrp = new Coin();
        xrp.rank = 8;
        xrp.name = "XRP";
        xrp.symbol = "XRP";
        xrp.price = 0.5196;
        xrp.percent_change_1h = 0.4;
        xrp.percent_change_24h = 1.5;
        xrp.percent_change_7d = 3.8;
        xrp._24h_volume = 1109597937L;
        xrp.market_cap = 28652125574L;
        coin.add(xrp);

        Coin doge = new Coin();
        doge.rank = 9;
        doge.name = "Dogecoin";
        doge.symbol = "DOGE";
        doge.price = 0.1466;
        doge.percent_change_1h = 0.1;
        doge.percent_change_24h = 2.8;
        doge.percent_change_7d = 2.5;
        doge._24h_volume = 986966952L;
        doge.market_cap = 21441558230L;
        coin.add(doge);

        Coin ton = new Coin();
        ton.rank = 10;
        ton.name = "Toncoin";
        ton.symbol = "TON";
        ton.price = 5.35;
        ton.percent_change_1h = 0.7;
        ton.percent_change_24h = 0.3;
        ton.percent_change_7d = 17.0;
        ton._24h_volume = 215965468L;
        ton.market_cap = 18595212710L;
        coin.add(ton);

        Coin ada = new Coin();
        ada.rank = 11;
        ada.name = "Cardano";
        ada.symbol = "ADA";
        ada.price = 0.46;
        ada.percent_change_1h = 0.2;
        ada.percent_change_24h = 2.8;
        ada.percent_change_7d = 1.2;
        ada._24h_volume = 343719557L;
        ada.market_cap = 16523639541L;
        coin.add(ada);

        Coin shib = new Coin();
        shib.rank = 12;
        shib.name = "Shiba Inu";
        shib.symbol = "SHIB";
        shib.price = 0.00002493;
        shib.percent_change_1h = 0.3;
        shib.percent_change_24h = 3.3;
        shib.percent_change_7d = 10.1;
        shib._24h_volume = 741326590L;
        shib.market_cap = 14901706117L;
        coin.add(shib);

        Coin avax = new Coin();
        avax.rank = 13;
        avax.name = "Avalanche";
        avax.symbol = "AVAX";
        avax.price = 34.14;
        avax.percent_change_1h = 0.5;
        avax.percent_change_24h = 4.0;
        avax.percent_change_7d = 0.1;
        avax._24h_volume = 406867601L;
        avax.market_cap = 13357048375L;
        coin.add(avax);

        Coin trx = new Coin();
        trx.rank = 14;
        trx.name = "TRON";
        trx.symbol = "TRX";
        trx.price = 0.119;
        trx.percent_change_1h = 0.7;
        trx.percent_change_24h = 1.1;
        trx.percent_change_7d = 7.9;
        trx._24h_volume = 411401722L;
        trx.market_cap = 10339173546L;
        coin.add(trx);

        Coin wbtc = new Coin();
        wbtc.rank = 15;
        wbtc.name = "Wrapped Bitcoin";
        wbtc.symbol = "WBTC";
        wbtc.price = 63091.92;
        wbtc.percent_change_1h = 0.1;
        wbtc.percent_change_24h = 1.9;
        wbtc.percent_change_7d = 1.5;
        wbtc._24h_volume = 201701023L;
        wbtc.market_cap = 9925825798L;
        coin.add(wbtc);

        Coin bch = new Coin();
        bch.rank = 16;
        bch.name = "Bitcoin Cash";
        bch.symbol = "BCH";
        bch.price = 477.18;
        bch.percent_change_1h = 0.4;
        bch.percent_change_24h = 0.1;
        bch.percent_change_7d = 0.4;
        bch._24h_volume = 281452198L;
        bch.market_cap = 9435011737L;
        coin.add(bch);

        Coin dot = new Coin();
        dot.rank = 17;
        dot.name = "Polkadot";
        dot.symbol = "DOT";
        dot.price = 6.72;
        dot.percent_change_1h = 0.0;
        dot.percent_change_24h = 1.8;
        dot.percent_change_7d = 0.7;
        dot._24h_volume = 181856450L;
        dot.market_cap = 9280559388L;
        coin.add(dot);

        Coin link = new Coin();
        link.rank = 18;
        link.name = "Chainlink";
        link.symbol = "LINK";
        link.price = 14.19;
        link.percent_change_1h = 0.3;
        link.percent_change_24h = 3.3;
        link.percent_change_7d = 5.2;
        link._24h_volume = 359301268L;
        link.market_cap = 8563659322L;
        coin.add(link);

        Coin near = new Coin();
        near.rank = 19;
        near.name = "NEAR Protocol";
        near.symbol = "NEAR";
        near.price = 7.22;
        near.percent_change_1h = 0.0;
        near.percent_change_24h = 2.7;
        near.percent_change_7d = 25.9;
        near._24h_volume = 832579720L;
        near.market_cap = 7562812173L;
        coin.add(near);

        Coin matic = new Coin();
        matic.rank = 20;
        matic.name = "Polygon";
        matic.symbol = "MATIC";
        matic.price = 0.6992;
        matic.percent_change_1h = 0.2;
        matic.percent_change_24h = 3.3;
        matic.percent_change_7d = 3.0;
        matic._24h_volume = 303447962L;
        matic.market_cap = 6583030216L;
        coin.add(matic);

// Continue adding the remaining cryptocurrencies in the same format


// Continue adding the remaining cryptocurrencies in the same format


// Similarly add the remaining cryptocurrencies


// Similarly add the remaining cryptocurrencies


// Similarly add other cryptocurrencies following the same pattern






        // Add more sample data for other coins as needed...

        // Global Data
        global_data = new Global_Data();
    }


    public static class RefreshCoins implements Runnable { // manuaully refresh with online API
        public RefreshCoins() {
            Thread t = new Thread(this, "Refresh Thread"); // create a separate thread that refreshes the coins
            t.start();
        }

        public void run() {
            try {
                long timerStart = System.nanoTime(); // TIMER START
                Main.gui.webData.fetch();
                long timerEnd = System.nanoTime(); // TIMER STOP
                Debug.log("TIMER - Main.gui.webData.fetch() took\n--seconds: "+(timerEnd - timerStart)/1000000000.0+"\n--milliseconds: "+((timerEnd-timerStart)/1000000.0)+"\n--nanoseconds: "+(timerEnd-timerStart));
    
                Main.gui.panelPortfolio.refreshPortfolio();
                Main.gui.panelPortfolio.serializePortfolio();
    
                Main.gui.panelCoin.reCreate();
                Main.gui.panelConverter.reCreate();
                Main.gui.panelPortfolio.reCreate();
            } catch(NoClassDefFoundError ex) {
                Debug.log("EXCEPTION: Missing Gson dependency!");
                JOptionPane.showMessageDialog(Main.frame, "Missing Gson dependency!\nDownload and use the default \"crypto-checker.jar\" instead of \"crypto-checker-no-dependencies.jar\"");
            } catch(Exception ex) {
                Debug.log("EXCEPTION: WebData.java - RefreshCoins().run()");
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked") // coin = (ArrayList<Coin> in.readObject()) supressing
    private void deserialize() throws Exception {
        if (!(new File(Main.dataSerLocation).canRead())) {
            Debug.log("ERROR: Couldn't find "+Main.dataSerLocation+".. fetching from API.");
            this.fetch();
            Debug.log("-- Fetched file");
            return;
        }

        try {
            FileInputStream file = new FileInputStream(Main.dataSerLocation);
            BufferedInputStream buffer = new BufferedInputStream(file);
            ObjectInputStream in = new ObjectInputStream(buffer);

            global_data = (Global_Data) in.readObject();
            coin = (ArrayList<Coin>) in.readObject();
            Debug.log("Deserialized Data From " + Main.dataSerLocation);
            in.close();
        } catch(Exception ex) {
            Debug.log("ERROR: WebData.deserialize(), deleting " + Main.dataSerLocation);
            File deleteFile = new File(Main.dataSerLocation);
            deleteFile.delete();

            this.fetch();
        }
    }

    public class Global_Data implements Serializable { // global data API
        private static final long serialVersionUID = 1L;

        @SerializedName(value="total_market_cap_usd", alternate={"total_market_cap_sek", "total_market_cap_eur", "total_market_cap_aud", "total_market_cap_brl", "total_market_cap_cad", "total_market_cap_chf", "total_market_cap_clp", "total_market_cap_cny", "total_market_cap_czk",
        "total_market_cap_dkk", "total_market_cap_gbp", "total_market_cap_hkd", "total_market_cap_huf", "total_market_cap_idr", "total_market_cap_ils", "total_market_cap_inr", "total_market_cap_jpy", "total_market_cap_krw", "total_market_cap_mxn", "total_market_cap_myr",
        "total_market_cap_nok", "total_market_cap_nzd", "total_market_cap_php", "total_market_cap_pkr", "total_market_cap_pln", "total_market_cap_rub", "total_market_cap_sgd", "total_market_cap_thb", "total_market_cap_try", "total_market_cap_twd", "total_market_cap_zar"})
        long total_market_cap;
        
        @SerializedName(value="total_24h_volume_usd", alternate={"total_24h_volume_sek", "total_24h_volume_eur", "total_24h_volume_aud", "total_24h_volume_brl", "total_24h_volume_cad", "total_24h_volume_chf", "total_24h_volume_clp", "total_24h_volume_cny", "total_24h_volume_czk",
        "total_24h_volume_dkk", "total_24h_volume_gbp", "total_24h_volume_hkd", "total_24h_volume_huf", "total_24h_volume_idr", "total_24h_volume_ils", "total_24h_volume_inr", "total_24h_volume_jpy", "total_24h_volume_krw", "total_24h_volume_mxn", "total_24h_volume_myr",
        "total_24h_volume_nok", "total_24h_volume_nzd", "total_24h_volume_php", "total_24h_volume_pkr", "total_24h_volume_pln", "total_24h_volume_rub", "total_24h_volume_sgd", "total_24h_volume_thb", "total_24h_volume_try", "total_24h_volume_twd", "total_24h_volume_zar"})
        long total_24h_volume;
        
        double bitcoin_percentage_of_market_cap;
        int active_currencies;
        int active_assets;
        int active_markets;
        long last_updated;

        public String toString() { // for global data button
            DecimalFormat df = new DecimalFormat("###,###");
            return "Total Market Cap: " + df.format(total_market_cap)
            + "\nTotal 24 Hour Volume: " + df.format(total_24h_volume)
            + "\nBitcoin Dominance: " + bitcoin_percentage_of_market_cap + "%"
            + "\nActive Currencies: " + active_currencies
            + "\nActive Assets: " + active_assets
            + "\nActive Markets: " + active_markets
            + "\nLast Updated: " + last_updated;
        }
    }

    public Coin getCoin() {
        return new Coin();
    }

    public class Coin implements Serializable, Cloneable { // store coin data in this class
        private static final long serialVersionUID = 1L;
        String id;
        String name;
        String symbol;
        short rank;
        double price_btc;

        //@SerializedName("price_"+Main.currency.toLowerCase()) double price_usd;
        @SerializedName(value="price_usd", alternate={"price_sek", "price_eur", "price_aud", "price_brl", "price_cad", "price_chf", "price_clp", "price_cny", "price_czk",
        "price_dkk", "price_gbp", "price_hkd", "price_huf", "price_idr", "price_ils", "price_inr", "price_jpy", "price_krw", "price_mxn", "price_myr",
        "price_nok", "price_nzd", "price_php", "price_pkr", "price_pln", "price_rub", "price_sgd", "price_thb", "price_try", "price_twd", "price_zar"})
        double price;

        @SerializedName(value="24h_volume_usd", alternate={"24h_volume_sek", "24h_volume_eur", "24h_volume_aud", "24h_volume_brl", "24h_volume_cad", "24h_volume_chf", "24h_volume_clp", "24h_volume_cny", "24h_volume_czk",
        "24h_volume_dkk", "24h_volume_gbp", "24h_volume_hkd", "24h_volume_huf", "24h_volume_idr", "24h_volume_ils", "24h_volume_inr", "24h_volume_jpy", "24h_volume_krw", "24h_volume_mxn", "24h_volume_myr",
        "24h_volume_nok", "24h_volume_nzd", "24h_volume_php", "24h_volume_pkr", "24h_volume_pln", "24h_volume_rub", "24h_volume_sgd", "24h_volume_thb", "24h_volume_try", "24h_volume_twd", "24h_volume_zar"})
        double _24h_volume; // long
        
        @SerializedName(value="market_cap_usd", alternate={"market_cap_sek", "market_cap_eur", "market_cap_aud", "market_cap_brl", "market_cap_cad", "market_cap_chf", "market_cap_clp", "market_cap_cny", "market_cap_czk",
        "market_cap_dkk", "market_cap_gbp", "market_cap_hkd", "market_cap_huf", "market_cap_idr", "market_cap_ils", "market_cap_inr", "market_cap_jpy", "market_cap_krw", "market_cap_mxn", "market_cap_myr",
        "market_cap_nok", "market_cap_nzd", "market_cap_php", "market_cap_pkr", "market_cap_pln", "market_cap_rub", "market_cap_sgd", "market_cap_thb", "market_cap_try", "market_cap_twd", "market_cap_zar"})
        long market_cap; // long
        
        long available_supply; // long
        long total_supply; // long
        long max_supply; // long
        double percent_change_1h;
        double percent_change_24h;
        double percent_change_7d;
        long last_updated;

        // for portfolio
        double portfolio_amount;
        double portfolio_price;
        double portfolio_value;
        double portfolio_gains;
        String portfolio_currency;
        double portfolio_price_start;
        double portfolio_value_start;

        public Object copy() {
            try {
                return clone();
            } catch(Exception ex) {
                ex.printStackTrace();
            }

            return new Object();
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public String toString() {
            return name;
        }

        public String trimPrice(double trimPrice) {
            String returnString = "";
            if (trimPrice > 1) {
                DecimalFormat df = new DecimalFormat("#.##");
                returnString = String.valueOf(df.format(trimPrice));
            }
            else if (trimPrice > 0.1) {
                DecimalFormat df = new DecimalFormat("#.###");
                returnString = String.valueOf(df.format(trimPrice));
            }
            else if (trimPrice > 0.01) {
                DecimalFormat df = new DecimalFormat("#.####");
                returnString = String.valueOf(df.format(trimPrice));
            }
            else if (trimPrice > 0.001) {
                DecimalFormat df = new DecimalFormat("#.#####");
                returnString = String.valueOf(df.format(trimPrice));
            }
            else if (trimPrice > 0.0001) {
                DecimalFormat df = new DecimalFormat("#.######");
                returnString = String.valueOf(df.format(trimPrice));
            }
            else {
                DecimalFormat df = new DecimalFormat("#.############");
                returnString = String.valueOf(df.format(trimPrice));
            }

            return returnString;
        }

        public String getInfo() {
            DecimalFormat df = new DecimalFormat("###,###");
            return "Rank: " + rank
            + "\nID: " + id
            + "\nName: " + name
            + "\nSymbol: " + symbol
            + "\nPrice BTC: " + trimPrice(price_btc)
            + "\nPrice " + Main.currency+ ": " + trimPrice(price)
            + "\nMarket Cap: " + df.format(market_cap)
            + "\n24 Hour Volume: " + df.format(_24h_volume)
            + "\nAvailable Supply: " + df.format(available_supply)
            + "\nTotal Supply: " + df.format(total_supply)
            + "\nMax Supply: " + df.format(max_supply)
            + "\nPercent 1 Hour: " + percent_change_1h + "%"
            + "\nPercent 24 Hour: " + percent_change_24h + "%"
            + "\nPercent 7 Days: " + percent_change_7d + "%"
            + "\nLast Updated: " + last_updated;
        }

        public String getPortfolio() {
            DecimalFormat df = new DecimalFormat("###,###.##");
            return getInfo()
            + "\n"
            + "\nPortfolio Amount: " + trimPrice(portfolio_amount)
            //+ "\nPortfolio Price: " + trimPrice(portfolio_price) // necessary for converting, but not necessary to display
            + "\nPortfolio Value: " + df.format(portfolio_value)
            + "\nPortfolio Gains: " + df.format(portfolio_gains)
            + "\n"
            + "\nPortfolio Currency: " + portfolio_currency
            + "\nPortfolio Price Start: " + df.format(portfolio_price_start)
            + "\nPortfolio Value Start: " + df.format(portfolio_value_start);
        }
    }
}
