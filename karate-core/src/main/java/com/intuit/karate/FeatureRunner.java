import com.intuit.karate.core.ExecutionContext;
import com.intuit.karate.core.FeatureContext;
import com.intuit.karate.core.FeatureExecutionUnit;
import com.intuit.karate.core.FeatureResult;
import com.intuit.karate.core.Results;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class FeatureRunner {

    public static Results runFeature(ExecutionContext execContext) {
        FeatureResult result = execContext.result;
        FeatureContext featureContext = execContext.featureContext;
        CountDownLatch latch = new CountDownLatch(1);

        // Create a FeatureExecutionUnit
        FeatureExecutionUnit unit = new FeatureExecutionUnit(execContext);
        unit.setNext(() -> {
            latch.countDown();
        });

        // Run the feature
        if (execContext.scenarioExecutor != null) {
            execContext.scenarioExecutor.submit(unit);
        } else {
            unit.run();
        }

        // Wait for the feature to complete
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to wait for feature execution", e);
        }

        // Process the results
        execContext.stop();
        return execContext.results;
    }

    // You can create additional utility methods as needed
}
