package ae.cyberspeed.assignment.scratch.game.dto.symbols.same;


public class SameSymbolNTimes {
    public double reward_multiplier;
    public String when;
    public double count;
    public String group;

    public double getReward_multiplier() {
        return reward_multiplier;
    }

    public void setReward_multiplier(double reward_multiplier) {
        this.reward_multiplier = reward_multiplier;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
