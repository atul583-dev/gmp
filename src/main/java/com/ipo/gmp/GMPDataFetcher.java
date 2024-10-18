package com.ipo.gmp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class GMPDataFetcher {
    //private static final String GMP_URL = "https://www.investorgain.com/gmp/hyundai-motor-ipo-gmp/1024/";

    private static final String GMP_URL = "https://www.investorgain.com/report/live-ipo-gmp/331/";
    //private static final String GMP_URL = "https://ipocentral.in/";
    //private static final String GMP_URL = "https://ipowatch.in/ipo-grey-market-premium-latest-ipo-gmp/";
    public List<IPO> fetchGMPData() {
        List<IPO> ipoList = new ArrayList<>();
        try {
            // Example for ignoring SSL certificate validation (not recommended for production)
            // Create an SSLContext with the desired protocol
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, new java.security.SecureRandom());
            System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");
            // Connect to the Chittorgarh GMP page
            Document document = Jsoup.connect(GMP_URL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                    .get();
            // Select the table containing the GMP data
            Elements rows = document.select("#mainTable tbody tr");

            for (Element row : rows) {
                String ipo = row.select("td[data-label='IPO'] a").text();
                String price = row.select("td[data-label='Price']").text();
                String gmp = row.select("td[data-label='GMP(₹)'] b").text();
                String estListing = row.select("td[data-label='Est Listing'] b").text();
                String ipoSize = row.select("td[data-label='IPO Size']").text();
                String lot = row.select("td[data-label='Lot']").text();
                String open = row.select("td[data-label='Open']").text();
                String close = row.select("td[data-label='Close']").text();
                String listing = row.select("td[data-label='Listing']").text();
                String gmpUpdated = row.select("td[data-label='GMP Updated']").text();

                ipoList.add(new IPO(ipo, price, gmp, estListing, ipoSize, lot, open, close, listing, gmpUpdated));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
        //getValues();
        return ipoList;
    }
}
