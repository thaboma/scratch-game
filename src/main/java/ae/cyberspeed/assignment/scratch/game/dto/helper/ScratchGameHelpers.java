package ae.cyberspeed.assignment.scratch.game.dto.helper;

import ae.cyberspeed.assignment.scratch.game.dto.ScratchGameConfig;
import ae.cyberspeed.assignment.scratch.game.dto.WinCombinations;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.input.MatrixSymbol;

import java.util.*;
import java.util.stream.Collectors;

public class ScratchGameHelpers {

    private ScratchGameHelpers() {
    }

    public static Map<String, List<String>> getAmalgamatedWinnings(ScratchGameConfig scratchGameConfig, MatrixSymbol inputMatrix) {
        WinCombinations winCombinations = scratchGameConfig.getWin_combinations();
        Map<String, List<List<String>>> coveredAreas = winCombinations.getCoveredAreas();
        Map<String, String> winningCombinations = getWinningCombination(inputMatrix.getSameSymbolsXtimes());
        Map<String, String> winOrientedCombinations = getWinOrientedCombinations(inputMatrix.getSymbolsOrientation(), coveredAreas);
        Map<String, List<String>> amalgamatedWinnings = amalgamateWinnings(winningCombinations, winOrientedCombinations);
        return amalgamatedWinnings;
    }

    private static Map<String, List<String>> amalgamateWinnings(Map<String, String> map1, Map<String, String> map2) {
        Map<String, List<String>> result = new HashMap<>();
        map1.entrySet().stream().forEach(entry -> {
            result.put(entry.getKey(), new ArrayList<>());
            result.get(entry.getKey()).add(entry.getValue());
        });

        map1.entrySet().stream().forEach(e -> {
            if (map2.containsKey(e.getKey())) {
                result.get(e.getKey()).add(map2.get(e.getKey()));
            }
        });
        return result;
    }

    private static Map<String, String> getWinningCombination(Map<String, Integer> symbolsXtimesMap) {
        Map<String, String> winningCombinationz = new HashMap<>();
        symbolsXtimesMap.entrySet().parallelStream().forEach(e ->
        {
            String winningComb = getWiningCombinationStr(e.getValue());
            winningCombinationz.put(e.getKey(), winningComb);
        });
        return winningCombinationz;
    }

    private static Map<String, String> getWinOrientedCombinations(Map<String, List<String>> symbolsOrientation,
                                                                  Map<String, List<List<String>>> coveredAreas) {
        Map<String, String> winningCombinationz = new HashMap<>();
        symbolsOrientation.entrySet().parallelStream().forEach(orientation ->
        {
            String key = orientation.getKey();
            List<String> value = orientation.getValue();
            var orientatio = coveredAreas.entrySet().parallelStream().filter(e -> hasMatch(value, e.getValue())).map(e -> e.getKey()).findFirst().orElse(null);
            if (orientatio != null) {
                winningCombinationz.put(key, orientatio);
            }
        });
        return winningCombinationz;
    }

    public static boolean hasMatch(List<String> list1, List<List<String>> list2) {
        boolean result = false;
        for (List<String> strList : list2) {
            result = result || list1.containsAll(strList);
        }
        return result;
    }


    private static String getWiningCombinationStr(int numberOfRepeats) {
        return String.format("same_symbol_%s_times", numberOfRepeats);
    }

    public static Map<String, List<String>> filterUnwantedSymbolsCounts(Map<String, List<String>> stdWins) {
        var filteredStdWins = stdWins
                .entrySet()
                .stream()
                .map(entr -> {
                    entr.getValue().removeAll(Arrays.asList("same_symbol_1_times", "same_symbol_2_times"));
                    return entr;
                })
                .filter(e -> !e.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return filteredStdWins;
    }
}
