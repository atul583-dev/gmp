package com.ipo.gmp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        try {
            // URL of the Marketsmojo IPO page
            String url = "https://www.investorgain.com/report/live-ipo-gmp/331/"; // Replace with the specific page for IPOs
            Document doc = Jsoup.connect(url).timeout(10000).get();

            System.out.println(doc);
            // Use the appropriate CSS selector to extract GMP data
            // Select the table rows in the tbody
            Elements rows = doc.select("#mainTable tbody tr");

            // Loop through each row and extract the required values
            for (Element row : rows) {
                String ipo = row.select("td[data-label='IPO']").text();
                String price = row.select("td[data-label='Price']").text();
                String gmp = row.select("td[data-label='GMP(₹)'] b").text();
                String estListing = row.select("td[data-label='Est Listing'] b").text();
                String ipoSize = row.select("td[data-label='IPO Size']").text();
                String lot = row.select("td[data-label='Lot']").text();

                // Print the extracted values
                System.out.println("IPO: " + ipo);
                System.out.println("Price: " + price);
                System.out.println("GMP: " + gmp);
                System.out.println("Est Listing: " + estListing);
                System.out.println("IPO Size: " + ipoSize);
                System.out.println("Lot: " + lot);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

