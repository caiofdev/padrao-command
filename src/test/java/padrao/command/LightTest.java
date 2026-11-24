package padrao.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LightTest {

    @Test
    void isOnReturnsFalseByDefault() {
        assertFalse(new Light().isOn());
    }

    @Test
    void turnOnSetsLightOn() {
        Light light = new Light();
        light.turnOn();
        assertTrue(light.isOn());
    }

    @Test
    void turnOffSetsLightOff() {
        Light light = new Light();
        light.turnOn();
        light.turnOff();
        assertFalse(light.isOn());
    }
}
