package com.intuit.karate.core;

import java.util.List;

public class ScenarioOutlineRunner {

    public static void runScenarioOutline(String featurePath) {
       
        Feature feature = FeatureParser.parse(featurePath);

      
        List<ScenarioOutline> scenarioOutlines = feature.getScenarioOutlines();

        for (ScenarioOutline scenarioOutline : scenarioOutlines) {
        
            List<Scenario> scenarios = scenarioOutline.getScenarios();

           
            for (Scenario scenario : scenarios) {
                
                ExecutionContext execContext = createExecutionContext(scenario);

                ScenarioExecutionUnit scenarioExecutionUnit = new ScenarioExecutionUnit(execContext);

                
                scenarioExecutionUnit.run();

               
                ScenarioResult scenarioResult = scenarioExecutionUnit.result;
               
            }
        }
    }

   
    private static ExecutionContext createExecutionContext(Scenario scenario) {
      
        FeatureContext featureContext = new FeatureContext(null, scenario.getFeature(), null);
        CallContext callContext = CallContext.forAsync(scenario.getFeature(), null, null, null, false);

       
        return new ExecutionContext(null, System.currentTimeMillis(), featureContext, callContext, null, null, null);
    }
}
