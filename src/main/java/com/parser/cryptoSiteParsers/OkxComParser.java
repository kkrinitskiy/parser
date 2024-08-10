package com.parser.cryptoSiteParsers;

import com.parser.TextFileStorage;
import com.parser.dto.Cryptocurrency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  Данный класс парсит страницу <a href="https://www.okx.com/ru/markets/prices">okx.com</a>
 *  Создает и хранит объекты Cryptocurrency
 */
public class OkxComParser implements CryptoSiteParser {
    private final List<Cryptocurrency> cryptocurrencyList = new ArrayList<>();
    private final String link = "https://www.okx.com/ru/markets/prices";
    private final String siteName = "okx.com";
    private final int waitSeconds;

    public OkxComParser(int waitSeconds) {
        this.waitSeconds = waitSeconds;
    }


    /**
     * Метод с помощью библиотеки Jsoup создает соединение с сайтом,
     * получает элемент tbody, обращается к дочерним элементам tr,
     * из каждого построчно получает текстовые значения колонок,
     * отсеивает пустые и создает из полученных данных объекты
     * Cryptocurrency
     */
    @Override
    public void getCryptocurrency() {
        try {
            Document doc = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get();
            Objects.requireNonNull(doc.select("tbody").first())
                    .children()
                    .stream()
                    .forEach(
                        e ->{
                            cryptocurrencyList.add(
                                    new Cryptocurrency(
                                            e.select("span.full-name").first().text(),
                                            e.select("td.last-price").first().text()
                                                    .replace(",",".")
                                                    .replace(" ",""),
                                            siteName)
                            );
                    });
        } catch (IOException e) {
            throw new RuntimeException("Соединение не удалось " + link, e);
        } catch (NullPointerException e) {
            throw new RuntimeException("tbody = null " + link, e);
        }
    }

    @Override
    public int getWaitSeconds() {
        return waitSeconds;
    }

    @Override
    public String getSiteName() {
        return siteName;
    }

    @Override
    public List<Cryptocurrency> getCryptocurrencyList() {
        return List.copyOf(cryptocurrencyList);
    }


}
