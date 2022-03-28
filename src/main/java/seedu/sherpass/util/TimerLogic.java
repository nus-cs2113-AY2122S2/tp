package seedu.sherpass.util;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.exception.InvalidTimeException;

import seedu.sherpass.util.parser.TaskParser;
import seedu.sherpass.util.parser.TimerParser;

import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_INVALID_TIMER_INPUT_MESSAGE;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TimerLogic implements WindowListener {

    private static Ui ui;
    private static Timer timer;
    private static TaskList taskList;
    protected boolean isTimerInitialised = false;
    private final JFrame jframe;
    private final JLabel jlabel;
    private final ActionListener actionListenerPause = actionEvent -> {
        callPauseTimer();
        ui.showLine();
    };
    private final ActionListener actionListenerResume = actionEvent -> {
        callResumeTimer();
        ui.showLine();
    };
    private final ActionListener actionListenerStop = actionEvent -> {
        callStopTimer();
        ui.showLine();
    };

    private void setWindowParameters() {
        jframe.setLayout(new BorderLayout());
        jframe.setBounds(500, 300, 300, 100);
        jframe.add(jlabel, BorderLayout.NORTH);
        jframe.addWindowListener(this);
        JToggleButton pauseButton = new JToggleButton("Pause");
        pauseButton.addActionListener(actionListenerPause);
        jframe.add(pauseButton, BorderLayout.WEST);
        JToggleButton resumeButton = new JToggleButton("Resume");
        resumeButton.addActionListener(actionListenerResume);
        jframe.add(resumeButton, BorderLayout.CENTER);
        JToggleButton stopButton = new JToggleButton("stop");
        stopButton.addActionListener(actionListenerStop);
        jframe.add(stopButton, BorderLayout.EAST);
    }

    /**
     * Creates a constructor for TimerLogic.
     *
     * @param ui       UI
     * @param taskList taskList
     */
    public TimerLogic(TaskList taskList, Ui ui) {
        TimerLogic.taskList = taskList;
        TimerLogic.ui = ui;
        jframe = new JFrame();
        jlabel = new JLabel(EMPTY_STRING, SwingConstants.CENTER);
        setWindowParameters();
    }

    /**
     * Marks a task as done, as specified in parsedInput.
     *
     * @param storage     Storage.
     * @param parsedInput parsedInput.
     */
    public void markTask(Storage storage, String[] parsedInput) {
        if (isTimerPausedOrStopped()) {
            executeMark(storage, parsedInput);
        } else {
            ui.showToUser("You can't mark a task as done while timer is running!");
        }
    }

    private void executeMark(Storage storage, String[] parsedInput) {
        Command c = TaskParser.prepareMarkOrUnmark(parsedInput, MarkCommand.COMMAND_WORD, taskList);
        if (c != null) {
            c.execute(taskList, ui, storage);
            printAvailableCommands();
        }
    }

    private void printAvailableCommands() {
        if (!isTimerInitialised) {
            ui.showToUser("Would you like to start another timer, mark a task as done, "
                    + "or leave the study session?");
        } else {
            ui.showToUser("Would you like to resume the timer, mark a task as done, "
                    + "or leave the study session?");
        }
    }

    public void showTasks(Storage storage, String[] parsedInput) {
        if (!isTimerInitialised) {
            executeShow(storage, parsedInput);
        } else {
            ui.showToUser("You can't show tasks while timer is running!");
        }
    }

    private void executeShow(Storage storage, String[] parsedInput) {
        Command c = TaskParser.prepareShow(parsedInput);
        if (c != null) {
            c.execute(taskList, ui, storage);
            printAvailableCommands();
        }
    }

    /**
     * Creates a thread using timer.start() to start the timer with the user's specified duration.
     *
     * @param parsedInput Parsed input of the user
     */
    public void callStartTimer(String[] parsedInput) {
        if (isTimerInitialised) {
            ui.showToUser("You already have a timer running!");
            return;
        }
        try {
            callResetTimer(parsedInput);
            if (timer instanceof Countdown) {
                int duration = TimerParser.parseTimerInput(parsedInput);
                assert (duration > 0);
                ((Countdown) timer).setDuration(duration);
            }
            isTimerInitialised = true;
            timer.start();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | InvalidTimeException e) {
            ui.showToUser(ERROR_INVALID_TIMER_INPUT_MESSAGE);
        }
    }

    public void callPauseTimer() {
        if (!isTimerInitialised) {
            ui.showToUser("There is no timer running.");
            return;
        }
        if (timer.isTimerPaused()) {
            ui.showToUser("The timer is already paused!");
            return;
        }
        if (timer.getIsStopped()) {
            ui.showToUser("The timer has already finished!");
            return;
        }
        assert timer.isTimerRunning();
        timer.pauseTimer();
    }

    public void callResumeTimer() {
        if (!isTimerInitialised) {
            ui.showToUser("There is no timer running.");
            return;
        }
        if (timer.isTimerPaused()) {
            timer.resumeTimer();
        } else if (timer.isTimerRunning()) {
            ui.showToUser("The timer is still running!");
        } else {
            ui.showToUser("There is no timer running currently!");
        }
    }

    public void callStopTimer() {
        if (isTimerInitialised) {
            timer.stopTimer();
            isTimerInitialised = updateIsTimerRunning();
            taskList.printAllTasks(ui);
            ui.showToUser("Would you like to start another timer, mark a task as done, "
                    + "or leave the study session?");
            return;
        }
        ui.showToUser("You don't have a timer running!");
    }

    private boolean updateIsTimerRunning() {
        return timer.isTimerRunning();
    }

    /**
     * Resets the timer by creating a new timer object, which can then be started by the user.
     */
    public void callResetTimer(String[] parsedInput) {
        String parameter = TimerParser.parseStudyParameter(parsedInput);
        if (parameter.equals("stopwatch")) {
            timer = new Stopwatch(taskList, ui, jframe, jlabel);
            return;
        }
        timer = new Countdown(taskList, ui, jframe, jlabel);
    }

    private boolean isTimerPausedOrStopped() {
        if (!isTimerInitialised) {
            return true;
        }
        return timer.isTimerPaused();
    }

    public boolean getIsTimerRunning() {
        if (!isTimerInitialised) {
            return false;
        }
        return timer.isTimerRunning;
    }

    /**
     * Destroy the jframe that was created.
     */
    public void destroyFrame() {
        jframe.dispose();
    }


    /**
     * Call method when window is opened.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Call method as window is closing.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        callStopTimer();
        ui.showLine();
    }

    /**
     * Call method when window is closed.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowClosed(WindowEvent e) {
    }


    /**
     * Call method when window is minimised.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowIconified(WindowEvent e) {

    }

    /**
     * Call method when window is set from minimised to normal size.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    /**
     * Call method when window is set to be the active (current viewing) window.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowActivated(WindowEvent e) {

    }


    /**
     * Call method when window is not set to be the active (current viewing) window.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
