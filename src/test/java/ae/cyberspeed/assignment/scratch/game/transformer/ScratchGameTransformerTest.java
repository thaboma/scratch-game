package ae.cyberspeed.assignment.scratch.game.transformer;

import ae.cyberspeed.assignment.scratch.game.dto.Symbols;
import ae.cyberspeed.assignment.scratch.game.dto.WinCombinations;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScratchGameTransformerTest {

    ScratchGameTransformer transformer;
    JsonNode json;

    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br = new BufferedReader(new FileReader("../config.json"));
        transformer = new ScratchGameTransformer();
        json = mapper.readTree(br);
    }

    @Test
    void mapSymbols() {
        Symbols symbolz = new Symbols();
        Symbols symbols = ScratchGameTransformer.mapSymbols(symbolz, "a", "5", "standard", "2", "10");
        assertEquals(symbols.a.getReward_multiplier(), 5.0D);
    }

    @Test
    void mapWinCombinations() {
        WinCombinations winombinations = new WinCombinations();
        var winCom = ScratchGameTransformer.mapWinCombinations(winombinations, "same_symbol_6_times", "6", "standard", "2", "10");
        assertEquals(winCom.same_symbol_6_times.getReward_multiplier(), 6.0D);
    }

}