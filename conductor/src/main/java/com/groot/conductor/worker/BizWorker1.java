package com.groot.conductor.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BizWorker1 implements Worker {

    private String taskDefName;


    public TaskResult execute(Task task) {
        System.out.println("bw1: "+task.getInputData().get("a1"));
        TaskResult result = new TaskResult(task);
        result.setStatus(TaskResult.Status.COMPLETED);
        result.getOutputData().put("aa","123");
        return result;
    }
}
