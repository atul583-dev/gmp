package com.ipo.gmp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GMPController {

    @GetMapping("/api/gmp")
    public List<IPO> getGMPData() {
        GMPDataFetcher fetcher = new GMPDataFetcher();
        return fetcher.fetchGMPData(); // Returns list of IPOs with GMP data
    }
}