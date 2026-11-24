package padrao.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LightOffCommandTest {

    @Test
    void constructorRejectsNullLight() {
        assertThrows(NullPointerException.class, () -> new LightOffCommand(null));
    }

    @Test
    void executeTurnsLightOff() {
        Light light = new Light();
        light.turnOn();
        new LightOffCommand(light).execute();
        assertFalse(light.isOn());
    }

    @Test
    void undoRevertsLight() {
        Light light = new Light();
        light.turnOff();
        new LightOffCommand(light).undo();
        assertTrue(light.isOn());
    }
}
