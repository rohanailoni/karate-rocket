import com.intuit.karate.core.ExecutionContext;
import com.intuit.karate.core.FeatureResult;
import com.intuit.karate.core.ScenarioContext;
import com.intuit.karate.core.ScenarioExecutionUnit;

import java.util.concurrent.CountDownLatch;

public class ScenarioRunner {

    public static void runScenario(ScenarioExecutionUnit unit) {
      
        CountDownLatch latch = new CountDownLatch(1);

       
        unit.setNext(() -> {
            latch.countDown();
        });

        
        unit.run();

    
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to wait for scenario execution", e);
        }

      
        ScenarioContext scenarioContext = unit.getContext();
        FeatureResult scenarioResult = unit.result;

     
    }

}
