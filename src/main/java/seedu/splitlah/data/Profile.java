package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Represents a profile that manages a list of sessions and keeps track of unique session and activity
 * identifiers for the SplitLah application.
 * 
 * @author Roy
 */
public class Profile {
    
    private ArrayList<Session> sessionList;
    private int sessionIdTracker;
    private int activityIdTracker;

    private static Logger logger = Logger.getLogger(Profile.class.getName());

    /**
     * Constructor to create a Profile object.
     */
    public Profile() {
        this.sessionList = new ArrayList<>();
        this.sessionIdTracker = 1;
        this.activityIdTracker = 1;
    }

    /**
     * Checks if there is a session that exists with the same name.
     *
     * @param sessionName A String object that represents the session name.
     * @return true if a session exists with the same name, false otherwise.
     */
    public boolean hasSessionName(String sessionName) {
        if (sessionList.isEmpty()) {
            return false;
        }
        for (Session session : sessionList) {
            if (session.getSessionName().equalsIgnoreCase(sessionName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a session that exists with the specified session unique identifier.
     *
     * @param sessionId An integer that uniquely identifies a session.
     * @return true if a session exists with the specified session unique identifier, false otherwise.
     */
    public boolean hasSessionId(int sessionId) {
        if (sessionList.isEmpty()) {
            return false;
        }
        for (Session session : sessionList) {
            if (session.getSessionId() == sessionId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the session object via the session unique identifier specified by the user.
     *
     * @param sessionId An integer that uniquely identifies a session.
     * @return A Session object with the matching session id.
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    public Session getSession(int sessionId) throws InvalidDataException {
        if (sessionList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_PROFILE_SESSION_LIST_EMPTY);
        }
        for (Session session : sessionList) {
            if (session.getSessionId() == sessionId) {
                return session;
            }
        }
        throw new InvalidDataException(Message.ERROR_PROFILE_SESSION_NOT_IN_LIST);
    }

    /**
     * Adds a Session object to the list of sessions.
     *
     * @param session A Session object that is to be added.
     */
    public void addSession(Session session) {
        sessionList.add(session);
        logger.finest(Message.LOGGER_PROFILE_SESSION_ADDED + session.getSessionId());
    }

    /**
     * Removes a Session object with the specified session unique identifier from the list of sessions.
     *
     * @param sessionId An integer that uniquely identifies a session.
     */
    public void removeSession(int sessionId) throws InvalidDataException {
        Session sessionToBeRemoved = getSession(sessionId);
        sessionList.remove(sessionToBeRemoved);
        logger.finest(Message.LOGGER_PROFILE_SESSION_REMOVED + sessionId);
    }

    /**
     * Returns the list of session stored in Profile object.
     *
     * @return An ArrayList of Session objects.
     */
    public ArrayList<Session> getSessionList() {
        return sessionList;
    }

    /**
     * Returns a new session id for session object to be created.
     * Assumption: Function is called when a new Session object is being created without errors.
     *
     * @return An integer that represents the new session unique identifier.
     */
    public int getNewSessionId() {
        int newSessionId = sessionIdTracker;
        sessionIdTracker += 1;
        return newSessionId;
    }

    /**
     * Returns the value of the current session unique identifier that is tracked.
     * @return An integer that represents the current session unique identifier.
     */
    public int getSessionIdTracker() {
        return sessionIdTracker;
    }

    /**
     * Returns a new activity id for activity object to be created.
     * Assumption: Function is called when a new Activity object is being created without errors.
     *
     * @return An integer that represents the new activity unique identifier.
     */
    public int getNewActivityId() {
        int newActivityId = activityIdTracker;
        activityIdTracker += 1;
        return newActivityId;
    }

    /**
     * Returns the value of the current activity unique identifier that is tracked.
     * @return An integer that represents the current activity unique identifier.
     */
    public int getActivityIdTracker() {
        return activityIdTracker;
    }
}
