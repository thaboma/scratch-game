package ae.cyberspeed.assignment.scratch.game.dto;

import ae.cyberspeed.assignment.scratch.game.dto.symbols.bonus.BonusSymbols;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.standard.StandardSymbol;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Probabilities {
    @JsonProperty("standard_symbols")
    public List<StandardSymbol> standard_symbols;

    @JsonProperty("bonus_symbols")
    public BonusSymbols bonus_symbols;

    public List<StandardSymbol> getStandard_symbols() {
        return standard_symbols;
    }

    public void setStandard_symbols(List<StandardSymbol> standard_symbols) {
        this.standard_symbols = standard_symbols;
    }

    public BonusSymbols getBonus_symbols() {
        return bonus_symbols;
    }

    public void setBonus_symbols(BonusSymbols bonus_symbols) {
        this.bonus_symbols = bonus_symbols;
    }

}
