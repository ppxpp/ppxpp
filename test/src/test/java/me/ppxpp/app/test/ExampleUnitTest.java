package me.ppxpp.app.test;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testVersion() throws Exception{
        SDKManager sdkManager = SDKManager.instance();
        assertEquals(sdkManager.getVersion(), 12);
    }

    @Test
    public void testVersion2() throws Exception{
        SDKManager sdkManager = SDKManager.instance();
        assertEquals(sdkManager.getVersion(), 11);
    }
    @Test
    public void testVersion3() throws Exception{
        SDKManager sdkManager = SDKManager.instance();
        assertEquals(sdkManager.getVersion(), 13);
    }

}