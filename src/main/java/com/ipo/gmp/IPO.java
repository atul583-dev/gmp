package com.ipo.gmp;

public class IPO {
    private String currentIPO;
    private String ipoGMP;
    private String gmpTrend;
    private String priceBand;
    private String listingGain;
    private String expectedListing;
    private String ipoDate;

    // Constructor
    public IPO(String currentIPO, String ipoGMP, String gmpTrend, String priceBand,
               String listingGain, String expectedListing, String ipoDate) {
        this.currentIPO = currentIPO;
        this.ipoGMP = ipoGMP;
        this.gmpTrend = gmpTrend;
        this.priceBand = priceBand;
        this.listingGain = listingGain;
        this.expectedListing = expectedListing;
        this.ipoDate = ipoDate;
    }

    // Getters and Setters
    public String getCurrentIPO() {
        return currentIPO;
    }

    public void setCurrentIPO(String currentIPO) {
        this.currentIPO = currentIPO;
    }

    public String getIpoGMP() {
        return ipoGMP;
    }

    public void setIpoGMP(String ipoGMP) {
        this.ipoGMP = ipoGMP;
    }

    public String getGmpTrend() {
        return gmpTrend;
    }

    public void setGmpTrend(String gmpTrend) {
        this.gmpTrend = gmpTrend;
    }

    public String getPriceBand() {
        return priceBand;
    }

    public void setPriceBand(String priceBand) {
        this.priceBand = priceBand;
    }

    public String getListingGain() {
        return listingGain;
    }

    public void setListingGain(String listingGain) {
        this.listingGain = listingGain;
    }

    public String getExpectedListing() {
        return expectedListing;
    }

    public void setExpectedListing(String expectedListing) {
        this.expectedListing = expectedListing;
    }

    public String getIpoDate() {
        return ipoDate;
    }

    public void setIpoDate(String ipoDate) {
        this.ipoDate = ipoDate;
    }

    @Override
    public String toString() {
        return "IPO{" +
                "currentIPO='" + currentIPO + '\'' +
                ", ipoGMP='" + ipoGMP + '\'' +
                ", gmpTrend='" + gmpTrend + '\'' +
                ", priceBand='" + priceBand + '\'' +
                ", listingGain='" + listingGain + '\'' +
                ", expectedListing='" + expectedListing + '\'' +
                ", ipoDate='" + ipoDate + '\'' +
                '}';
    }
}