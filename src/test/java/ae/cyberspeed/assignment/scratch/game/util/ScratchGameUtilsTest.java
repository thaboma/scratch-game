package ae.cyberspeed.assignment.scratch.game.util;

import ae.cyberspeed.assignment.scratch.game.dto.symbols.input.MatrixSymbol;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static ae.cyberspeed.assignment.scratch.game.util.ScratchGameUtils.symbolsProbabilityGenerator;
import static org.junit.jupiter.api.Assertions.*;

class ScratchGameUtilsTest {

    ScratchGameUtils gameUtils;
    JsonNode json;

    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br = new BufferedReader(new FileReader("../config.json"));
        json = mapper.readTree(br);
    }

    @Test
    void initialiseScratchGame() {
        ScratchGameUtils.initialiseScratchGame(json);
        var symbolMap = ScratchGameUtils.getSymbolsMap();
        assertEquals(11, symbolMap.size());
    }

    @Test
    void extractSymbols() {
        var symbol = ScratchGameUtils.extractSymbols(json);
        assertEquals(1000, symbol.get_1000().extra);
    }

    @Test
    void extractProbabilities() {
        var probabilities = ScratchGameUtils.extractProbabilities(json);
        assertEquals(10, probabilities.bonus_symbols.getSymbols().get_10x().getReward_multiplier());
    }

    @Test
    void extractStandardSymbols() {
        var stdSymbols = ScratchGameUtils.extractStandardSymbols(json);
        assertEquals(0, stdSymbols.get(0).getColumn());
    }

    @Test
    void extractBonusSymbols() {
        var bonusSymbols = ScratchGameUtils.extractBonusSymbols(json);
        assertEquals(5, bonusSymbols.getSymbols()._5x.getReward_multiplier());
    }

    @Test
    void extractWinCombinations() {
        var combinations = ScratchGameUtils.extractWinCombinations(json);
        assertEquals(20, combinations.same_symbol_9_times.getReward_multiplier());
    }

    @Test
    void extractPropabilitySymbols() {
        var propabilitySymbols = ScratchGameUtils.extractPropabilitySymbols(json);
        assertEquals(5, propabilitySymbols.entrySet().size());
    }

    @Test
    void testMatrixGenerator() {

        int width = 3;
        int height = 3;
        String[] symbolsArray = new String[]{"A", "B", "C", "D", "E", "F", "5x", "10x", "+500", "+1000", "miss"};
        MatrixSymbol matrixSymbol = symbolsProbabilityGenerator(width, height);
        assertNotNull(matrixSymbol);
        assertTrue(matrixSymbol.getMatrix().values().parallelStream().anyMatch(cell -> !Arrays.stream(symbolsArray).toList().contains(cell)));
    }
}