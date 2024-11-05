package ae.cyberspeed.assignment.scratch.game.dto.symbols.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cell {
    @JsonProperty("row")
    public int row;

    @JsonProperty("column")
    public int column;

    @JsonProperty("type")
    public String type;

    @JsonProperty("symbol")
    public String symbol;

    public Cell(int row, int column, String type, String symbol) {
        this.row = row;
        this.column = column;
        this.type = type;
        this.symbol = symbol;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
