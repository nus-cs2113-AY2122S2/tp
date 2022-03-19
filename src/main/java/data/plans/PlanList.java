package data.plans;

import commands.PlanCommand;
import data.workouts.Workout;
import data.workouts.WorkoutList;

import java.util.ArrayList;
import java.util.HashMap;

public class PlanList {
    public static final int MAX_NUMBER_OF_WORKOUTS_IN_A_PLAN = 10;
    private WorkoutList workoutList;
    private HashMap<String, Plan> plansHashMapList = new HashMap<>();
    private ArrayList<String> plansDisplayList = new ArrayList<>();

    public PlanList(WorkoutList workoutList) {
        this.workoutList = workoutList;
    }

    public ArrayList<String> getPlansDisplayList() {
        return this.plansDisplayList;
    }

    public HashMap<String, Plan> getPlansHashMapList() {
        return this.plansHashMapList;
    }

    public Plan getPlanFromKey(String planKey) {
        return getPlansHashMapList().get(planKey);
    }

    public Plan getPlanFromIndexNum(int indexNum) {
        int elementNum = indexNum - 1;
        assert (elementNum >= 0);
        String keyValue = getPlansDisplayList().get(elementNum);
        Plan planObject = plansHashMapList.get(keyValue);
        return planObject;
    }

    /**
     * Parses the given user argument to identify the details of the new plan to be added.
     * Thereafter, the details will be checked for their validity. If all checks pass, a new
     * Plan object is instantiated before adding it to the ArrayList of plans.
     *
     * @param userArgument The user's details for the new plan, including plan name
     *                     and workout(s) to be added to the plan, via their workout number(s).
     * @return A Plan object that represents the new plan.
     * @throws ArrayIndexOutOfBoundsException If userArgument contains insufficient arguments and parsing fails.
     * @throws NumberFormatException If the workout number(s) specified in userArgument is an
     *                               invalid number.
     * @throws InvalidPlanException If the details specified in userArgument is invalid.
     */
    public Plan createAndAddPlan(String userArgument) throws ArrayIndexOutOfBoundsException,
            NumberFormatException, InvalidPlanException {
        String userPlanNameInput = userArgument.split(PlanCommand.CREATE_ACTION_WORKOUTS_KEYWORD)[0].trim();

        String className = this.getClass().getSimpleName();
        boolean hasSamePlanName = checkForExistingPlanName(userPlanNameInput);
        if (hasSamePlanName) {
            throw new InvalidPlanException(className, InvalidPlanException.DUPLICATE_PLAN_NAME_ERROR_MSG);
        }

        String userWorkoutNumbersString = userArgument.split(PlanCommand.CREATE_ACTION_WORKOUTS_KEYWORD)[1].trim();

        int numberOfWorkoutsInAPlan = userWorkoutNumbersString.split(",").length;
        boolean isAppropriateNumberOfWorkouts = checkMinMaxNumberOfWorkouts(numberOfWorkoutsInAPlan);
        if (!isAppropriateNumberOfWorkouts) {
            throw new InvalidPlanException(className, InvalidPlanException.MIN_MAX_WORKOUTS_IN_A_PLAN);
        }

        ArrayList<Workout> workoutsToAddInAPlanList = new ArrayList<Workout>();
        for (int i = 0; i < numberOfWorkoutsInAPlan; i += 1) {
            int workoutNumberInteger = Integer.parseInt(userWorkoutNumbersString.split(",")[i]);

            boolean isWithinWorkoutListRange = checkWorkoutNumberWithinRange(workoutNumberInteger);
            if (!isWithinWorkoutListRange) {
                throw new InvalidPlanException(className, InvalidPlanException.WORKOUT_NUMBER_OUT_OF_RANGE);
            }

            assert (workoutNumberInteger > 0) && (workoutNumberInteger <= workoutList.getWorkoutsDisplayList().size());
            String workoutToAddKey = workoutList.getWorkoutsDisplayList().get(workoutNumberInteger - 1);
            Workout workoutToAddObject = workoutList.getWorkoutFromKey(workoutToAddKey);
            workoutsToAddInAPlanList.add(workoutToAddObject);
        }

        Plan newPlan = new Plan(userPlanNameInput, workoutsToAddInAPlanList);
        String newPlanKey = newPlan.toString();
        plansHashMapList.put(newPlanKey, newPlan);
        plansDisplayList.add(newPlanKey);
        return newPlan;
    }

    /**
     * Checks if the provided plan details already exists in the ArrayList of plans. A plan
     * is considered to already exist in the list if the plan name matches an existing plan
     * name in the ArrayList.
     *
     * @param userPlanNameInput The plan name entered by user to check.
     * @return True if an existing plan with the same plan name exists in the plans list.
     *         Otherwise, returns false.
     */
    public boolean checkForExistingPlanName(String userPlanNameInput) {
        String userPlanNameInputLowerCase = userPlanNameInput.toLowerCase();
        for (int i = 0; i < plansDisplayList.size(); i += 1) {
            String getPlanName = plansDisplayList.get(i).toString().toLowerCase();

            if (userPlanNameInputLowerCase.equals(getPlanName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks whether the number of workouts to be added in a
     * plan is within the minimum and maximum number of workouts a plan can hold.
     * The range is 1 - 10 workouts.
     *
     * @param numberOfWorkouts The number of workouts to be added in a plan.
     * @return True if the number of workouts to be added is within the min
     *         and max range, else false.
     */
    public boolean checkMinMaxNumberOfWorkouts(int numberOfWorkouts) {
        return numberOfWorkouts > 0 && numberOfWorkouts <= MAX_NUMBER_OF_WORKOUTS_IN_A_PLAN;
    }

    /**
     * This method checks whether the workout number supplied
     * is within the range of the current workout list.
     *
     * @param workoutNumber The workout number to check.
     * @return True if workout number is within the range of the workout list,
     *         else false if out of range.
     */
    public boolean checkWorkoutNumberWithinRange(int workoutNumber) {
        return workoutNumber > 0 && workoutNumber <= workoutList.getWorkoutsDisplayList().size();
    }

    /**
     * Prints all the plan names that are stored in the plans list.
     */
    public void listAllPlan() {
        if (getPlansDisplayList().size() <= 0) {
            System.out.println("Oops! You have not created any plans yet!");
            System.out.println("To create a new plan, enter 'plan /new <plan name> /workouts "
                    + "\n<workout number(s) to add, separated by comma>'."
                    + "\nAlternatively, enter 'help' for more information.");
            return;
        }

        assert (getPlansDisplayList().size() > 0);
        System.out.println("Here are all your plan(s).");
        System.out.println("To view each plan in detail, enter 'plan /details <plan number in list>'.\n");
        for (int i = 0; i < getPlansDisplayList().size(); i += 1) {
            System.out.println((i + 1) + "." + getPlansDisplayList().get(i).toString());
        }
    }
}
