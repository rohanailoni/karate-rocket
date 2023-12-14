public class CustomRunner {

    
    public Results execute() {
        Results results = Runner.path(featurePath).parallel(threadCount);
        
        ExecutionContext customContext = new ExecutionContext(
                results, System.currentTimeMillis(),
                featureContext, callContext, "customReportDir",
                resultsConsumer, scenarioExecutor
        );

     
        Results customResults = FeatureRunner.runFeature(customContext);

        return customResults;
    }

    // ... existing code
}
