package com.parser;

import com.parser.cryptoSiteParsers.CoinMarketCapComParser;
import com.parser.cryptoSiteParsers.CryptoComParser;
import com.parser.cryptoSiteParsers.CryptoSiteParser;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        List<CryptoSiteParser> list = new ArrayList<>();
        list.add(new CoinMarketCapComParser());
        list.add(new CryptoComParser());

        TextFileStorage textFileStorage = new TextFileStorage();
        textFileStorage.store(list);
    }
}
