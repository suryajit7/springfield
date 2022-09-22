package com.springfield.framework.functional;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class TestLauncher {

    public static SummaryGeneratingListener listener = new SummaryGeneratingListener();

    public static void main(String[] args) throws InterruptedException {

        //@formatter:off
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("src.test.java.com.springfield.framework"))
                .filters(includeClassNamePatterns(".*Test")).build();
/*                .configurationParameter("junit.conditions.deactivate", "com.baeldung.extensions.*")
                .configurationParameter("junit.jupiter.extensions.autodetection.enabled", "true")*/


        TestPlan plan = LauncherFactory.create().discover(request);
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        listener.getSummary().printTo(new PrintWriter(System.out));

    }
}
