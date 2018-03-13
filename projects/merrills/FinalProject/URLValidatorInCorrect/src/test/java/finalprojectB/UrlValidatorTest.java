
package finalprojectB;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;
//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {
	
	String[][] schemes={
		{"http://","true"},
		{"https://","true"},
		{"ftp://","true"},
		{"file://","true"},
		{"http","false"},
		{"http:","false"},
		{"http:/","false"},
		{"http/:","false"},
		{"http/","false"},	
		{"","true"},	
		{"://","true"}	
	};

	int numSchemes = 11;

	String[][] ports={
		{":80","true"},
		{"","true"},
		{":","false"},
		{"80","false"},
		{":-1","false"},
		{":1000000000","false"},
		{":0","true"},
		{":dx","false"},
		{":80dx","false},
		{":/","false},
		{":80/","false},
		{":80&","false}
	};

	int numPorts = 12;

	String[][] paths={
   		{"","true"},
   		{"/","true"},
   		{"/path","true"},
   		{"/path/morepath","true"},
   		{"/path/morepath/","true"},
   		{"path/morepath/","false"},
   		{"path","false"},
   		{"/path/morepath","true"},
   		{"/path/more_path","true"},
   		{"/path/more-path","true"},
   		{"/pa_th","true"},
   		{"/pa-th","true"},
   		{"/path$","true"},
   		{"/..","false"},
   		{"/../","false"}
	};

	int numPaths = 15;

	String[][] queries={
		{"?action=view","true"},
		{"?action=edit&mode=up","true"},		
		{"","true"},
		{"?action =view","false"},
		{"?action^=view","false"},
		{"?act>ion=view","false"},
		{"?act<ion=view","false"},
		{"?act}ion=view","false"}
	};	

	int numQueries = 8;

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
	   //assertTrue(validator.isValid("https://j.mp"));
	   //assertTrue(validator.isValid("ftp://j.mp")); //Exception in initializer error
	   //assertTrue(validator.isValid("ftps://foo.bar")); //Exception in initializer error
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
	   
			   
	   
   }
   
  @Test
   public void testPathPartitions() throws Throwable {
	UrlValidator validator = new UrlValidator(1 << 0);

	//Path
	assertTrue(validator.isValid("http://google.com")); //Without www
	assertTrue(validator.isValid("http://www.google.com")); //With www
	assertTrue(validator.isValid("http://google.com/path")); //With path
	//assertTrue(validator.isValid("http://google.com/path/morepath")); //With more path

	// / at end
	assertTrue(validator.isValid("http://google.com/")); //Without www
	assertTrue(validator.isValid("http://www.google.com/")); //With www
	//assertTrue(validator.isValid("http://google.com/path/")); //With path
	//assertTrue(validator.isValid("http://google.com/path/morepath/")); //With more path

	// _ in paths
	assertTrue(validator.isValid("http://google.com/pa_th/")); //With path
	assertTrue(validator.isValid("http://google.com/path/more_path/")); //With more path
	assertTrue(validator.isValid("http://google.com/pa_th/morepath/")); //With more path
	assertTrue(validator.isValid("http://google.com/pa_th/more_path/")); //With more path

	//Path has non-alpha chars
	assertTrue(validator.isValid("http://google.com/path")); //With path

   }
   
  	@Test
  	public void testEmptyAuthorityPartition(){
		UrlValidator validator = new UrlValidator(1 << 0);

	   
		//Empty authority with file:
		//assertTrue(validator.isValid("file://")); //Exception initializer

		//Empty authority with http: 
		//assertFalse(validator.isValid("http://")); //ERROR
		
		//Empty authority with https:
		//assertFalse(validator.isValid("https://")); //Exception initializer
		//Empty authority with ftp:
		//assertFalse(validator.isValid("ftp://")); //Exception initializer

   	}
  	
	
	@Test
  	public void testSchemePartition(){
		UrlValidator validator = new UrlValidator();

		assertTrue(validator.isValid("http://google.com/")); //Without www
		assertTrue(validator.isValid("https://google.com/")); //EI ERROR
		assertTrue(validator.isValid("ftp://google.com/")); //Without www
		assertFalse(validator.isValid("blarg://google.com/")); //Without www
	   	assertFalse(validator.isValid("HTTP://google.com/"));

   	}
   //You need to create more test cases for your Partitions if you need to 
   	@Test 
	public void testPermutations() {
		//For test permutaions, iterate through each set of url parts and test
		//Output to file expected and actual result based on true or fales second string
		//If any of the url parts are false, output expected false to file
		//Then output actual result
		int i;
		int j;
		int k;
		int l;
		String url;
		for (i = 0; i < numSchemes; i++) {
			for (j = 0; j < numPorts; j++) {
				for (k = 0; k < numPaths; k++) {
					for (l = 0; l < numQueries) {
						url = schemes[i][0] + ports[j][0] + paths[k][0] + queries[l][0];
					}
				}
			}	
		}



	}
   


}
