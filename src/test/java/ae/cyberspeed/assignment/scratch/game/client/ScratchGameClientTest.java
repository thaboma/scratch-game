package ae.cyberspeed.assignment.scratch.game.client;

import ae.cyberspeed.assignment.scratch.game.dto.ScratchGameConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScratchGameClientTest {

    public static ScratchGameConfig config;
    private ScratchGameClient scratchGameClient;

    public ScratchGameClientTest() {
        initilise();
    }

    @BeforeEach
    void setUp() {
        initilise();
    }

    private void initilise() {
        String file = "../config.json";
        File f = new File(file);
        f.getPath();
        System.out.println(f.getAbsolutePath());
        try {
            scratchGameClient = new ScratchGameClient(file);
            config = scratchGameClient.getScratchGameConfig();
            System.out.println(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getScratchGameConfig() {
        assertNotNull(config);
    }

    public ScratchGameClient getScratchGameClient() {
        return scratchGameClient;
    }
}