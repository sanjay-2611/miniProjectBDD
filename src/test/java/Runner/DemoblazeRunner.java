package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		features="src//test//resources//features//demoblaze.feature",
		glue={"StepDefs"},
		plugin= {"pretty",
				"html:target//Reports//HTMLReport.html",}
		)

public class DemoblazeRunner extends AbstractTestNGCucumberTests {

	
}
