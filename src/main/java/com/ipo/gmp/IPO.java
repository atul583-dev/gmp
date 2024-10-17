package com.ipo.gmp;

public class IPO {
    private String ipo;
    private String price;
    private String gmp;
    private String estListing;
    private String ipoSize;
    private String lot;
    private String open;
    private String close;
    private String listing;
    private String gmpUpdated;

    // Parameterized constructor
    public IPO(String ipo, String price, String gmp, String estListing, String ipoSize,
               String lot, String open, String close, String listing, String gmpUpdated) {
        this.ipo = ipo;
        this.price = price;
        this.gmp = gmp;
        this.estListing = estListing;
        this.ipoSize = ipoSize;
        this.lot = lot;
        this.open = open;
        this.close = close;
        this.listing = listing;
        this.gmpUpdated = gmpUpdated;
    }

    // Getters and Setters
    public String getIpo() {
        return ipo;
    }

    public void setIpo(String ipo) {
        this.ipo = ipo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGmp() {
        return gmp;
    }

    public void setGmp(String gmp) {
        this.gmp = gmp;
    }

    public String getEstListing() {
        return estListing;
    }

    public void setEstListing(String estListing) {
        this.estListing = estListing;
    }

    public String getIpoSize() {
        return ipoSize;
    }

    public void setIpoSize(String ipoSize) {
        this.ipoSize = ipoSize;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getListing() {
        return listing;
    }

    public void setListing(String listing) {
        this.listing = listing;
    }

    public String getGmpUpdated() {
        return gmpUpdated;
    }

    public void setGmpUpdated(String gmpUpdated) {
        this.gmpUpdated = gmpUpdated;
    }

    @Override
    public String toString() {
        return "IPO{" +
                "ipo='" + ipo + '\'' +
                ", price='" + price + '\'' +
                ", gmp='" + gmp + '\'' +
                ", estListing='" + estListing + '\'' +
                ", ipoSize='" + ipoSize + '\'' +
                ", lot='" + lot + '\'' +
                ", open='" + open + '\'' +
                ", close='" + close + '\'' +
                ", listing='" + listing + '\'' +
                ", gmpUpdated='" + gmpUpdated + '\'' +
                '}';
    }
}

