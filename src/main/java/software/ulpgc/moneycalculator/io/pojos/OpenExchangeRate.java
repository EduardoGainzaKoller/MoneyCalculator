package software.ulpgc.moneycalculator.io.pojos;

import java.util.List;

import java.util.Map;

public record OpenExchangeRate(
        String disclaimer,
        String license,
        long timestamp,
        String base,
        Map<String, Double> rates
) {}

