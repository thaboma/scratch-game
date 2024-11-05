package ae.cyberspeed.assignment.scratch.game.dto.symbols.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixSymbol {

    @JsonProperty("matrix")
    public Map<String, Cell> matrix = new HashMap<>();

    @JsonProperty("sameSymbols")
    public Map<String, Integer> sameSymbolsXtimes = new HashMap<>();

    @JsonProperty("symbolsOrientation")
    public Map<String, List<String>> symbolsOrientation = new HashMap<>();

    public int width;
    public int height;

    public MatrixSymbol() {
    }

    public MatrixSymbol(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private static String getKey(int row, int column) {
        String key = String.format("%s:%s", row, column);
        return key;
    }

    public Map<String, Cell> getMatrix() {
        return matrix;
    }

    public void setMatrix(Map<String, Cell> matrix) {
        this.matrix = matrix;
    }

    public Map<String, Integer> getSameSymbolsXtimes() {
        return sameSymbolsXtimes;
    }

    public void setSameSymbolsXtimes(Map<String, Integer> sameSymbolsXtimes) {
        this.sameSymbolsXtimes = sameSymbolsXtimes;
    }

    public Map<String, List<String>> getSymbolsOrientation() {
        return symbolsOrientation;
    }

    public void setSymbolsOrientation(Map<String, List<String>> symbolsOrientation) {
        this.symbolsOrientation = symbolsOrientation;
    }

    public void updateMatrixSymbol(int row, int column, String type, String symbol) {
        String key = getKey(row, column);
        matrix.put(key, new Cell(row, column, type, symbol));

        if (!sameSymbolsXtimes.containsKey(symbol))
            sameSymbolsXtimes.put(symbol, 0);

        if (!symbolsOrientation.containsKey(symbol))
            symbolsOrientation.put(symbol, new ArrayList<>());

        sameSymbolsXtimes.put(symbol, sameSymbolsXtimes.get(symbol) + 1);

        symbolsOrientation.get(symbol).add(key);
        symbolsOrientation.put(symbol, symbolsOrientation.get(symbol));
    }

    public void updateMatrixSymbol(int row, int column, String symbol) {
        updateMatrixSymbol(row, column, "standard", symbol);
    }

    @Override
    public String toString() {
        return prettyPring();
    }

    private String prettyPring() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ \r\n");
        for (int i = 0; i < width; i++) {
            sb.append(" [");
            for (int j = 0; j < height; j++) {
                sb.append(matrix.get(getKey(i, j)).toString()).append(j < height - 1 ? "," : "");
            }
            sb.append("]\r\n");
        }
        return sb.append("]").toString();
    }
}
