package dataStructure;

import util.Constant;
import util.ExcelUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class API {

    public static HashMap<String, ArrayList<String>> apiDB;

    public API(String SheetName) throws Exception {
        ExcelUtil.setExcelFile(Constant.Path_TestData + Constant.File_TestData, SheetName);

        apiDB = new HashMap<String, ArrayList<String>>();
        for (int row = 1; row <= Constant.rowNumber; row++) {
            //Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String statusCode = ExcelUtil.getCellData(row, Constant.statusCodeIdx);
            String message = ExcelUtil.getCellData(row, Constant.messageIdx);
            String request = ExcelUtil.getCellData(row, Constant.requestIdx);
            ArrayList<String> response = new ArrayList<String>();
            response.add(0, statusCode);
            response.add(1, message);

            apiDB.put(request, response);
        }

    }

    public static ArrayList<String> AcceptRequest(String request) throws Exception {

        ArrayList<String> res = new ArrayList<String>();

        if (apiDB.containsKey(request)) {
            res = apiDB.get(request);
        }

        return res;
    }

}



