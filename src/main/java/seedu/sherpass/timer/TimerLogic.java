package seedu.sherpass.timer;

import seedu.sherpass.command.Command;
import seedu.sherpass.exception.InvalidTimeException;

import seedu.sherpass.util.parser.Parser;
import seedu.sherpass.util.parser.TimerParser;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.timetable.Timetable;

import static seedu.sherpass.constant.Index.STUDY_PARAMETER_INDEX;
import static seedu.sherpass.constant.Message.ERROR_INVALID_TIMER_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.EMPTY_STRING;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;

public class TimerLogic implements WindowListener {

    private static Ui ui;
    private Timer timer;
    private static TaskList taskList;
    protected static volatile boolean isTimerInitialised = false;
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
        jframe.setBounds(500, 300, 300, 100);
        jlabel = new JLabel(EMPTY_STRING, SwingConstants.CENTER);
        jframe.setLayout(new BorderLayout());
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
     * Checks if the timer is paused or stopped, then executes the mark, unmark or show command.
     *
     * @param storage     Storage.
     * @param parsedInput parsedInput.
     */
    public void markOrShowTask(Storage storage, String parsedInput) {
        if (isTimerPausedOrStopped()) {
            executeCommand(storage, parsedInput);
        } else {
            ui.showToUser("You can't mark/show tasks while timer is running!");
        }
    }


    private void executeCommand(Storage storage, String parsedInput) {
        Command c = Parser.parseCommand(parsedInput, ui);
        c.execute(taskList, ui, storage);
        printAvailableCommands();
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
            Timetable.showScheduleByDay(LocalDate.now(), taskList, ui);
            ui.showToUser("Would you like to start another timer, mark a task as done, "
                    + "or leave the study session?");
            return;
        }
        ui.showToUser("You don't have a timer running!");
    }

    private boolean updateIsTimerRunning() {
        return timer.isTimerRunning();
    }

    public static void resetIsTimerInitialised() {
        isTimerInitialised = false;
    }

    private String selectStudyTimer(String[] parsedInput) {
        if (parsedInput[STUDY_PARAMETER_INDEX].trim().equals("stopwatch")) {
            return "stopwatch";
        }
        return "countdown";
    }

    /**
     * Resets the timer by creating a new timer object, which can then be started by the user.
     */
    public void callResetTimer(String[] parsedInput) {
        String parameter = selectStudyTimer(parsedInput);
        if (parameter.equals("stopwatch")) {
            timer = new Stopwatch(taskList, ui, jframe, jlabel);
            return;
        }
        timer = new Countdown(taskList, ui, jframe, jlabel);
    }

    public void killTimer() {
        if (!isTimerInitialised) {
            return;
        } else {
            timer.interrupt();
        }
    }

    /**
     * Returns if a timer is paused or stopped, so that mark or show command can be called.
     * It first checks if timer is initialised. If initialised, it then calls the isTimerPaused() method to check the
     * static variable isTimerPaused in abstract class Timer.
     *
     * @return Returns if timer is paused or stopped.
     */
    public boolean isTimerPausedOrStopped() {
        if (!isTimerInitialised) {
            return true;
        }
        if (!timer.isTimerRunning()) {
            return true;
        }
        return timer.isTimerPaused();
    }

    /**
     * Destroy the jframe that was created.
     */
    public void destroyFrame() {
        jframe.dispose();
    }


    /**
     * Calls method when window is opened.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Calls method as window is closing.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        callStopTimer();
        ui.showLine();
    }

    /**
     * Calls method when window is closed.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowClosed(WindowEvent e) {
    }


    /**
     * Calls method when window is minimised.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowIconified(WindowEvent e) {

    }

    /**
     * Calls method when window is set from minimised to normal size.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    /**
     * Calls method when window is set to be the active (current viewing) window.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowActivated(WindowEvent e) {

    }


    /**
     * Calls method when window is not set to be the active (current viewing) window.
     *
     * @param e Event signifying the change in status of the window.
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
