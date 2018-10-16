package com.cloudrancher.dropwiz;

import com.cloudrancher.dropwiz.health.TemplateHealthCheck;
import com.cloudrancher.dropwiz.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizHelloApplication extends Application<DropwizHelloConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizHelloApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizHello";
    }

    @Override
    public void initialize(final Bootstrap<DropwizHelloConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizHelloConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource hwRes = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        environment.jersey().register(hwRes);
        
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
