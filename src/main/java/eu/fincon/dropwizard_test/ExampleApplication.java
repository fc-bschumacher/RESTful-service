package eu.fincon.dropwizard_test;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import eu.fincon.dropwizard_test.resources.ExampleResource;
import eu.fincon.dropwizard_test.health.TemplateHealthCheck;

public class ExampleApplication extends Application<ExampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new ExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard_test";
    }

    @Override
    public void initialize(Bootstrap<ExampleConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(ExampleConfiguration configuration,
                    Environment environment) {
        final ExampleResource resource = new ExampleResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}