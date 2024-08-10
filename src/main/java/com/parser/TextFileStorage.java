package com.parser;

import com.parser.cryptoSiteParsers.CryptoSiteParser;
import com.parser.dto.Cryptocurrency;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextFileStorage {

    public void store(List<Cryptocurrency> cryptocurrencyList, String siteName){
        try{
            Files.deleteIfExists(Path.of("output/" + siteName + "_output.txt"));
        }catch (IOException e){
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/" + siteName + "_output.txt", false))) {
            for (Cryptocurrency cryptocurrency : cryptocurrencyList) {
                    writer.write(cryptocurrency.name() + " - " + cryptocurrency.currentRate() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
