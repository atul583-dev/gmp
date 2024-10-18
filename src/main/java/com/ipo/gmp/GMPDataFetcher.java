package com.ipo.gmp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class GMPDataFetcher {
    private static final String GMP_URL = "https://www.investorgain.com/report/live-ipo-gmp/331/";

    public List<IPO> fetchGMPData() {
        List<IPO> ipoList = new ArrayList<>();

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.96 Safari/537.36");
        headers.set("Referer", "https://www.investorgain.com/");

        // Create an HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the HTTP request
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(GMP_URL, HttpMethod.GET, entity, String.class);
            String responseBody = response.getBody();
            System.out.println("RESPONSEBODY : " + responseBody);

            if (responseBody != null) {
                // Parse the HTML response using Jsoup
                Document document = Jsoup.parse(responseBody);
                // Select the table containing the GMP data
                Elements rows = document.select("#mainTable tbody tr");

                for (Element row : rows) {
                    // Extract data and add to list
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