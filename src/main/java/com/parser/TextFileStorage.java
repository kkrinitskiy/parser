package com.parser;

import com.parser.cryptoSiteParsers.CryptoSiteParser;
import com.parser.dto.Cryptocurrency;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileStorage {

    public void store(List<Cryptocurrency> cryptocurrencyList, String siteName){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/" + siteName + "_output.txt"))) {
                for (Cryptocurrency cryptocurrency : cryptocurrencyList) {
                    writer.write(cryptocurrency.name() + " - " + cryptocurrency.currentRate() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
