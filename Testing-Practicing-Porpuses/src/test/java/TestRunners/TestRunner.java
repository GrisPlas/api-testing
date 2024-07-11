package TestRunners;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;


public class TestRunner {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("src/main/resources/Suites/Suite.xml");
        testNG.setTestSuites(suites);
        testNG.run();
    }
}
