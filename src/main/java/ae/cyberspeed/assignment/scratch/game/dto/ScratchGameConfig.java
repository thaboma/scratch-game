package ae.cyberspeed.assignment.scratch.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ScratchGameConfig {
    @JsonProperty("symbolsMap")
    private static Map<String, Object> symbolsMap;
    @JsonProperty("columns")
    public int columns;
    @JsonProperty("rows")
    public int rows;
    @JsonProperty("symbols")
    public Symbols symbols;
    @JsonProperty("probabilities")
    public Probabilities probabilities;
    @JsonProperty("win_combinations")
    public WinCombinations win_combinations;

    public static Map<String, Object> getSymbolsMap() {
        return symbolsMap;
    }

    public static void setSymbolsMap(Map<String, Object> symbolsMap) {
        symbolsMap = symbolsMap;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public WinCombinations getWin_combinations() {
        return win_combinations;
    }

    public void setWin_combinations(WinCombinations win_combinations) {
        this.win_combinations = win_combinations;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Symbols getSymbols() {
        return symbols;
    }

    public void setSymbols(Symbols symbols) {
        this.symbols = symbols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ScratchGameConfig{" +
                "columns=" + columns +
                ", rows=" + rows +
                ", symbols=" + symbols +
                ", probabilities=" + probabilities +
                ", win_combinations=" + win_combinations +
                '}';
    }
}
