package com.parser.cryptoSiteParsers;

import com.parser.dto.Cryptocurrency;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  Данный класс парсит страницу <a href="https://crypto.com/price">crypto.com/price</a>
 *  Создает и хранит объекты Cryptocurrency
 */
@Getter
public class CryptoComParser implements CryptoSiteParser {
    private final String link = "https://crypto.com/price";
    private final String siteName = "crypto.com";

    private List<Cryptocurrency> cryptocurrencyList = new ArrayList<>();

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
                    .map(e -> e.getElementsByTag("p"))
                    .filter(e -> !e.isEmpty())
                    .forEach(e ->
                cryptocurrencyList.add(
                        new Cryptocurrency(e.get(0).text(), e.get(1).text(), siteName)
                )
            );
        } catch (IOException e) {
            throw new RuntimeException("Соединение не удалось " + link, e);
        } catch (NullPointerException e) {
            throw new RuntimeException("tbody = null " + link, e);
        }
    }
}
