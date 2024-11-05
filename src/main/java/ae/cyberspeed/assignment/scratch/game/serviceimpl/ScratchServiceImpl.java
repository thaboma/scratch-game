package ae.cyberspeed.assignment.scratch.game.serviceimpl;

import ae.cyberspeed.assignment.scratch.game.client.ScratchGameClient;
import ae.cyberspeed.assignment.scratch.game.dto.GameResult;
import ae.cyberspeed.assignment.scratch.game.dto.ScratchGameConfig;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.input.MatrixSymbol;
import ae.cyberspeed.assignment.scratch.game.service.ScratchService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static ae.cyberspeed.assignment.scratch.game.dto.helper.ScratchGameHelpers.filterUnwantedSymbolsCounts;
import static ae.cyberspeed.assignment.scratch.game.dto.helper.ScratchGameHelpers.getAmalgamatedWinnings;

public class ScratchServiceImpl implements ScratchService {

    private final ScratchGameConfig scratchGameConfig;

    public ScratchServiceImpl(ScratchGameClient scratchGameClient) {
        this.scratchGameConfig = scratchGameClient.getScratchGameConfig();
    }

    @Override
    public GameResult computeResult(MatrixSymbol inputMatrix, double betAmount) {
        GameResult result = new GameResult();
        result.setMatrix(inputMatrix);

        Map<String, List<String>> amalgamatedWinnings = getAmalgamatedWinnings(scratchGameConfig, inputMatrix);
        List<String> standardSymbols = Arrays.asList("A", "B", "C", "D", "E", "F");
        Map<String, List<String>> standardWinnings = filterUnwantedSymbolsCounts(amalgamatedWinnings);
        Map<String, List<String>> bonusWinnings = amalgamatedWinnings.entrySet().stream().filter(e -> !standardSymbols.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Double reward = calculateReward(standardSymbols, amalgamatedWinnings, betAmount);

        result.setReward(reward);
        if (reward != 0D) {
            result.setApplied_winning_combinations(standardWinnings);
            result.setApplied_bonus_symbol(bonusWinnings.keySet().stream().map(k -> k.replace("_", "x")).toList());
        }
        return result;
    }

    @Override
    public Double calculateReward(List<String> standardSymbols, Map<String, List<String>> winnings, double betAmount) {
        Map<String, List<String>> stdWins = winnings.entrySet().stream().filter(e -> standardSymbols.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        AtomicReference<Double> reward = new AtomicReference<>(0D);
        var winCombinations = scratchGameConfig.getWin_combinations();
        var symbolExtraRewardsMap = scratchGameConfig.getSymbols().getSymbolExtraMap();
        var symbolMultipierMap = scratchGameConfig.getSymbols().getSymbolMultipierMap();
        var rewardMultiplierWinMap = winCombinations.getRewardMultiplier();
        rewardMultiplierWinMap.putAll(symbolMultipierMap);

        var relevantHits = filterUnwantedSymbolsCounts(stdWins);

        if (relevantHits.isEmpty()) {
            return reward.get();
        }

        relevantHits.keySet().stream().forEach(k -> {
            List<String> winStrings = new ArrayList<>();
            winStrings.add(k);
            winStrings.addAll(stdWins.get(k));

            var amounts = winStrings.parallelStream().map(str -> rewardMultiplierWinMap.get(str.toLowerCase())).filter(v -> v != null)
                    .reduce((l1, l2) -> {
                        l1 = l1 * l2;
                        return l1;
                    }).orElse(0D);
            reward.updateAndGet(v -> v + amounts * betAmount);
        });

        Map<String, List<String>> bonusWins = winnings.entrySet().stream().filter(e -> !e.getKey().equalsIgnoreCase("miss")).filter(e -> !standardSymbols.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return bonusWins.keySet().stream().map(k ->
                k.contains("+") ? reward.get() + symbolExtraRewardsMap.get(k).doubleValue() :
                        reward.get() * symbolExtraRewardsMap.get(k).doubleValue()
        ).findFirst().orElse(reward.get());
    }
}
