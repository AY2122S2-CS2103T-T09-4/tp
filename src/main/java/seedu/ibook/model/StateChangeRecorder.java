package seedu.ibook.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * A class that keeps records of changes to iBook.
 */
public class StateChangeRecorder {

    /**
     * A class that represents a single state change.
     */
    private static class StateChange {
        private final List<Consumer<IBook>> forwardActionList;
        private final List<Consumer<IBook>> reverseActionList;

        public StateChange(List<Consumer<IBook>> forwardActionList, List<Consumer<IBook>> reverseActionList) {
            this.forwardActionList = forwardActionList;
            this.reverseActionList = reverseActionList;
        }
    }

    private static final int INVALID_STATE_CHANGE = -1;
    private static final int LIST_OFFSET = 1;

    private final List<StateChange> stateChanges;
    // A variable acting as a pointer to most recent state change.
    private int curStateChange;

    private List<Consumer<IBook>> forwardActionList;
    private List<Consumer<IBook>> reverseActionList;

    /**
     * Class constructor.
     */
    public StateChangeRecorder() {
        stateChanges = new ArrayList<>();
        curStateChange = INVALID_STATE_CHANGE;

        getNewWorkspace();
    }

    public void addForwardAction(Consumer<IBook> forwardAction) {
        forwardActionList.add(forwardAction);
    }

    public void addReverseAction(Consumer<IBook> reverseAction) {
        reverseActionList.add(reverseAction);
    }

    public List<Consumer<IBook>> getCurrentForwardActionList() {
        return stateChanges.get(curStateChange).forwardActionList;
    }

    public List<Consumer<IBook>> getCurrentReverseActionList() {
        return stateChanges.get(curStateChange).reverseActionList;
    }

    /**
     * Save the changes made to iBook.
     */
    public void saveStateChange() {
        if (curStateChange != stateChanges.size() - LIST_OFFSET) {
            stateChanges.subList(curStateChange + LIST_OFFSET, stateChanges.size() - LIST_OFFSET).clear();
        }

        Collections.reverse(reverseActionList);
        StateChange stateChange = new StateChange(forwardActionList, reverseActionList);
        stateChanges.add(stateChange);
        curStateChange++;

        getNewWorkspace();
    }

    /**
     * Checks if there is any older changes.
     * @return true if there is one or more older changes.
     */
    public boolean hasOlderStateChange() {
        return curStateChange != INVALID_STATE_CHANGE;
    }

    /**
     * Checks if there is any newer changes.
     * @return true if there is one or more newer changes.
     */
    public boolean hasNewerStateChange() {
        return curStateChange != stateChanges.size() - LIST_OFFSET;
    }

    /**
     * Reverts most recently changes to IBook.
     */
    public void revertStateChange() {
        curStateChange--;;
    }

    /**
     * Restores most recently reverted changes to IBook.
     */
    public void restoreStateChange() {
        curStateChange++;
    }

    private void getNewWorkspace() {
        forwardActionList = new ArrayList<>();
        reverseActionList = new ArrayList<>();
    }

}