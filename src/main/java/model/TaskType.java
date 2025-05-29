package model;

/**
 * Represents the type of task.
 * Each task type is associated with a predefined reward in coins.
 */
public enum TaskType {
    // School-related tasks
    HOMEWORK(5),
    STUDY(15),
    PROJECT(10),
    EXAM(20),

    // Work-related tasks
    WORK_TASK(10),
    MEETING(5),
    DEADLINE(15),

    // Personal tasks
    SELF_CARE(5),
    EXERCISE(10),
    HOBBY(5),

    OTHER(5);

    private final int reward;

    TaskType(int reward) {
        this.reward = reward;
    }

    public int getReward() {
        return reward;
    }
}
