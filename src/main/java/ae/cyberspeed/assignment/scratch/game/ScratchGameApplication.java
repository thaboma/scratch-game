package ae.cyberspeed.assignment.scratch.game;

import ae.cyberspeed.assignment.scratch.game.client.ScratchGameClient;
import ae.cyberspeed.assignment.scratch.game.dto.GameResult;
import ae.cyberspeed.assignment.scratch.game.dto.ScratchGameConfig;
import ae.cyberspeed.assignment.scratch.game.dto.symbols.input.MatrixSymbol;
import ae.cyberspeed.assignment.scratch.game.service.ScratchService;
import ae.cyberspeed.assignment.scratch.game.serviceimpl.ScratchServiceImpl;

import java.io.File;

import static ae.cyberspeed.assignment.scratch.game.util.ScratchGameUtils.symbolsProbabilityGenerator;

public class ScratchGameApplication {

    private static ScratchGameClient scratchGameClient;
    private static ScratchService scratchService;

    public static void main(String[] args) {

        //  java -jar cyberspeed-assignment-1.0-RELEASE-jar-with-dependencies.jar  --config config.json --betting-amount 100
        for (int i = 0; i < args.length; i++) {
            System.out.printf("args[%s] is %s %n", i, args[i]);
        }

        try {
            String file = args[1];
            File f = new File(file);
            System.out.printf("File exists %s does it exits ? %s%n", f.getAbsolutePath(), f.exists());
            scratchGameClient = new ScratchGameClient(file);
            ScratchGameConfig config = scratchGameClient.getScratchGameConfig();

            //Could use NxN matrix depending on the config used.
            int width = config.getRows();
            int height = config.getColumns();

            scratchService = new ScratchServiceImpl(new ScratchGameClient(file));
            MatrixSymbol matrixSymbol = symbolsProbabilityGenerator(width, height);
            GameResult result = scratchService.computeResult(matrixSymbol, 100);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
