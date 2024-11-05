package ae.cyberspeed.assignment.scratch.game.client;

import ae.cyberspeed.assignment.scratch.game.dto.Probabilities;
import ae.cyberspeed.assignment.scratch.game.dto.ScratchGameConfig;
import ae.cyberspeed.assignment.scratch.game.dto.Symbols;
import ae.cyberspeed.assignment.scratch.game.dto.WinCombinations;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static ae.cyberspeed.assignment.scratch.game.util.ScratchGameUtils.*;

public class ScratchGameClient {

    private final ScratchGameConfig scratchGameConfig;

    public ScratchGameClient(String file) throws IOException {
        this.scratchGameConfig = loadConfig(file);
    }

    private ScratchGameConfig loadConfig(String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br = new BufferedReader(new FileReader(file));
        JsonNode json = mapper.readTree(br);
        initialiseScratchGame(json);
        ScratchGameConfig scratchGameConfig = new ScratchGameConfig();

        Symbols symbols = extractSymbols(json);
        Probabilities probabilities = extractProbabilities(json);
        WinCombinations winCombinations = extractWinCombinations(json);

        scratchGameConfig.setRows(json.get("rows").asInt());
        scratchGameConfig.setColumns(json.get("columns").asInt());
        scratchGameConfig.setSymbols(symbols);
        scratchGameConfig.setProbabilities(probabilities);
        scratchGameConfig.setWin_combinations(winCombinations);

        return scratchGameConfig;
    }

    public ScratchGameConfig getScratchGameConfig() {
        return scratchGameConfig;
    }
}
