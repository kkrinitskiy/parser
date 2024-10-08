package com.parser.cryptoSiteParsers;

import com.parser.dto.Cryptocurrency;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  Данный класс парсит страницу <a href="https://coinmarketcap.com/">coinmarketcap.com</a>
 *  Создает и хранит объекты Cryptocurrency
 */
public class CoinMarketCapComParser implements CryptoSiteParser {
    private final List<Cryptocurrency> cryptocurrencyList = new ArrayList<>();
    private final String link = "https://coinmarketcap.com/";
    private final String siteName = "coinmarketcap.com";
    private final int waitSeconds;

    public CoinMarketCapComParser(int waitSeconds) {
        this.waitSeconds = waitSeconds;
    }

    /**
     * Метод с помощью библиотеки Jsoup создает соединение с сайтом,
     * получает элемент tbody, обращается к дочерним элементам tr,
     * из каждого построчно получает текстовые значения колонок,
     * отсеивает пустые и создает из полученных данных объекты
     * Cryptocurrency
     */
    public void getCryptocurrency() {
        try {
            Document doc = Jsoup.connect(link).get();
            Objects.requireNonNull(doc.select("tbody").first())
                    .children()
                    .stream()
                    .map(e -> {
                        Elements children = e.children();
                        Elements p = children.get(2).select("p.sc-71024e3e-0.ehyBa-d");
                        p.addAll(children.get(3).select("span:not([class])"));
                        return p;
                    })
                    .filter(e -> !e.isEmpty())
                    .forEach(e ->
                            cryptocurrencyList.add(
                                    new Cryptocurrency(
                                            e.get(0).text(),
                                            e.get(1).text().replace(",",""),
                                            siteName)
                            )
                    );
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
