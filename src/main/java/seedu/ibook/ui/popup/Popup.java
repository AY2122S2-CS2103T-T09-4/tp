package seedu.ibook.ui.popup;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiPart;

/**
 * Abstract Class representing the skeleton of a popup.
 */
public abstract class Popup extends UiPart<Stage> {

    @FXML
    private Text error;

    private final MainWindow.CommandExecutor commandExecutor;

    /**
     * Initialize a new popup window with a {@code FXML} file path
     * and a {@code CommandExecutor}.
     *
     * @param filePath FXML filepath.
     * @param commandExecutor Function to execute command.
     */
    Popup(String filePath, MainWindow.CommandExecutor commandExecutor) {
        super(filePath, new Stage());
        this.commandExecutor = commandExecutor;
    }

    /**
     * Show the popup window.
     */
    public void show() {
        getRoot().show();
        error.setText("");
    }

    /**
     * Hide the popup window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Check if the popup is showing.
     *
     * @return A boolean indicating if the popup is showing.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focus the popup window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Show feedback to user.
     *
     * @param feedbackToUser The feedback string.
     */
    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        error.setText(feedbackToUser);
    }

    /**
     * Execute the commandText with the {@code commandExecutor}.
     *
     * @param commandText The commandText.
     */
    void execute(String commandText) {
        commandExecutor.execute(commandText);
    }

}