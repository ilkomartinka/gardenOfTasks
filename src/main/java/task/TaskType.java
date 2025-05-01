package task;

public enum TaskType {

    TODO("TO DO"),
    IN_PROGRESS("IN PROGRESS"),
    COMPLETED("COMPLETED");

    private final String description;

    TaskType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
