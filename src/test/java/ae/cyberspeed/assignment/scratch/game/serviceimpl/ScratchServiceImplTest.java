package ae.cyberspeed.assignment.scratch.game.serviceimpl;


import ae.cyberspeed.assignment.scratch.game.client.ScratchGameClientTest;
import ae.cyberspeed.assignment.scratch.game.dto.GameResult;
import ae.cyberspeed.assignment.scratch.game.dto.ScratchGameConfig;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.input.MatrixSymbol;
import ae.cyberspeed.assignment.scratch.game.service.ScratchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ae.cyberspeed.assignment.scratch.game.dto.helper.ScratchGameHelpers.getAmalgamatedWinnings;
import static ae.cyberspeed.assignment.scratch.game.util.ScratchGameUtils.symbolsProbabilityGenerator;
import static org.junit.jupiter.api.Assertions.*;

public class ScratchServiceImplTest {

    ScratchGameClientTest gameClientTest;
    ScratchService scratchService;
    ScratchGameConfig config;

    @BeforeEach
    void setUp() {
        config = ScratchGameClientTest.config;
        gameClientTest = new ScratchGameClientTest();
        scratchService = new ScratchServiceImpl(gameClientTest.getScratchGameClient());
    }

    @Test
    void computeResultForWinningsWith1000Extra() {

        MatrixSymbol matrixSymbol = buildInputMatrix();
        GameResult result = scratchService.computeResult(matrixSymbol, 100);
        double reward = result.getReward();
        System.out.println("Result is \r\n" + result);
        assertEquals(3600.0D, reward, String.format("expected reward is 3600.0D and actual reward is %s", reward));
    }

    @Test
    void computeResultForWinningsWith500Extra() {
        MatrixSymbol matrixSymbol = buildInputMatrix("+500");
        GameResult result = scratchService.computeResult(matrixSymbol, 100);
        double reward = result.getReward();
        System.out.println("Result is \r\n" + result);
        assertEquals(3100.0D, reward, String.format("expected reward is 3100.0D and actual reward is %s", reward));

    }

    @Test
    void computeResultForWinningsWithX10() {
        MatrixSymbol matrixSymbol = buildInputMatrix("10x");
        GameResult result = scratchService.computeResult(matrixSymbol, 100);
        double reward = result.getReward();
        double expected = 26000.0D;
        System.out.println("Result is \r\n" + result);
        assertEquals(expected, reward, String.format("expected reward is %s and actual reward is %s", expected, reward));

    }

    @Test
    void computeResultForWinningsWithX5() {
        MatrixSymbol matrixSymbol = buildInputMatrix("5x");
        GameResult result = scratchService.computeResult(matrixSymbol, 100);
        double reward = result.getReward();
        double expected = 13000.0D;
        System.out.println("Result is \r\n" + result);
        assertEquals(expected, reward, String.format("expected reward is %s and actual reward is %s", expected, reward));

    }

    @Test
    void computeResultForLoss() {
        MatrixSymbol matrixSymbol = buildInputMatrixForLoss("miss");
        GameResult result = scratchService.computeResult(matrixSymbol, 100);
        double reward = result.getReward();
        double expected = 0D;
        System.out.println("Result is \r\n" + result);
        assertEquals(expected, reward, String.format("expected reward is %s and actual reward is %s", expected, reward));
    }

    @Test
    void computeResultForGivenLossBoard() {
        MatrixSymbol matrixSymbol = buildInputMatrixForGivenLossBoard();
        GameResult result = scratchService.computeResult(matrixSymbol, 100);
        double reward = result.getReward();
        double expected = 0D;
        System.out.println("Result is \r\n" + result);
        assertEquals(expected, reward, String.format("expected reward is %s and actual reward is %s", expected, reward));
    }

    @Test
    void computeResultForGivenWinBoard() {
        MatrixSymbol matrixSymbol = buildInputMatrixForGivenWonBoard();
        GameResult result = scratchService.computeResult(matrixSymbol, 100);
        double reward = result.getReward();
        double expected = 3000;
        System.out.println("Result is \r\n" + result);
        assertEquals(expected, reward, String.format("expected reward is %s and actual reward is %s", expected, reward));
    }

    @Test
    void computeResultSpecialBoard() {
        MatrixSymbol matrixSymbol = buildSpecialBoard("miss");
        GameResult result = scratchService.computeResult(matrixSymbol, 100);
        double reward = result.getReward();
        double expected = 1500.0D;
        System.out.println("Result is \r\n" + result);
        assertEquals(expected, reward, String.format("expected reward is %s and actual reward is %s", expected, reward));
    }

    @Test
    void compute_result_for_a_randomly_created_board() {
        MatrixSymbol matrixSymbol = symbolsProbabilityGenerator(3, 3);
        assertNotNull(matrixSymbol);
        GameResult result = scratchService.computeResult(matrixSymbol, 100);
        System.out.println("Result is \r\n" + result);
        assertTrue(result.getReward() >= 0D);
    }


    @Test
    void calculateReward() {
        ScratchGameConfig config = ScratchGameClientTest.config;
        assertNotNull(config);
        assertNotNull(scratchService);
        MatrixSymbol matrixSymbol = buildInputMatrix();

        Map<String, List<String>> amalgamatedWinnings = getAmalgamatedWinnings(config, matrixSymbol);
        List<String> standardSymbols = Arrays.asList("A", "B", "C", "D", "E", "F");
        Double betAmount = 100D;
        Double reward = scratchService.calculateReward(standardSymbols, amalgamatedWinnings, betAmount);
        assertEquals(3600.0D, reward);
    }


    private MatrixSymbol buildInputMatrix() {
        return buildInputMatrix("+1000");
    }

    private MatrixSymbol buildInputMatrix(String bunus) {
        MatrixSymbol matrixSymbol = new MatrixSymbol(3, 3);

        // row 1
        matrixSymbol.updateMatrixSymbol(0, 0, "A");
        matrixSymbol.updateMatrixSymbol(0, 1, "A");
        matrixSymbol.updateMatrixSymbol(0, 2, "B");

        // row 2
        matrixSymbol.updateMatrixSymbol(1, 0, "A");
        matrixSymbol.updateMatrixSymbol(1, 1, "bonus", bunus);
        matrixSymbol.updateMatrixSymbol(1, 2, "B");

        // row 3
        matrixSymbol.updateMatrixSymbol(2, 0, "A");
        matrixSymbol.updateMatrixSymbol(2, 1, "A");
        matrixSymbol.updateMatrixSymbol(2, 2, "B");

        return matrixSymbol;
    }

    private MatrixSymbol buildInputMatrixForLoss(String bunus) {
        MatrixSymbol matrixSymbol = new MatrixSymbol(3, 3);

        // row 1
        matrixSymbol.updateMatrixSymbol(0, 0, "F");
        matrixSymbol.updateMatrixSymbol(0, 1, "C");
        matrixSymbol.updateMatrixSymbol(0, 2, "B");

        // row 2
        matrixSymbol.updateMatrixSymbol(1, 0, "bonus", bunus);
        matrixSymbol.updateMatrixSymbol(1, 1, "A");
        matrixSymbol.updateMatrixSymbol(1, 2, "B");

        // row 3
        matrixSymbol.updateMatrixSymbol(2, 0, "A");
        matrixSymbol.updateMatrixSymbol(2, 1, "D");
        matrixSymbol.updateMatrixSymbol(2, 2, "E");

        return matrixSymbol;
    }

    private MatrixSymbol buildInputMatrixForGivenWonBoard() {
        MatrixSymbol matrixSymbol = new MatrixSymbol(3, 3);

        // row 1
        matrixSymbol.updateMatrixSymbol(0, 0, "A");
        matrixSymbol.updateMatrixSymbol(0, 1, "B");
        matrixSymbol.updateMatrixSymbol(0, 2, "C");

        // row 2
        matrixSymbol.updateMatrixSymbol(1, 0, "E");
        matrixSymbol.updateMatrixSymbol(1, 1, "B");
        matrixSymbol.updateMatrixSymbol(1, 2, "bonus", "10x");

        // row 3
        matrixSymbol.updateMatrixSymbol(2, 0, "F");
        matrixSymbol.updateMatrixSymbol(2, 1, "D");
        matrixSymbol.updateMatrixSymbol(2, 2, "B");

        return matrixSymbol;
    }

    private MatrixSymbol buildInputMatrixForGivenLossBoard() {
        MatrixSymbol matrixSymbol = new MatrixSymbol(3, 3);

        // row 1
        matrixSymbol.updateMatrixSymbol(0, 0, "A");
        matrixSymbol.updateMatrixSymbol(0, 1, "B");
        matrixSymbol.updateMatrixSymbol(0, 2, "C");

        // row 2
        matrixSymbol.updateMatrixSymbol(1, 0, "E");
        matrixSymbol.updateMatrixSymbol(1, 1, "B");
        matrixSymbol.updateMatrixSymbol(1, 2, "bonus", "5x");

        // row 3
        matrixSymbol.updateMatrixSymbol(2, 0, "F");
        matrixSymbol.updateMatrixSymbol(2, 1, "D");
        matrixSymbol.updateMatrixSymbol(2, 2, "C");

        return matrixSymbol;
    }


    private MatrixSymbol buildSpecialBoard(String bonus) {
        MatrixSymbol matrixSymbol = new MatrixSymbol(3, 3);

        // row 1
        matrixSymbol.updateMatrixSymbol(0, 0, "B");
        matrixSymbol.updateMatrixSymbol(0, 1, "bonus",bonus);
        matrixSymbol.updateMatrixSymbol(0, 2, "A");

        // row 2
        matrixSymbol.updateMatrixSymbol(1, 0, "C");
        matrixSymbol.updateMatrixSymbol(1, 1, "F");
        matrixSymbol.updateMatrixSymbol(1, 2, "A");

        // row 3
        matrixSymbol.updateMatrixSymbol(2, 0, "F");
        matrixSymbol.updateMatrixSymbol(2, 1, "A");
        matrixSymbol.updateMatrixSymbol(2, 2, "A");

        return matrixSymbol;
    }
}