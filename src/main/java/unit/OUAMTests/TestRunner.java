package unit.OUAMTests;

 
import org.junit.runner.JUnitCore;

import org.junit.runner.Result;

import org.junit.runner.notification.Failure;


public class TestRunner {	
    public static void main(String[] args) {
        runTest(LoginTest.class, "Login");
        runTest(RegistrationServletTest.class, "RegistrationServlet");
        runTest(VerificationTest.class, "Verification");
        runTest(LogoutTest.class, "Logout");
        runTest(AccountTest.class, "Account");
    }
	
    private static void runTest(Class<?> testClass, String testName) {
        System.out.println("- Testing " + testName + ":");
        Result result = JUnitCore.runClasses(testClass);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        String status = result.wasSuccessful() ? "Passed" : "Failed";
        System.out.println("Test status = " + status);
        System.out.println("Number of Tests Passed = " + result.getRunCount());
        System.out.println("Number of Tests Ignored = " + result.getIgnoreCount());
        System.out.println("Number of Tests Failed = " + result.getFailureCount());
        System.out.println("Time = " + result.getRunTime() / 1000.0 + "s");
        System.out.println();
    }
}