package com.ipo.gmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GMPController {

    @Autowired
    GMPDataFetcher fetcher;

    @GetMapping("/api/gmp")
    public List<IPO> getGMPData() {

        return fetcher.fetchGMPData(); // Returns list of IPOs with GMP data
    }
}