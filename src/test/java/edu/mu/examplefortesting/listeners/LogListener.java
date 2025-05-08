package edu.mu.examplefortesting.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogListener implements ITestListener, ISuiteListener {
    private PrintWriter writer;

    @Override
    public void onStart(ISuite suite) {
        try {
            writer = new PrintWriter(new FileWriter("test-log.txt"));
            writer.println(">> Suite started: " + suite.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 // Called once after ALL <test>s have finished
    @Override
    public void onFinish(ISuite suite) {
        writer.println(">> SUITE FINISHED: " + suite.getName());
        writer.close();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        writer.println("[PASS] " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        writer.println("[FAIL] " + result.getName() + " : " + result.getThrowable());
    }

    // Other ITestListener methods left empty
    @Override public void onTestSkipped(ITestResult result) { }
    @Override public void onTestStart(ITestResult result) { }
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }
}