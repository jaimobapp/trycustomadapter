package com.example.jai.trycustomadapter;

/**
 * Created by Jai on 10/1/2016.
 */

public class Task {
    private String taskTitle;
    private String taskDesc;

    public Task(String taskTitle, String taskDesc) {
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }
}
