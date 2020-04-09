package testCases;

import dataStructure.API;
import dataStructure.TestCase;
import org.testng.annotations.Test;
import util.Constant;
import util.ExcelUtil;

import java.util.ArrayList;
import java.util.Map;

public class TestAPI {
    @Test
    public void testAPI() throws Exception {

        API api = new API("APIDB");
        TestCase testCase = new TestCase("TestCase");

       int row = 1;
        for (Map.Entry<String, ArrayList<String>> entry : testCase.testCaseApiDB.entrySet()) {
            System.out.println("----------------------------------");

            String testCase_request = entry.getKey();
            ArrayList<String> testCase_expect_result = entry.getValue();
            ArrayList<String> api_result =  api.AcceptRequest(testCase_request);

//
//            System.out.println(testCase_request);
//            System.out.println(testCase_expect_result);
//            System.out.println(api_result);
            System.out.println("----------------------------------");

            System.out.println("Test expect result: " + testCase_expect_result.get(0));
            System.out.println("API result: " + api_result.get(0));

            //Test Report variables
            String statusCodeTestResult;
            String MessageCodeTestResult;

            //Verify and Compare Data between TestCase Data and APIDB Data
            if(api_result.isEmpty()){
                System.out.println("NULLLLL");
                System.out.print("***********");
            }
            else{
                System.out.println("NOT NULLLLL");
            }

            if ( testCase_expect_result.get(0).equals(api_result.get(0))){
                System.out.println("Status Code passed!");
                statusCodeTestResult = "PASS";
            }
            else{
                System.out.println("Status Code failed!");
                statusCodeTestResult = "Failed";
                System.out.print("%%%%%%%");
            }

            if ( testCase_expect_result.get(1).equals(api_result.get(1))){
                System.out.println("Message passed!");

                MessageCodeTestResult = "PASS";
            }
            else{
                System.out.println("Message failed!");
                MessageCodeTestResult = "Failed";
                System.out.print("%%%%%%%%%%%%%%%");
            }

    // Write Result Data into TestReport excel file
    ExcelUtil.setExcelFile(Constant.Path_TestData+ Constant.File_TestData,"TestReport");
            System.out.println("row number is : " + row);
            System.out.println("request is :" + testCase_request);
            System.out.println("StatusCodeResult: " + statusCodeTestResult);
            System.out.println("Message TestResult: " + MessageCodeTestResult);

    ExcelUtil.setCellData(testCase_request,row,0);
    ExcelUtil.setCellData(statusCodeTestResult,row,1);
    ExcelUtil.setCellData(MessageCodeTestResult,row,2);

    row++;


        }


    }

}
