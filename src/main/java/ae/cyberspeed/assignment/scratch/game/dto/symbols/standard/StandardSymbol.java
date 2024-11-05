package ae.cyberspeed.assignment.scratch.game.dto.symbols.standard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class StandardSymbol {
    @JsonProperty("column")
    public int column;

    @JsonProperty("row")
    public int row;

    @JsonProperty("symbolProbability")
    public Map<String, Number> symbolProbability;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Map<String, Number> getSymbolProbability() {
        return symbolProbability;
    }

    public void setSymbolProbability(Map<String, Number> symbolProbability) {
        this.symbolProbability = symbolProbability;
    }

    @Override
    public String toString() {
        return "StandardSymbol{" +
                "column=" + column +
                ", row=" + row +
                ", symbolProbability=" + symbolProbability +
                '}';
    }
}
