package ae.cyberspeed.assignment.scratch.game.service;

import ae.cyberspeed.assignment.scratch.game.dto.GameResult;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.input.MatrixSymbol;

import java.util.List;
import java.util.Map;

public interface ScratchService {
    GameResult computeResult(MatrixSymbol inputMatrix, double betAmount);

    Double calculateReward(List<String> standardSymbols, Map<String, List<String>> winnings, double betAmount);
}
