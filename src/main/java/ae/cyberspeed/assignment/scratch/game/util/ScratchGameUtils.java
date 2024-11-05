package ae.cyberspeed.assignment.scratch.game.util;

import ae.cyberspeed.assignment.scratch.game.dto.Probabilities;
import ae.cyberspeed.assignment.scratch.game.dto.Symbols;
import ae.cyberspeed.assignment.scratch.game.dto.WinCombinations;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.bonus.BonusSymbols;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.input.MatrixSymbol;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.standard.StandardSymbol;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

import static ae.cyberspeed.assignment.scratch.game.transformer.ScratchGameTransformer.mapSymbols;
import static ae.cyberspeed.assignment.scratch.game.transformer.ScratchGameTransformer.mapWinCombinations;

public class ScratchGameUtils {

    public static final String BONUS_SYMBOLS = "bonus_symbols";
    public static final String STANDARD_SYMBOLS = "standard_symbols";
    public static final String ROW = "row";
    public static final String SYMBOLS = "symbols";
    public static final String COLUMN = "column";
    public static final String REWARD_MULTIPLIER = "reward_multiplier";
    public static final String TYPE = "type";
    public static final String IMPACT = "impact";
    public static final String EXTRA = "extra";
    public static final String A = "a";
    public static final String B = "b";
    public static final String C = "c";
    public static final String D = "d";
    public static final String E = "e";
    public static final String F = "f";
    public static final String BONUS = "bonus";
    public static final String EXTRA_BONUS = "extra_bonus";
    public static final String _5x = "5x";
    public static final String _plus1000 = "+1000";
    public static final String _plus500 = "+500";
    public static final String MISS = "miss";
    public static final String _10x = "10x";
    public static final String WIN_COMBINATIONS = "win_combinations";
    public static final String WHEN = "when";
    public static final String COUNT = "count";
    public static final String GROUP = "group";
    public static final String TIMES = "times";
    public static final String COVERED_AREAS = "covered_areas";
    public static final String EMPTY_STRING = "";
    public static final String PROBABILITIES = "probabilities";
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    private static Map<String, Symbols> symbolsMap;

    private ScratchGameUtils() {
        symbolsMap = new HashMap<>();
    }

    public static void initialiseScratchGame(JsonNode json) {
        Iterator<Map.Entry<String, JsonNode>> it = json.path(SYMBOLS).fields();

        Symbols symbols = new Symbols();
        while (it.hasNext()) {
            Map.Entry<String, JsonNode> node = it.next();
            String key = node.getKey().toLowerCase();
            JsonNode value = node.getValue();

            String reward_multiplier = value.path(REWARD_MULTIPLIER).asText();
            String type = value.path(TYPE).asText();
            String impact = value.path(IMPACT).asText();
            String extra = value.path(EXTRA).asText();
            mapSymbols(symbols, key, reward_multiplier, type, impact, extra);
            getSymbolsMap().put(key, symbols);
        }
    }

    public static Symbols extractSymbols(JsonNode json) {
        Iterator<Map.Entry<String, JsonNode>> it = json.path(SYMBOLS).fields();

        Symbols symbols = new Symbols();
        while (it.hasNext()) {
            Map.Entry<String, JsonNode> node = it.next();
            String key = node.getKey().toLowerCase();
            JsonNode value = node.getValue();

            String reward_multiplier = value.path(REWARD_MULTIPLIER).asText();
            String type = value.path(TYPE).asText();
            String impact = value.path(IMPACT).asText();
            String extra = value.path(EXTRA).asText();
            mapSymbols(symbols, key, reward_multiplier, type, impact, extra);
        }

        return symbols;
    }

    public static Probabilities extractProbabilities(JsonNode json) {
        Iterator<Map.Entry<String, JsonNode>> it = json.path(PROBABILITIES).fields();
        Probabilities probabilities = new Probabilities();
        List<StandardSymbol> standard_symbols = new ArrayList<>();
        BonusSymbols bonus_symbols = new BonusSymbols();

        while (it.hasNext()) {
            Map.Entry<String, JsonNode> node = it.next();
            String key = node.getKey().toLowerCase();
            JsonNode value = node.getValue();

            if (STANDARD_SYMBOLS.equals(key))
                standard_symbols = extractStandardSymbols(json);
            else if (BONUS_SYMBOLS.equals(key))
                bonus_symbols = extractBonusSymbols(value);

            probabilities.setStandard_symbols(standard_symbols);
            probabilities.setBonus_symbols(bonus_symbols);
        }
        return probabilities;
    }


    public static List<StandardSymbol> extractStandardSymbols(JsonNode json) {
        List<JsonNode> jsonNodes = json.findParents(SYMBOLS);
        List<StandardSymbol> standard_symbols = new ArrayList<>();

        for (JsonNode jsonNode : jsonNodes) {

            String row = jsonNode.path(ROW).asText();

            if (row == null || row.length() == 0) {
                continue;
            }
            String column = jsonNode.path(COLUMN).asText();
            Map<String, Number> symbols = extractPropabilitySymbols(jsonNode.get(SYMBOLS));
            StandardSymbol standardSymbol = new StandardSymbol();
            standardSymbol.setRow(Integer.parseInt(row));
            standardSymbol.setColumn(Integer.parseInt(column));

            standardSymbol.setSymbolProbability(symbols);
            standard_symbols.add(standardSymbol);
            System.out.println(jsonNode);
        }
        return standard_symbols;
    }

    public static BonusSymbols extractBonusSymbols(JsonNode json) {
        List<JsonNode> jsonNodes = json.findParents(SYMBOLS);
        BonusSymbols bonus_symbols = new BonusSymbols();
        Symbols symbols = new Symbols();
        for (JsonNode jsonNode : jsonNodes) {

            var symbol = jsonNode.path(SYMBOLS);
            String _10x = symbol.path(ScratchGameUtils._10x).asText();
            String _5x = symbol.path(ScratchGameUtils._5x).asText();
            String _1000 = symbol.path(_plus1000).asText();
            String _500 = symbol.path(_plus500).asText();
            String miss = symbol.path(MISS).asText();

            mapSymbols(symbols, ScratchGameUtils._10x, String.valueOf(10), BONUS, _10x, String.valueOf(0));
            mapSymbols(symbols, ScratchGameUtils._5x, String.valueOf(5), BONUS, _5x, String.valueOf(0));
            mapSymbols(symbols, _plus1000, _1000, EXTRA_BONUS, _1000, String.valueOf(1000));
            mapSymbols(symbols, _plus500, _500, EXTRA_BONUS, _500, String.valueOf(500));
            mapSymbols(symbols, MISS, miss, BONUS, miss, String.valueOf(0));
            bonus_symbols.setSymbols(symbols);
            System.out.println(jsonNode);
            break;
        }
        return bonus_symbols;
    }

    public static WinCombinations extractWinCombinations(JsonNode json) {
        Iterator<Map.Entry<String, JsonNode>> it = json.path(WIN_COMBINATIONS).fields();

        WinCombinations winCombinations = new WinCombinations();
        while (it.hasNext()) {
            Map.Entry<String, JsonNode> node = it.next();
            String key = node.getKey().toLowerCase();
            JsonNode value = node.getValue();

            String reward_multiplier = value.path(REWARD_MULTIPLIER).asText();
            String when = value.path(WHEN).asText();

            String count = value.path(COUNT).asText();
            String group = value.path(GROUP).asText();

            if (key.toLowerCase().endsWith(TIMES))
                mapWinCombinations(winCombinations, key, reward_multiplier, when, count, group);
            else {
                List<List<String>> areasList = getCoveredAreas(value);
                mapWinCombinations(winCombinations, key, reward_multiplier, when, count, group, areasList);
            }
            System.out.println(node);
        }
        return winCombinations;
    }

    private static List<List<String>> getCoveredAreas(JsonNode value) {
        Iterator<JsonNode> jnIt = value.path(COVERED_AREAS).deepCopy().elements();
        List<List<String>> areasList = new ArrayList<>();
        while (jnIt.hasNext()) {

            var area = jnIt.next();
            var areaText = area.toPrettyString().trim().replace("\"", "");
            var coveredArea = Arrays.stream(areaText.replace(OPEN_BRACKET, EMPTY_STRING).replace(CLOSE_BRACKET, EMPTY_STRING).split(",")).toList();
            coveredArea = coveredArea.parallelStream().map(s -> s.trim()).toList();
            areasList.add(coveredArea);
        }
        return areasList;
    }


    public static Map<String, Number> extractPropabilitySymbols(JsonNode json) {

        Iterator<Map.Entry<String, JsonNode>> it = json.fields();
        Map<String, Number> symbols = new HashMap<>();
        while (it.hasNext()) {

            var entry = it.next();
            String key = entry.getKey().toLowerCase();
            Number propability = !entry.getValue().asText().isEmpty() ? Double.parseDouble(entry.getValue().asText()) : 0D;
            symbols.put(key, propability);
        }
        return symbols;
    }

    public static Map<String, Symbols> getSymbolsMap() {
        if (symbolsMap == null) {
            symbolsMap = new HashMap<>();
        }
        return symbolsMap;
    }

    public static MatrixSymbol symbolsProbabilityGenerator(int width, int height) {

        String[] symbolsArray = new String[]{"A", "B", "C", "D", "E", "F", "5x", "10x", "+500", "+1000", "miss"};
        String[] standardSymbolsArray = new String[]{"A", "B", "C", "D", "E", "F"};
        Set<String> bonusSymbols = Set.of("5x", "10x", "+500", "+1000", "miss");
        int moduloN = symbolsArray.length;
        boolean foundBonusSymbol = false;
        Random random = new Random();
        int randomNumber = 0;
        int symbolPosition = 0;

        MatrixSymbol matrixSymbol = new MatrixSymbol(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if (!foundBonusSymbol) {

                    randomNumber = Math.abs(random.nextInt());
                    symbolPosition = randomNumber % moduloN;
                    String selectedSymbol = symbolsArray[symbolPosition];
                    if (bonusSymbols.contains(selectedSymbol)) {
                        foundBonusSymbol = true;
                        moduloN = standardSymbolsArray.length;
                    }
                    matrixSymbol.updateMatrixSymbol(i, j, selectedSymbol);
                } else {
                    randomNumber = Math.abs(random.nextInt());
                    symbolPosition = randomNumber % moduloN;
                    String selectedSymbol = standardSymbolsArray[symbolPosition];
                    matrixSymbol.updateMatrixSymbol(i, j, selectedSymbol);
                }
            }
        }
        System.out.println(matrixSymbol);
        return matrixSymbol;
    }

}
