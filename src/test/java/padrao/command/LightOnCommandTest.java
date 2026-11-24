package padrao.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LightOnCommandTest {

    @Test
    void constructorRejectsNullLight() {
        assertThrows(NullPointerException.class, () -> new LightOnCommand(null));
    }

    @Test
    void executeTurnsLightOn() {
        Light light = new Light();
        light.turnOff();
        new LightOnCommand(light).execute();
        assertTrue(light.isOn());
    }

    @Test
    void undoRevertsLight() {
        Light light = new Light();
        light.turnOn();
        new LightOnCommand(light).undo();
        assertFalse(light.isOn());
    }
}
