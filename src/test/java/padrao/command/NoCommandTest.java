package padrao.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class NoCommandTest {

    @Test
    void executeDoesNothing() {
        assertDoesNotThrow(() -> new NoCommand().execute());
    }

    @Test
    void undoDoesNothing() {
        assertDoesNotThrow(() -> new NoCommand().undo());
    }
}
