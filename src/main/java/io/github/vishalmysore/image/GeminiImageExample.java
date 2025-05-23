package io.github.vishalmysore.image;


import com.t4a.processor.AIProcessingException;
import com.t4a.processor.GeminiImageActionProcessor;
import com.t4a.transform.GeminiV2PromptTransformer;
import io.github.vishalmysore.pojo.*;
import lombok.extern.java.Log;


@Log
public class GeminiImageExample {

    public static void main(String[] args) throws AIProcessingException {
        GeminiImageActionProcessor processor = new GeminiImageActionProcessor();
        ProjectDashboard projectDashboard = (ProjectDashboard) processor.imageToPojo(GeminiImageExample.class.getClassLoader().getResource("images/RAG.PNG"), ProjectDashboard.class);
        log.info(projectDashboard.toString());


        log.info(processor.imageToJson(GeminiImageExample.class.getClassLoader().getResource("images/sales.PNG"),"sales in 2013", "sales in 2015"));

        FoodConsumption foodConsume = (FoodConsumption) processor.imageToPojo(GeminiImageExample.class.getClassLoader().getResource("images/PieChart.PNG"), FoodConsumption.class);
        log.info(foodConsume.toString());
        log.info(processor.imageToPojo(GeminiImageExample.class.getClassLoader().getResource("images/FruitsSold.PNG"), WeeklyFruitSales.class).toString());


       String text = processor.imageToText(GeminiImageExample.class.getClassLoader().getResource("images/library.PNG"),"convert the entire screen to text");
        GeminiV2PromptTransformer transformer = new GeminiV2PromptTransformer();
        log.info(transformer.transformIntoPojo(text, LibraryScreen.class).toString());

        String jsonStr = processor.imageToJson(GeminiImageExample.class.getClassLoader().getResource("images/auto.PNG"),"Full Inspection");
        log.info(jsonStr);

       jsonStr = processor.imageToJson(GeminiImageExample.class.getClassLoader().getResource("images/auto.PNG"),"Full Inspection","Tire Rotation","Oil Change");
        log.info(jsonStr);
        jsonStr = processor.imageToJson(GeminiImageExample.class.getClassLoader().getResource("images/auto.PNG"), AutoRepairScreen.class);
        log.info(jsonStr);
        jsonStr = processor.imageToJson(GeminiImageExample.class.getClassLoader().getResource("images/fitness.PNG"), MyGymSchedule.class);
        log.info(jsonStr);
        Object pojo = processor.imageToPojo(GeminiImageExample.class.getClassLoader().getResource("images/fitness.PNG"), MyGymSchedule.class);
        log.info(pojo.toString());
        pojo = processor.imageToPojo(GeminiImageExample.class.getClassLoader().getResource("images/auto.PNG"), AutoRepairScreen.class);
        log.info(pojo.toString());

        log.info(processor.imageToPojo(GeminiImageExample.class.getClassLoader().getResource("images/sales.PNG"), Sales.class).toString());




    }

}
