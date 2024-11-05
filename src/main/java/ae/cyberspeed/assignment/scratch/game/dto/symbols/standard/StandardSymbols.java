package ae.cyberspeed.assignment.scratch.game.dto.symbols.standard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardSymbols {
    @JsonProperty("reward_multiplier")
    public double reward_multiplier;

    @JsonProperty("type")
    public String type;

    public double getReward_multiplier() {
        return reward_multiplier;
    }

    public void setReward_multiplier(double reward_multiplier) {
        this.reward_multiplier = reward_multiplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
