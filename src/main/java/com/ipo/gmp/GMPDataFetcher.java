package com.ipo.gmp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import java.util.ArrayList;
import java.util.List;

public class GMPDataFetcher {
    private static final String GMP_URL = "https://www.investorgain.com/report/live-ipo-gmp/331/";

    public List<IPO> fetchGMPData() {
        List<IPO> ipoList = new ArrayList<>();
        try {
            // Create a custom TrustManager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                    }
            };

            // Initialize the SSL context with the custom TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLContext.setDefault(sslContext);

            // Connect to the GMP page
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
        return ipoList;
    }
}
