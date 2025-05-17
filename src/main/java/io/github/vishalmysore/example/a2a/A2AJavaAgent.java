package io.github.vishalmysore.example.a2a;

import com.t4a.processor.AIProcessingException;
import io.github.vishalmysore.a2a.client.LocalA2ATaskClient;
import io.github.vishalmysore.a2a.domain.Task;
import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log
public class A2AJavaAgent {
    public static void main(String[] args) throws AIProcessingException {
        // Create and configure Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("io.github.vishalmysore.a2a", "io.github.vishalmysore.a2a.server","io.github.vishalmysore.exec.a2a");
        context.refresh();

        // Use the prompt
        String prompt
                = "hey I am in Toronto do you think i can go out without jacket";

        // Get the client from Spring context
        LocalA2ATaskClient client = new LocalA2ATaskClient();

        // Send task and log response
        Task t = client.sendTask(prompt);

        log.info(client.getTask(t.getId(),2).toString());

        prompt ="Sachin Tendulkar is very good cricket player, " +
                "he joined the sports on 24032022, he has played 300 matches " +
                "and his max score is 400. Can you send him a congratulations email";

        t = client.sendTask(prompt);

        log.info(client.getTask(t.getId(),2).toString());

        prompt ="Maharaja restaurant in \" +\n" +
                "                \"Toronto for Sachin Tendulkar and 4 people on 12th may";
        t = client.sendTask(prompt);

        log.info(client.getTask(t.getId(),2).toString());
        // Clean up
        context.close();
    }
}

