package com.ipo.gmp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GMPDataFetcher {
    //private static final String GMP_URL = "https://www.investorgain.com/report/live-ipo-gmp/331/";
    private static final String GMP_URL = "https://ipowatch.in/ipo-grey-market-premium-latest-ipo-gmp/";

    // Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(GMPDataFetcher.class);

    private final RestTemplate restTemplate;

    @Autowired
    public GMPDataFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<IPO> fetchGMPData() {
        List<IPO> ipoList = new ArrayList<>();


        System.out.println("-------This----------");
        logger.debug("logger ------------------: ");

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.96 Safari/537.36");
        headers.set("Referer", "https://ipowatch.in");

        // Create an HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Set the URL to scrape

            System.out.println("-------Before----------");
            // Connect to the URL and add headers
            Document document = Jsoup.connect(GMP_URL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .referrer("https://www.google.com/")
                    .timeout(5000)  // Set timeout to prevent hanging
                    .get();

            System.out.println("-------After----------");
            // Process the scraped data
            System.out.println(document.title());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Make the HTTP request
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(GMP_URL, HttpMethod.GET, entity, String.class);
            String responseBody = response.getBody();
            System.out.println("------responseBody SOP : " + responseBody);
            logger.debug("----RESPONSEBODY : " + responseBody);

            if (responseBody != null) {
                // Parse the HTML response using Jsoup
                Document document = Jsoup.parse(responseBody);
                // Select the table containing the GMP data
                Elements rows = document.select("figure.wp-block-table table tbody tr");

                // Iterate through each row and extract data
                for (Element row : rows) {
                    String currentIPO = row.select("td").get(0).text();
                    String ipoGMP = row.select("td").get(1).text();
                    String gmpTrend = row.select("td").get(2).select("img").attr("src"); // Gets the image source for trend
                    String priceBand = row.select("td").get(3).text();
                    String listingGain = row.select("td").get(4).text();
                    String expectedListing = row.select("td").get(5).text();
                    String ipoDate = row.select("td").get(6).text();

                    ipoList.add(new IPO(currentIPO, ipoGMP, ipoGMP, ipoGMP, ipoGMP, ipoGMP, ipoGMP, ipoGMP, ipoGMP, ipoGMP));
                    //ipoList.add(new IPO(ipo, price, gmp, estListing, ipoSize, lot, open, close, listing, gmpUpdated));
                }
            } else {
                System.err.println("No response body received.");
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error fetching GMP data: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        return ipoList;
    }

}