package com.parser.cryptoSiteParsers;

import com.parser.dto.Cryptocurrency;

import java.util.List;

public interface CryptoSiteParser {
    String getSiteName();
    void getCryptocurrency();
    List<Cryptocurrency> getCryptocurrencyList();
}
