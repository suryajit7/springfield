package com.springfield.framework;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

@RestController
@SpringBootApplication
public class SpringfieldApplication {

	@GetMapping("/welcome")
	public String welcome(){
		return "Welcome to Project Springfield";
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringfieldApplication.class, args);

		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
				.selectors(selectPackage(""))
				.filters(includeClassNamePatterns(".*Test"))
				.build();

		SummaryGeneratingListener listener = new SummaryGeneratingListener();

		try (LauncherSession session = LauncherFactory.openSession()) {
			Launcher launcher = session.getLauncher();
			// Register a listener of your choice
			launcher.registerTestExecutionListeners(listener);
			// Discover tests and build a test plan
			TestPlan testPlan = launcher.discover(request);
			// Execute test plan
			launcher.execute(testPlan);
			// Alternatively, execute the request directly
			launcher.execute(request);
		}

		TestExecutionSummary summary = listener.getSummary();

		System.out.println("containers Found Count  " + summary.getContainersFoundCount());
		System.out.println("containers Succeeded Count  " + summary.getContainersSucceededCount());
		System.out.println("containers Failed Count  " + summary.getContainersFailedCount());
		System.out.println("containers Skipped Count  " + summary.getContainersSkippedCount());

		System.out.println("tests Found Count  " + summary.getTestsFoundCount());
		System.out.println("tests Succeeded Count  " + summary.getTestsSucceededCount());
		System.out.println("tests Failed Count  " + summary.getTestsFailedCount());
		System.out.println("tests Skipped Count  " + summary.getTestsSkippedCount());

	}



}
