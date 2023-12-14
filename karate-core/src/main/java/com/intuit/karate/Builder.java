import com.intuit.karate.core.ExecutionContext;
import com.intuit.karate.core.Feature;
import com.intuit.karate.core.FeatureContext;
import com.intuit.karate.core.FeatureParser;
import com.intuit.karate.core.ScenarioExecutionUnit;
import com.intuit.karate.job.JobConfig;
import com.intuit.karate.job.ScenarioJobServer;

import java.io.File;
import java.util.List;

public class CustomScenarioRunner {

    public static class Builder {

        private int threadCount = 1;
        private String reportDir;
        private List<String> tags;
        private List<String> paths;
        private List<Resource> resources;

        public Builder threadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        public Builder reportDir(String reportDir) {
            this.reportDir = reportDir;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder paths(List<String> paths) {
            this.paths = paths;
            return this;
        }

        public Builder resources(List<Resource> resources) {
            this.resources = resources;
            return this;
        }

        public CustomScenarioRunner build() {
            return new CustomScenarioRunner(this);
        }
    }

    private final Builder builder;

    private CustomScenarioRunner(Builder builder) {
        this.builder = builder;
    }

    public void execute() {
        RunnerResults results = Runner.parallel(builder.threadCount, builder.reportDir, builder.tags, builder.paths, builder.resources);
        // Process results or perform additional actions if needed
    }

    public static class Runner {

        public static RunnerResults parallel(int threadCount, String reportDir, List<String> tags, List<String> paths, List<Resource> resources) {
            Builder options = new Builder();
            options.threadCount = threadCount;
            options.reportDir = reportDir;
            options.tags = tags;
            options.paths = paths;
            options.resources = resources;
            return CustomScenarioRunner.run(options);
        }
    }

    private static class CustomJobServer extends ScenarioJobServer {

        public CustomJobServer(JobConfig config, String reportDir) {
            super(config, reportDir);
        }

        @Override
        public void onComplete() {
            //no idea what to do but we have reporting directory 
        }
    }

    



    private static class CustomFeatureExecutionUnit extends FeatureExecutionUnit {

        public CustomFeatureExecutionUnit(ExecutionContext execContext) {
            super(execContext);
        }

        @Override
        public void run() {
            super.run();
        }
    }

    private static class CustomScenarioExecutionUnit extends ScenarioExecutionUnit {

        public CustomScenarioExecutionUnit(ExecutionContext execContext) {
            super(execContext);
        }

        @Override
        public void run() {

            super.run();
        }
    }

    private static class CustomFeature extends Feature {

        public CustomFeature(FeatureContext featureContext) {
            super(featureContext);
        }

        
    }

    private static class CustomFeatureParser extends FeatureParser {

        public CustomFeatureParser(File file) {
            super(file);
        }

       
    }

    private static class CustomExecutionContext extends ExecutionContext {

        public CustomExecutionContext(Results results, long startTime, FeatureContext featureContext, CallContext callContext, String reportDirString, Consumer<Runnable> system, ExecutorService scenarioExecutor, ClassLoader classLoader) {
            super(results, startTime, featureContext, callContext, reportDirString, system, scenarioExecutor, classLoader);
        }

    
    }
}
