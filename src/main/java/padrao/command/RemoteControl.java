package padrao.command;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class RemoteControl {

    private final Command[] commands;
    private final Deque<Command> history = new ArrayDeque<>();

    public RemoteControl(int slots) {
        if (slots <= 0) {
            throw new IllegalArgumentException("slots must be greater than zero");
        }
        commands = new Command[slots];
        Arrays.fill(commands, new NoCommand());
    }

    public void setCommand(int slot, Command command) {
        validateSlot(slot);
        if (command == null) {
            throw new IllegalArgumentException("command must not be null");
        }
        commands[slot] = command;
    }

    public void pressButton(int slot) {
        validateSlot(slot);
        Command command = commands[slot];
        command.execute();
        history.push(command);
    }

    public boolean undoLast() {
        if (history.isEmpty()) {
            return false;
        }
        history.pop().undo();
        return true;
    }

    public int historySize() {
        return history.size();
    }

    private void validateSlot(int slot) {
        if (slot < 0 || slot >= commands.length) {
            throw new IndexOutOfBoundsException("invalid slot: " + slot);
        }
    }
}
