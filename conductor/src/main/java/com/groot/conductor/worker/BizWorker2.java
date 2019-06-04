package com.groot.conductor.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BizWorker2 implements Worker {

    private String taskDefName;


    public TaskResult execute(Task task) {
        System.out.println("bw2: "+task.getInputData().get("b1"));
        TaskResult result = new TaskResult(task);
        result.setStatus(TaskResult.Status.COMPLETED);
        result.getOutputData().put("bb","456");
        return result;
    }
}
