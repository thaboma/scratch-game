package ae.cyberspeed.assignment.scratch.game.dto;

import ae.cyberspeed.assignment.scratch.game.dto.symbols.input.MatrixSymbol;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class GameResult {
    @JsonProperty("matrix")
    public MatrixSymbol matrix;
    @JsonProperty("reward")
    public double reward;

    @JsonProperty("applied_winning_combinations")
    public Map<String, List<String>> applied_winning_combinations;

    @JsonProperty("applied_bonus_symbol")
    public List<String> applied_bonus_symbol;

    public MatrixSymbol getMatrix() {
        return matrix;
    }

    public void setMatrix(MatrixSymbol matrix) {
        this.matrix = matrix;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public Map<String, List<String>> getApplied_winning_combinations() {
        return applied_winning_combinations;
    }

    public void setApplied_winning_combinations(Map<String, List<String>> applied_winning_combinations) {
        this.applied_winning_combinations = applied_winning_combinations;
    }

    public List<String> getApplied_bonus_symbol() {
        return applied_bonus_symbol;
    }

    public void setApplied_bonus_symbol(List<String> applied_bonus_symbol) {
        this.applied_bonus_symbol = applied_bonus_symbol;
    }

    @Override
    public String toString() {
        return "GameResult{\r\n\r\n" +
                "matrix=" + matrix +
                "\r\n, reward=" + reward +
                "\r\n\t, applied_winning_combinations=\r\n\t" + prettyPrintWinningCombinations() +
                "\r\n\t, applied_bonus_symbol=" + (applied_bonus_symbol == null ? "[MISS]" : applied_bonus_symbol) +
                "\r\n\n}";
    }

    private String prettyPrintWinningCombinations() {
        return applied_winning_combinations == null ? "[ ]" : applied_winning_combinations.entrySet().stream().map(e -> String.format("\t%s\n\t", e.toString())).toList().toString();
    }
}
