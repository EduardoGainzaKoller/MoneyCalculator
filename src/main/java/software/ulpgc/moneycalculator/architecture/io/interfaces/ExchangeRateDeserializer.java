package software.ulpgc.moneycalculator.architecture.io.interfaces;

public interface ExchangeRateDeserializer {
    Object deserialize(String line);
}
