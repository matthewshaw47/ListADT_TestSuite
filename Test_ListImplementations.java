/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017
// PROJECT:          Test List Implementation
// FILE:             Test_ListImplementations
//
// TEAM:    Team 16
// Authors: Matthew Shaw
// Author1: Nathan Logan
// Author2: Yuying Yan
//
// ---------------- OTHER ASSISTANCE CREDITS
// Persons: Identify persons by name, relationship to you, and email.
// Describe in detail the the ideas and help they provided.
//
// Online sources: avoid web searches to solve your problems, but if you do
// search, be sure to include Web URLs and description of
// of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Example of a framework for testing multiple implementations of the
 * ListADT for TEAM_TestListImplementations assignment.
 *
 * @version 0.0
 * @author deppeler
 *
 */
public class Test_ListImplementations {

        private static int successes = 0;
        private static int failures = 0;
        // Was a test failed
        private static boolean failed = false;
        private static String sequence = "";

        /**
         * Run all tests for each list submission available.
         *
         * @param args UNUSED
         */
        public static void main(String[] args)
        {
                // List of TA names
                String [] ta_list = { "Deb", "Mingi", "Yash", "Sonu", 
                                        "Sapan", "Tianrun", "Roshan" };
                // For each TA name in the list
                for ( String ta_name : ta_list )
                {
                        // Get the class name for each name
                        String listClassName = "List_" + ta_name;
                        // Current class being tested
                        System.out.println("\n==================== TESTING "
                                                + listClassName );
                        // Begin running tests on class
                        runTestsFor(listClassName);
                        // Finished testing a class
                        System.out.println("==================== done");
                }
        }

        /**
         * Constructs a list of the correct type based on the name provided.
         * This method assumes there is a class with the name List_taName
         * that implements the ListADT<T> type given.
         *
         * For example: if taName is Deb, then it constructs an instance of
         * a generic class with the following structure.
         *
         *  <pre>public class List_Deb<T> implements ListADT<T> { ... }</pre>
         *
         * @param listClassName The name "List_Deb" or other list type name.
         */
        private static void runTestsFor(String listClassName)
        {
                try
                {
                        // Create a list of strings for a class
                        ListADT<String> list = constructListOfString(listClassName);
                        // Begin testing
                        runTestsOn(listClassName, list);
                } catch (IllegalArgumentException e) {
                        // List could not be constructed
                        System.out.println("Unable to construct " +
                                        listClassName + " NO TESTS RUN");
                }
        }

        /**
         * Constructs an instance of the list type that will contains String data.
         * The class name is given and it is assumed to be a generic type with
         * a no-arg constructor. If the List of Strings can not be constructed
         * an IllegalArgumentException is thrown.
         *
         * @param listname List_Deb or other that matches existing list type
         * @return a ListADT<String> constructed with specified class name.
         *
         * @throws IllegalArgumentException if any of several types of exceptions
         * occur when attempting to construct a ListADT<String> object from
         * the given class name.
         */
        private static ListADT<String> constructListOfString(String listname)
        {
                try
                {
                        // Get the class for the given name
                        Class<?> listClassName = Class.forName(listname);
                        // Get the class' constructor
                        Constructor<?> ctr = listClassName.getDeclaredConstructor();
                        // New Array of arguments
                        Object [] initargs = new Object[] { };

                        // CAUTION: Type cast warnings are suppressed here.
                        // to constructing Lists that will contain String data items.
                        @SuppressWarnings("unchecked")
                        // List created using class
                        ListADT<String> list = ((ListADT<String>)
                                        ctr.newInstance(initargs));
                        return list;
                } catch (Exception e)
                {
                        throw new IllegalArgumentException("Unable to construct "
                                        + "instance of " + listname + "<String>()");
                }
        }

        /**
         * Runs all tests on this list.  Note, may have to create new lists
         * to be sure that we start with a new empty list instance.
         * Otherwise, errors from one test may affect the next test.
         *
         * @param className List_Deb or other list class name
         * @param list
         */
        private static void runTestsOn(String className,ListADT<String> list) {
                // Test the isEmpty method of the class
                if ( ! list.isEmpty() ) {
                        System.out.println("test00 failed: " + className +
                                        " should be empty at start. ");
                        failures++;
                }
                // Checks if the size of the list is initially 0
                if ( list.size() != 0 ) {
                        System.out.println("test00 failed: " + className +
                                        " size() should be 0 at start, but size() = "
                                        + list.size());
                        failures++;
                }

                // Add single item and check size
                test01_List_Add_OneItem(className, list);
                list = constructListOfString(className); // Clear the list

                // Add multiple items and check size
                test02_List_Add_MultipleItems(className, list);
                list = constructListOfString(className); // Clear the list

                // Check for proper placement using add @ index
                test03_List_Check_Placement(className, list);
                list = constructListOfString(className); // Clear the list

                // Add item to list then remove it
                test04_Remove_From_List(className, list);
                list = constructListOfString(className); // Clear the list

                // Test that a class' iterator works like an ArrayList iterator
                test05_Use_List_Iterator(className, list);
                list = constructListOfString(className); // Clear the list

                // Check that the list utilizes generic data types
                test06_Check_Java_Generics(className, list);
                list = constructListOfString(className); // Clear the list
                
                // Check that the list throws InvalidArgumentException
                test07_List_Add_Null(className, list);
                list = constructListOfString(className); // Clear the list
                
                // Check that the list throws IndexOutOfBoundsException 
                // when adding to index -1
                test08_List_Add_Index_Negative_One(className, list);
                list = constructListOfString(className); // Clear the list
                
                // Check that the list throws IndexOutOfBoundsException 
                // when adding to index equal to its size
                test09_List_Add_Index_Size(className, list);
                list = constructListOfString(className); // Clear the list
                
                // Check that the list does not modify input type
                test10_Check_Type_Invariance(className, list);
                list = constructListOfString(className); // Clear the list
                
                // Check implementation of ListADT
                test11_Check_Class_Implements_Interface(className, list);
                list = constructListOfString(className); // Clear the list

                // Print out the number of successful and failed tests
                System.out.println("Passed Tests: " + successes + "; "
                                        + "Failed Tests: " + failures);
                // Reset failures and successes
                successes = 0;
                failures = 0;
        }

        /**
         * Prints out the error message for a method as well as the expected
         * and actual outcomes
         *
         * @param msg Method where the error occurred and extra details
         * @param input The Input Sequence being tested
         * @param expected The expected output of the List
         * @param actual The actual output of the list
         */
        private static void failMsg(String msg, String input,
                        String expected, String actual)
        {
                System.out.println("FAILED " + msg);
                System.out.println("       Input: " + input);
                System.out.println("    expected: " + expected);
                System.out.println("      actual: " + actual);
                // Default sequencing
                sequence = "";
        }

        /**
         * Determines if the List failed or succeeded the test
         *
         * @param failure If true, increment failures
         *                                If false, increment successes
         */
        private static void failCheck(boolean failure)
        {
                // Did the test fail
                if (!failed)
                        successes++; // Successful test
                else
                {
                        // Failed test
                        failures++;
                        // Reset failed boolean
                        failed = false;
                }
        }

        /**
         * creates empty List and adds one item
         * and checks that size is 1
         *
         * @param className The name of the class being tested
         * @param list The List created from the class
         */
        public static void test01_List_Add_OneItem(
                        String className, ListADT<String> list)
        {
                // Name of test
                String name = new Object(){}.getClass().getEnclosingMethod().getName();
                // Name of the class
                String ta_name = list.getClass().getName();

                list.add(ta_name);
                // Expected size of list
                int expected = 1;
                // Actual size of list
                int actual = list.size();
                if (actual != expected)
                {
                        // Input sequence
                        sequence = sequence + ta_name + " ";
                        // Failure output with test name, sequence,
                        // Expected output, and actual output
                        failMsg(name + " for " + className, "" + sequence,
                                        "" + expected, "" + actual);
                        // Test Failed
                        failed = true;
                }
                // Did the test fail or succeed
                failCheck(failed);
        }

        /**
         * Add multiple items to list and test size increases accordingly
         *
         * @param className The name of the class being tested
         * @param list The List created from the class
         */
        private static void test02_List_Add_MultipleItems(
                        String className, ListADT<String> list)
        {
                // Name of test
                String name = new Object(){}.getClass().getEnclosingMethod().getName();
                // number of items to add and check size
                int numItems = 3;

                // should be big enough to force expand
                for ( int i = 0; i < numItems; i++ )
                {
                        // String of indexes to add to the list
                        String s = "item_" + i;
                        list.add(s);
                        // Expected output
                        int expected = i + 1;
                        // Actual output
                        int actual = list.size();
                        // Does expected match actual
                        if (actual != expected)
                        {
                                // Input sequence
                                sequence = sequence + s + " ";
                                // Failure output with test name, sequence,
                                // Expected output, and actual output
                                failMsg(name + "_size()","" + sequence,
                                                "" + expected, "" + actual);
                                // Test failed
                                failed = true;
                        }
                }
                // Did the test fail or succeed
                failCheck(failed);
        }

        /**
         * Make sure items are arranged properly when both
         * add and add at index are used
         *
         * @param className The name of the class being tested
         * @param list The List created from the class
         */
        private static void test03_List_Check_Placement(
                        String className, ListADT<String> list)
        {
                // Name of test
                String name = new Object(){}.getClass().getEnclosingMethod().getName();
                // number of items to add and check size
                int numItems = 3;

                for ( int i = 0; i < numItems; i++ )
                {
                        // String of indexes to add to the list
                        String s = "item_" + i;
                        // use add(item) when i is not 2, else use add(index, item)
                        if (i != 2) // add(item)
                                list.add(s);
                        else // add(index, item)
                                if (list.size() > 0) list.add(1, s);
                }
                // Expected output
                String expected = "item_0, item_2, item_1";
                // Make sure the list can contain items
                try
                {
                        // Actual output
                        String actual = list.get(0) + ", " + list.get(1)
                                        + ", " + list.get(2);
                        // Is the list structured properly
                        if (!expected.equals(actual))
                        {
                                // Input sequence
                                sequence = sequence + "item_0, item_1, item_2";
                                // Failure output with test name, sequence,
                                // Expected output, and actual output
                                failMsg(name + "ContainsItem_","" + sequence,
                                                "" + expected,"" + actual);
                                // Test failed
                                failed = true;
                        }
                        // Did the test fail or succeed
                        failCheck(failed);
                }
                catch (Exception e)
                {
                        // Input Sequence
                        sequence = sequence + "Add Items: item_0, item_1, item_2";
                        // Failure output with test name, sequence,
                        // Expected output, and actual output
                        failMsg(name + ": Exception", "" + sequence, "" + expected, "" +e);
                        // Test failed
                        failed = true;
                        // Did the test fail or succeed
                        failCheck(failed);
                        return;
                }
        }

        /**
         * Add an item to the list then remove it
         * Test that list size is one after removal
         *
         * @param className The name of the class being tested
         * @param list The List created from the class
         */
        private static void test04_Remove_From_List(
                        String className, ListADT<String> list)
        {
                // Name of test
                String name = new Object(){}.getClass().getEnclosingMethod().getName();
                // initialSize of the array (should be 1)
                int initialSize = list.size();
                // Expected output
                String expected = "item";
                // Actual output (item at index 0)
                String actual = "";
                // Add 'item' to the empty list
                list.add(expected);

                try
                {
                        // Actual value in the list
                        actual = list.remove(0);
                }
                catch (Exception e)
                {
                        // Input Sequence
                        sequence = sequence + "Add: 'item' - Remove: null";
                        // Failure output with test name, sequence,
                        // Expected output, and actual output
                        failMsg(name + ": Exception", "" + sequence, "" + expected, "" +e);
                        // Test failed
                        failed = true;
                        // Did the test fail or succeed
                        failCheck(failed);
                        return;
                }
                if (!expected.equals(actual))
                {
                        // Failure output with test name, sequence,
                        // Expected output, and actual output
                        failMsg(name, "" + sequence, "" + expected, "" + actual);
                        // Test Failed
                        failed = true;
                }
                // Expected size is back to the original
                expected = ""+initialSize;
                // Actual size of the list after removal
                actual = list.size()+"";

                if (!expected.equals(actual))
                {
                        // Failure output with test name, sequence,
                        // Expected output, and actual output
                        failMsg(name+" size()", "" + sequence, ""+expected,""+actual);
                        // Test Failed
                        failed = true;
                }
                // Did the test fail or succeed
                failCheck(failed);
        }

        /**
         * Create an iterator using the class' iterator and ArrayList iterator
         * Then compare the two and make sure class' iterator matches
         * the ArrayList iterator
         *
         * @param className The name of the class being tested
         * @param list The List created from the class
         */
        private static void test05_Use_List_Iterator(
                        String className, ListADT<String> list)
        {
                // Name of test
                String name = new Object(){}.getClass().getEnclosingMethod().getName();
                int numItems = 11; // number of items to add
                // Array List to test List Iterator
                ArrayList<String> arrayList = new java.util.ArrayList<String>();
                // Input Sequence
                String input = "item_0 -> item_30";
                // Add items to both lists
                for ( int i = 0; i < numItems; i++ )
                {
                        // String of an item's index
                        String s = "item_"+i;
                        // Class' list
                        list.add(s);
                        // ArrayList list
                        arrayList.add(s);
                }
                // Iterator constructed through the class
                Iterator<String> itr = list.iterator();
                // Iterator constructed through the ArrayList
                Iterator<String> aItr = arrayList.iterator();
                // Check for the iterator
                if (itr == null)
                {
                        // Failure output with test name, sequence,
                        // Expected output, and actual output
                        failMsg(name + "_no_iterator", "" + input,
                                        "valid iterator",""+itr);
                        // Test Failed
                        failed = true;
                }
                // Check for the iterator
                if (aItr == null)
                {
                        // Failure output with test name, sequence,
                        // Expected output, and actual output
                        failMsg(name + "_no_iterator", "" + input,
                                        "valid iterator", "" + aItr);
                        // Test Failed
                        failed = true;
                }
                while (itr.hasNext() && aItr.hasNext())
                {
                        // Expected output
                        boolean expected = true;
                        // Next item in the ArrayList iterator
                        Object aObj = aItr.next();
                        try
                        {
                                // Next item in the class iterator
                                Object obj = itr.next();
                                // Actual output
                                boolean actual = obj.equals(aObj);
                                if ( expected != actual )
                                {
                                        // Failure output with test name, sequence,
                                        // Expected output, and actual output
                                        failMsg(name + "_iterator_error: item in iterator is not "
                                                        + "correct", "" + input, "" + aObj, "" + obj);
                                        // Test failed
                                        failed = true;
                                }
                        }
                        catch (Exception e)
                        {
                                // Failure output with test name, sequence,
                                // Expected output, and actual output
                                failMsg(name + "_iterator_error: exception thrown", ""
                                                        + input, "" + aObj, "" + e);
                                // Test failed
                                failed = true;
                                // Did the test fail or succeed
                                failCheck(failed);
                                return;
                        }

                }
                if ( itr.hasNext() )
                {
                        // Failure output with test name, sequence,
                        // Expected output, and actual output
                        failMsg(name+"_iterator_error: item in iterator but should not be",
                                                "" + input, " ", itr.next() );
                        // Test failed
                        failed = true;
                }
                if ( aItr.hasNext() )
                {
                        // Failure output with test name, sequence,
                        // Expected output, and actual output
                        failMsg(name+"_iterator_error: item not in iterator but should be",
                                                "" + input, aItr.next()," ");
                        // Test failed
                        failed = true;
                }
                // Did the test fail or succeed
                failCheck(failed);
        }

        /**
         * Create an Integer List and an Object list to make sure the list
         * makes use of generic data types
         *
         * @param className The name of the class being tested
         * @param list The List created from the class
         */
        private static void test06_Check_Java_Generics(
                        String className, ListADT<String> list)
        {
                // Name of the test
                String name = new Object(){}.getClass().getEnclosingMethod().getName();
                try
                {
                        // Get the class for the given name
                        Class<?> listClassName = Class.forName(className);
                        // Get the class' constructor
                        Constructor<?> ctr = listClassName.getDeclaredConstructor();
                        // New Array of arguments
                        Object [] initargs = new Object[] { };
                        // CAUTION: Type cast warnings are suppressed here.
                        // to constructing Lists that will contain String data items.
                        @SuppressWarnings("unchecked")
                        // Create a list of integers
                        ListADT<Integer> intList = ((ListADT<Integer>)
                                        ctr.newInstance(initargs));
                        // Add an integer to the list
                        intList.add(20);
                        // CAUTION: Type cast warnings are suppressed here.
                        // to constructing Lists that will contain String data items.
                        @SuppressWarnings("unchecked")
                        // Create a list of objects
                        ListADT<Object> objList = (ListADT<Object>)
                                        ctr.newInstance(initargs);
                        // Add an object to the list
                        objList.add(new Test_ListImplementations());
                }
                catch (Exception e)
                {
                        failMsg(name + ": Literals used instead of Generics",
                                        "Added: 20 (Integer)", "IntegerList","" + e);
                        // Test failed
                        failed = true;
                        // Did the test fail or succeed
                        failCheck(failed);
                        return;
                }
                // Did the test fail or succeed
                failCheck(failed);
        }
        
        /**
         * Attempt to add null to the list in order to verify that 
         * null is checked as invalid input
         * 
         * @param ClassName the name of the class being tested
         * @param list the list created from the class
         */
        private static void test07_List_Add_Null(String className, ListADT<String> list ) {
        	// Name of test
        	String name = new Object(){}.getClass().getEnclosingMethod().getName();
        	
        	try {
        		
        		// Trigger determining pass/fail of the test
                list.add(null);
        		
        		// Test failed if this point is reached without throwing an exception
        		failMsg(name + ": Accepted null as a valid input value", "null", 
        						"IllegalArgumentException", "No Exception thrown" );
        		failed = true;
        		// Report status of test
        		failCheck(failed);
        		return;
        		
        	} catch ( Exception e ) {
        		// Report status of test
        		failCheck(failed);
        		return;
        	}
        }
        
        /**
         * Attempt to add a value at an index of negative one to verify
         * proper index management
         * 
         * @param ClassName the name of the class being tested
         * @param list the list created from the class
         */
        private static void test08_List_Add_Index_Negative_One(String className, ListADT<String> list ) {
        	// Name of Test
        	String name = new Object(){}.getClass().getEnclosingMethod().getName();
        	
        	try {
        		list.add("item_0");
        		// Trigger to throw IndexOutOfBoundsException
        		list.add(-1, "item_-1");
        		
        		// Test failed if this point is reached without throwing an exception
        		failMsg(name + ": Added to index of -1", "Add items: item_0; Add at index -1: item_-1", 
        						"IndexOutOfBoundsException", "No Exception thrown" );
        		failed = true;
        		// Report status of test
        		failCheck(failed);
        		return;
        		
        	} catch ( Exception e ) {
        		// Report status of test
        		failCheck(failed);
        		return;
        	}
        }
        
        /**
         * Attempt to add a value at an index equal to the size of the list
         * to ensure use of zero based indexing and proper index usage
         * 
         * @param ClassName the name of the class being tested
         * @param list the list created from the class
         */
        private static void test09_List_Add_Index_Size(String ClassName, ListADT<String> list ) {
        	// Name of Test
        	String name = new Object(){}.getClass().getEnclosingMethod().getName();
        	
        	try {
        		// Initialize list to size of 2
        		list.add("item_0");
        		list.add("item_1");
        		
        		// Trigger to throw IndexOutOfBoundsException
        		list.add(2, "item_2");
        		
        		// Test failed if this point is reached without throwing an exception
        		failMsg(name + ": Added to index equal to list size", 
        						"Add Items: item_0, item_1; Add at index 2: item_2", 
        						"IndexOutOfBoundsException", "No Exception thrown" );
        		failed = true;
        		// Report status of test
        		failCheck(failed);
        		return;
        		
        	} catch ( Exception e ) {
        		// Report status of test
        		failCheck(failed);
        		return;
        	}
        }
        
        /**
         * Add value of some class to the list, then retrieve from list to verify
         * data types do not get changed within the class
         * 
         * @param ClassName the name of the class being tested
         * @param list the list created from the class
         */
        private static void test10_Check_Type_Invariance(String className, ListADT<String> list ) {
        	// Name of test
        	String name = new Object(){}.getClass().getEnclosingMethod().getName();
        	
        	try {
        		// Get the class for the given name
                Class<?> listClassName = Class.forName(className);
                // Get the class' constructor
                Constructor<?> ctr = listClassName.getDeclaredConstructor();
                // New Array of arguments
                Object [] initargs = new Object[] { };
                // CAUTION: Type cast warnings are suppressed here.
                // to constructing Lists that will contain String data items.
                @SuppressWarnings("unchecked")
                // Create a list of integers
                ListADT<Integer> intList = ((ListADT<Integer>) ctr.newInstance(initargs));
        		
                // throw exception if remove does not exist
        		listClassName.getMethod("get", int.class);
        		
        		// Add integers to the list
        		intList.add(20);
        		intList.add(25);
        		
	        	// Retrieve values from the list
	        	Object val = intList.get(1);
	        	
	        	// Check class type of returned value
	        	if ( !val.getClass().equals(Integer.class) ) {
	        		// Report failure if type mismatch
	        		failMsg(name + ": Class type changed within list",
                            "Added: 20, 25 (Integer)", "Class Match: Integer", "" + val.getClass().getName() );
	        		failed = true;
	        	}
	        	// Report status of test
	        	failCheck(failed);
	        	return;
        				
        	} catch (Exception e) {
        		// Report test error
        		failMsg(name + ": Unable to complete test", "Added: 20, 25 (Integer)", "Class Match: Integer", "N/A");
        		return;
        	}
        }
        
        /**
         * Gets interfaces used by the class and ensures it implements exactly one interface,
         * that is the ListADT interface
         * 
         * @param ClassName the name of the class being tested
         * @param list the list created from the class
         */
        private static void test11_Check_Class_Implements_Interface(String className, ListADT<String> list ) {
        	// Name of test
        	String name = new Object(){}.getClass().getEnclosingMethod().getName();

        	try {
        		// Get the class for the given name
        		Class<?> listClassName = Class.forName(className);
        		// Get the interfaces used in the class
        		Class<?>[] interfaces = listClassName.getInterfaces();
        		// Check that class used exactly one interface
        		if (interfaces.length == 1) {
	        		// Check that correct interface was used
	        		if ( !interfaces[0].getName().equals("ListADT") ) {
	        			
	        			failMsg(name + ": Does not implement ListADT Interface",
	        				"Get Interfaces", "ListADT","" + interfaces[0]);
	        			failed = true;
	        		}
        		}
        		// Report status of test
        		failCheck(failed);
        		return;

        	} catch (ClassNotFoundException e) {
        		e.printStackTrace();
        	}

        }
        
}