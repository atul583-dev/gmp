package com.ipo.gmp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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


    private final RestTemplate restTemplate;

    @Autowired
    public GMPDataFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<IPO> fetchGMPData() {
        List<IPO> ipoList = new ArrayList<>();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.96 Safari/537.36");
        headers.set("Referer", "https://ipowatch.in");

        // Create an HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);


        // Make the HTTP request
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(GMP_URL, HttpMethod.GET, entity, String.class);
            String responseBody = response.getBody();

            if (responseBody != null) {
                // Parse the HTML response using Jsoup
                Document document = Jsoup.parse(responseBody);

                Elements rows = document.select("figure.wp-block-table table tbody tr");

                    // Iterate through each row starting from the second row (index 1)
                    for (int i = 1; i < rows.size(); i++) { // Start from index 1 to skip the header
                        Element row = rows.get(i); // Get the current row
                        String currentIPO = row.select("td").get(0).text();
                        String ipoGMP = row.select("td").get(1).text();
                        String gmpTrend = row.select("td").get(2).select("img").attr("src"); // Gets the image source for trend
                        String priceBand = row.select("td").get(3).text();
                        String listingGain = row.select("td").get(4).text();
                        String expectedListing = row.select("td").get(5).text();
                        String ipoDate = row.select("td").get(6).text();
                        ipoList.add(new IPO(currentIPO, ipoGMP, gmpTrend, priceBand, listingGain, expectedListing, ipoDate));
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