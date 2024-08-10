package com.parser.cryptoSiteParsers;

import com.parser.TextFileStorage;
import com.parser.dto.Cryptocurrency;

import java.util.List;
import java.util.concurrent.TimeUnit;


public interface CryptoSiteParser extends Runnable{

    int getWaitSeconds();
    String getSiteName();
    void getCryptocurrency();
    List<Cryptocurrency> getCryptocurrencyList();



    @Override
    default void run(){
        TextFileStorage storage = new TextFileStorage();
        while(true){
            getCryptocurrency();
            storage.store(getCryptocurrencyList(), getSiteName());
            try {
                TimeUnit.SECONDS.sleep(getWaitSeconds());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };
}
