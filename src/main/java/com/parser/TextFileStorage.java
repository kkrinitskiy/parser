package com.parser;

import com.parser.cryptoSiteParsers.CoinMarketCapComParser;
import com.parser.cryptoSiteParsers.CryptoSiteParser;
import com.parser.dto.Cryptocurrency;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileStorage {

    public void store(List<CryptoSiteParser> parsers){
        for (CryptoSiteParser parser : parsers) {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/" + parser.getSiteName() + "_output.txt"))) {
                parser.getCryptocurrency();
                for (Cryptocurrency cryptocurrency : parser.getCryptocurrencyList()) {
                    writer.write(cryptocurrency.name() + " - " + cryptocurrency.currentRate() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
