package com.parser;

import com.parser.cryptoSiteParsers.CoinMarketCapComParser;
import com.parser.cryptoSiteParsers.CryptoComParser;
import com.parser.cryptoSiteParsers.OkxComParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App
{
    public static void main( String[] args )
    {

        int timeWaitInSeconds = 10;

        try(ExecutorService executorService = Executors.newCachedThreadPool()) {

            executorService.execute(new CoinMarketCapComParser(timeWaitInSeconds));
            executorService.execute(new OkxComParser(timeWaitInSeconds));
            executorService.execute(new CryptoComParser(timeWaitInSeconds));


//      добавить обработку ошибок
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
