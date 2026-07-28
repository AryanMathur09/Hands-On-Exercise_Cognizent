package com.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyServiceTest {

    // @Mock creates a fake ExternalApi automatically
    @Mock
    private ExternalApi mockApi;

    // @InjectMocks creates MyService and injects mockApi into it
    @InjectMocks
    private MyService myService;

    // ==========================================
    // Exercise 1: Mocking and Stubbing
    // ==========================================
    @Test
    public void testMockingAndStubbing() {
        System.out.println("Testing Mocking and Stubbing...");

        // ARRANGE - stub the mock method
        // "when getData() is called, return Mock Data"
        when(mockApi.getData()).thenReturn("Mock Data");

        // ACT
        String result = myService.fetchData();

        // ASSERT
        assertEquals("Mock Data", result,
                "Should return mocked data");
        System.out.println("Mocking test passed! Result: " + result);
    }

    @Test
    public void testStubbingWithArguments() {
        System.out.println("Testing Stubbing with arguments...");

        // Stub with specific argument
        when(mockApi.getDataById(1)).thenReturn("Mock Data for ID 1");
        when(mockApi.getDataById(2)).thenReturn("Mock Data for ID 2");

        // ACT
        String result1 = myService.fetchDataById(1);
        String result2 = myService.fetchDataById(2);

        // ASSERT
        assertEquals("Mock Data for ID 1", result1);
        assertEquals("Mock Data for ID 2", result2);
        System.out.println("Stubbing with arguments passed!");
    }

    // ==========================================
    // Exercise 2: Verifying Interactions
    // ==========================================
    @Test
    public void testVerifyInteraction() {
        System.out.println("Testing Verify Interaction...");

        // ARRANGE
        when(mockApi.getData()).thenReturn("Mock Data");

        // ACT
        myService.fetchData();

        // ASSERT - verify getData() was called exactly once
        verify(mockApi).getData();
        verify(mockApi, times(1)).getData();
        System.out.println("Verify interaction test passed!");
    }

    @Test
    public void testVerifyNeverCalled() {
        System.out.println("Testing Verify Never Called...");

        // ACT - don't call fetchData()

        // ASSERT - verify getData() was NEVER called
        verify(mockApi, never()).getData();
        System.out.println("Verify never called test passed!");
    }

    @Test
    public void testVerifyWithArguments() {
        System.out.println("Testing Verify with Arguments...");

        // ARRANGE
        when(mockApi.getDataById(anyInt())).thenReturn("Some Data");

        // ACT
        myService.fetchDataById(42);

        // ASSERT - verify called with specific argument
        verify(mockApi).getDataById(42);
        verify(mockApi, never()).getDataById(1); // never called with 1
        System.out.println("Verify with arguments test passed!");
    }

    @Test
    public void testProcessData_WithValidData() {
        System.out.println("Testing processData with valid data...");

        // ARRANGE
        when(mockApi.saveData("ValidData")).thenReturn(true);

        // ACT
        boolean result = myService.processData("ValidData");

        // ASSERT
        assertTrue(result);
        verify(mockApi).saveData("ValidData");
        System.out.println("Process valid data test passed!");
    }

    @Test
    public void testProcessData_WithNullData() {
        System.out.println("Testing processData with null data...");

        // ACT
        boolean result = myService.processData(null);

        // ASSERT
        assertFalse(result);
        // saveData should NEVER be called with null input
        verify(mockApi, never()).saveData(any());
        System.out.println("Process null data test passed!");
    }
}
