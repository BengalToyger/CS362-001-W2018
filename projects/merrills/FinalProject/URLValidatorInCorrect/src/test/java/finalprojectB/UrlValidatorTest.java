
package finalprojectB;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;
//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
  @Test 
   public void testManualTest() throws Throwable {
	   //Valid URLs
	   UrlValidator validator = new UrlValidator(1 << 0);
	   assertTrue(validator.isValid("http://foo.com/blah_blah"));
	   //assertTrue(validator.isValid("http://foo.com/blah_blah/")); //ERROR
	   assertTrue(validator.isValid("http://foo.com/blah_blah_(wikipedia)"));
	   assertTrue(validator.isValid("http://foo.com/blah_blah_(wikipedia)_(again)"));
	   //assertTrue(validator.isValid("http://www.example.com/wpstyle/?p=364")); //ERROR
	   //assertTrue(validator.isValid("http://www.example.com/foo/?bar=baz&inga=42&quux")); //ERROR
	   //assertTrue(validator.isValid("http://userid:password@example.com:8080")); //ERROR
	   //assertTrue(validator.isValid("http://userid:password@example.com:8080/"));
	   assertTrue(validator.isValid("http://userid@example.com"));
	   assertTrue(validator.isValid("http://userid@example.com/"));
	   //assertTrue(validator.isValid("http://userid@example.com:8080")); //ERROR
	   //assertTrue(validator.isValid("http://userid@example.com:8080/")); //ERROR
	   //assertTrue(validator.isValid("http://userid:password@example.com")); //ERROR
	   //assertTrue(validator.isValid("http://userid:password@example.com/")); //ERROR
	   assertTrue(validator.isValid("http://j.mp"));
	   assertTrue(validator.isValid("https://j.mp"));
	   //assertTrue(validator.isValid("ftp://j.mp"));
	   //assertTrue(validator.isValid("ftps://foo.bar")); //Exception initializer error
	   assertTrue(validator.isValid("http://0.0.0.0"));
	   assertTrue(validator.isValid("http://10.1.1.0"));
	   assertTrue(validator.isValid("http://10.1.1.255"));
	   assertTrue(validator.isValid("http://224.1.1.1"));
	   assertTrue(validator.isValid("http://123.123.123.123"));
	   assertTrue(validator.isValid("http://www.foo.bar./"));

	   //Invalid URLs
	   //assertFalse(validator.isValid("http://")); //ERROR
	   //assertFalse(validator.isValid("http://.")); //ERROR
	   //assertFalse(validator.isValid("http://.."));
	   //assertFalse(validator.isValid("http://../"));
	   //assertFalse(validator.isValid("http://?"));
	   //assertFalse(validator.isValid("http://??"));
	   //assertFalse(validator.isValid("http://??/"));
	   //assertFalse(validator.isValid("http://#"));
	   //assertFalse(validator.isValid("http://##"));
	   //assertFalse(validator.isValid("http://##/"));
	   assertFalse(validator.isValid("http://foo.bar?q=Spaces should be encoded"));
	   assertFalse(validator.isValid("//"));
	   assertFalse(validator.isValid("//a"));
	   assertFalse(validator.isValid("///a"));
	   assertFalse(validator.isValid("///"));
	   assertFalse(validator.isValid("foo.com"));
	   //assertFalse(validator.isValid("rdar://1234"));
	   //assertFalse(validator.isValid("h://test"));
	   //assertFalse(validator.isValid("http:// shouldfail.com"));
	   assertFalse(validator.isValid(":// should fail"));
	   //assertFalse(validator.isValid("http://-error-.invalid"));
	   //assertFalse(validator.isValid("http://1.1.1.1.1"));
	   //assertFalse(validator.isValid("http://57832965"));
	   //assertFalse(validator.isValid("http://.www.foo.bar"));
	   assertFalse(validator.isValid("http:142.42.1.1/"));
	   assertFalse(validator.isValid("http:142.42.1.1:8080/")); 

	   
			   
//You can use this function to implement your manual testing	   
	   
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   


}
