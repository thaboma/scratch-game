package ae.cyberspeed.assignment.scratch.game.dto;

import ae.cyberspeed.assignment.scratch.game.dto.symbols.same.SameSymbolNTimes;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.same.SameSymbolOrientation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinCombinations {
    @JsonProperty("same_symbol_3_times")
    public SameSymbolNTimes same_symbol_3_times;

    @JsonProperty("same_symbol_4_times")
    public SameSymbolNTimes same_symbol_4_times;

    @JsonProperty("same_symbol_5_times")
    public SameSymbolNTimes same_symbol_5_times;

    @JsonProperty("same_symbol_6_times")
    public SameSymbolNTimes same_symbol_6_times;

    @JsonProperty("same_symbol_7_times")
    public SameSymbolNTimes same_symbol_7_times;

    @JsonProperty("same_symbol_8_times")
    public SameSymbolNTimes same_symbol_8_times;

    @JsonProperty("same_symbol_9_times")
    public SameSymbolNTimes same_symbol_9_times;

    @JsonProperty("same_symbols_horizontally")
    public SameSymbolOrientation same_symbols_horizontally;

    @JsonProperty("same_symbols_vertically")
    public SameSymbolOrientation same_symbols_vertically;

    @JsonProperty("same_symbols_diagonally_left_to_right")
    public SameSymbolOrientation same_symbols_diagonally_left_to_right;

    @JsonProperty("same_symbols_diagonally_right_to_left")
    public SameSymbolOrientation same_symbols_diagonally_right_to_left;

    public SameSymbolNTimes getSame_symbol_3_times() {
        return same_symbol_3_times;
    }

    public void setSame_symbol_3_times(SameSymbolNTimes same_symbol_3_times) {
        this.same_symbol_3_times = same_symbol_3_times;
    }

    public SameSymbolNTimes getSame_symbol_4_times() {
        return same_symbol_4_times;
    }

    public void setSame_symbol_4_times(SameSymbolNTimes same_symbol_4_times) {
        this.same_symbol_4_times = same_symbol_4_times;
    }

    public SameSymbolNTimes getSame_symbol_5_times() {
        return same_symbol_5_times;
    }

    public void setSame_symbol_5_times(SameSymbolNTimes same_symbol_5_times) {
        this.same_symbol_5_times = same_symbol_5_times;
    }

    public SameSymbolNTimes getSame_symbol_6_times() {
        return same_symbol_6_times;
    }

    public void setSame_symbol_6_times(SameSymbolNTimes same_symbol_6_times) {
        this.same_symbol_6_times = same_symbol_6_times;
    }

    public SameSymbolNTimes getSame_symbol_7_times() {
        return same_symbol_7_times;
    }

    public void setSame_symbol_7_times(SameSymbolNTimes same_symbol_7_times) {
        this.same_symbol_7_times = same_symbol_7_times;
    }

    public SameSymbolNTimes getSame_symbol_8_times() {
        return same_symbol_8_times;
    }

    public void setSame_symbol_8_times(SameSymbolNTimes same_symbol_8_times) {
        this.same_symbol_8_times = same_symbol_8_times;
    }

    public SameSymbolNTimes getSame_symbol_9_times() {
        return same_symbol_9_times;
    }

    public void setSame_symbol_9_times(SameSymbolNTimes same_symbol_9_times) {
        this.same_symbol_9_times = same_symbol_9_times;
    }

    public SameSymbolOrientation getSame_symbols_horizontally() {
        return same_symbols_horizontally;
    }

    public void setSame_symbols_horizontally(SameSymbolOrientation same_symbols_horizontally) {
        this.same_symbols_horizontally = same_symbols_horizontally;
    }

    public SameSymbolOrientation getSame_symbols_vertically() {
        return same_symbols_vertically;
    }

    public void setSame_symbols_vertically(SameSymbolOrientation same_symbols_vertically) {
        this.same_symbols_vertically = same_symbols_vertically;
    }

    public SameSymbolOrientation getSame_symbols_diagonally_left_to_right() {
        return same_symbols_diagonally_left_to_right;
    }

    public void setSame_symbols_diagonally_left_to_right(SameSymbolOrientation same_symbols_diagonally_left_to_right) {
        this.same_symbols_diagonally_left_to_right = same_symbols_diagonally_left_to_right;
    }

    public SameSymbolOrientation getSame_symbols_diagonally_right_to_left() {
        return same_symbols_diagonally_right_to_left;
    }

    public void setSame_symbols_diagonally_right_to_left(SameSymbolOrientation same_symbols_diagonally_right_to_left) {
        this.same_symbols_diagonally_right_to_left = same_symbols_diagonally_right_to_left;
    }

    public Map<String, List<List<String>>> getCoveredAreas() {
        Map<String, List<List<String>>> areas = new HashMap<>();
        areas.put("same_symbols_horizontally", getSame_symbols_horizontally().covered_areas);
        areas.put("same_symbols_vertically", getSame_symbols_vertically().covered_areas);
        areas.put("same_symbols_diagonally_left_to_right", getSame_symbols_diagonally_left_to_right().covered_areas);
        areas.put("same_symbols_diagonally_right_to_left", getSame_symbols_diagonally_right_to_left().covered_areas);
        return areas;
    }

    public Map<String, Double> getRewardMultiplier() {
        Map<String, Double> rewardMultiplierMap = new HashMap<>();
        rewardMultiplierMap.put("same_symbol_3_times", getSame_symbol_3_times().getReward_multiplier());
        rewardMultiplierMap.put("same_symbol_4_times", getSame_symbol_4_times().getReward_multiplier());
        rewardMultiplierMap.put("same_symbol_5_times", getSame_symbol_5_times().getReward_multiplier());
        rewardMultiplierMap.put("same_symbol_6_times", getSame_symbol_6_times().getReward_multiplier());
        rewardMultiplierMap.put("same_symbol_7_times", getSame_symbol_7_times().getReward_multiplier());
        rewardMultiplierMap.put("same_symbol_8_times", getSame_symbol_8_times().getReward_multiplier());
        rewardMultiplierMap.put("same_symbol_9_times", getSame_symbol_9_times().getReward_multiplier());
        rewardMultiplierMap.put("same_symbols_horizontally", getSame_symbols_horizontally().getReward_multiplier());
        rewardMultiplierMap.put("same_symbols_vertically", getSame_symbols_vertically().getReward_multiplier());
        rewardMultiplierMap.put("same_symbols_diagonally_left_to_right", getSame_symbols_diagonally_left_to_right().getReward_multiplier());
        rewardMultiplierMap.put("same_symbols_diagonally_right_to_left", getSame_symbols_diagonally_right_to_left().getReward_multiplier());

        return rewardMultiplierMap;
    }
}
