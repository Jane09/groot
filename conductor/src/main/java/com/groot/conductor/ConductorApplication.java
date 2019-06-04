package com.groot.conductor;

import com.groot.conductor.worker.BizWorker1;
import com.groot.conductor.worker.BizWorker2;
import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.http.WorkflowClient;
import com.netflix.conductor.client.task.WorkflowTaskCoordinator;
import com.netflix.conductor.common.run.Workflow;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConductorApplication implements CommandLineRunner {

    private static final String BASE_URL = "http://localhost:8080/api/";

    public static void main(String[] args) {
        SpringApplication.run(ConductorApplication.class);
    }

    public void run(String... args) throws Exception {
        TaskClient client = new TaskClient();
        client.setRootURI(BASE_URL);
        WorkflowTaskCoordinator.Builder builder = new WorkflowTaskCoordinator.Builder();
        BizWorker1 worker1 = new BizWorker1("bw1");
        System.out.println(worker1.getPollingInterval());
        BizWorker2 worker2 = new BizWorker2("bw2");
        WorkflowTaskCoordinator coordinator = builder.
                withThreadCount(2).withTaskClient(client)
                .withWorkers(worker1,worker2).build();
        coordinator.init();

        WorkflowClient flow = new WorkflowClient();
        flow.setRootURI(BASE_URL);
        Workflow wf = flow.getWorkflow("fb2b79af-f768-4cb3-ab60-97f5c75e8a43",true);
        System.out.println(wf.getOutput());
    }
}
