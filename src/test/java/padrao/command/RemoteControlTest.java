package padrao.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RemoteControlTest {

    private Light light;
    private RemoteControl remote;

    @BeforeEach
    void setUp() {
        light = new Light();
        remote = new RemoteControl(2);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void constructorRejectsNonPositiveSlots(int slots) {
        assertThrows(IllegalArgumentException.class, () -> new RemoteControl(slots));
    }

    @Test
    void constructorAcceptsPositiveSlots() {
        assertDoesNotThrow(() -> new RemoteControl(1));
    }

    @Test
    void setCommandRejectsNullCommand() {
        assertThrows(IllegalArgumentException.class, () -> remote.setCommand(0, null));
    }

    @Test
    void setCommandStoresCommandInSlot() {
        remote.setCommand(0, new LightOnCommand(light));
        remote.pressButton(0);
        assertTrue(light.isOn());
    }

    @Test
    void setCommandRejectsNegativeSlot() {
        assertThrows(IndexOutOfBoundsException.class, () -> remote.setCommand(-1, new NoCommand()));
    }

    @Test
    void setCommandRejectsSlotAboveLimit() {
        assertThrows(IndexOutOfBoundsException.class, () -> remote.setCommand(2, new NoCommand()));
    }

    @Test
    void pressButtonRejectsNegativeSlot() {
        assertThrows(IndexOutOfBoundsException.class, () -> remote.pressButton(-1));
    }

    @Test
    void pressButtonRejectsSlotAboveLimit() {
        assertThrows(IndexOutOfBoundsException.class, () -> remote.pressButton(2));
    }

    @Test
    void pressButtonOnEmptySlotExecutesNoCommand() {
        remote.pressButton(1);
        assertFalse(light.isOn());
        assertEquals(1, remote.historySize());
    }

    @Test
    void pressButtonExecutesCommandAndRecordsHistory() {
        remote.setCommand(0, new LightOnCommand(light));
        remote.setCommand(1, new LightOffCommand(light));
        remote.pressButton(0);
        remote.pressButton(1);
        assertFalse(light.isOn());
        assertEquals(2, remote.historySize());
    }

    @Test
    void undoLastReturnsFalseWhenHistoryIsEmpty() {
        assertFalse(remote.undoLast());
    }

    @Test
    void undoLastRevertsMostRecentCommand() {
        remote.setCommand(0, new LightOnCommand(light));
        remote.pressButton(0);
        assertTrue(remote.undoLast());
        assertFalse(light.isOn());
        assertEquals(0, remote.historySize());
    }

    @Test
    void historySizeStartsAtZero() {
        assertEquals(0, remote.historySize());
    }
}
