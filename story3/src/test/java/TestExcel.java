import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import entities.User;

public class TestExcel {
       // record the the output of the class
//       static Log log = LogFactory.getLog(TestExcel.class); 
       // get the path of the excel file
       public static String filePath = "/home/user1/conygreJEE/solutions/JavaEE6Workspace/story3/src/main/webapp/Users.xls";
       
       
       public static void main(String[] args){
    	   
    	   try {
               // Create input stream for EXCEL
               HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filePath));
               // the default indexing of 1st sheet in Excel is "0"
               // The syntax is：HSSFSheet sheet = workbook.getSheetAt(0);
               HSSFSheet sheet = wookbook.getSheet("Sheet1");
               // getting the number of rows in the Excel file
               int rows = sheet.getPhysicalNumberOfRows();
               System.out.println(rows+"rows");
               // scan through all the rows
               for (int i = 0; i < rows; i++) {
                     // read the top left unit cell
                     HSSFRow row = sheet.getRow(i);
                     // if the row is not empty
                     if (row != null) {
                           // getting all the columns in the Excel file
                           int cells = row.getPhysicalNumberOfCells();
                           System.out.println(cells+"columns");
                           String value = "";
                           // scan through all the columns
                           for (int j = 0; j < cells; j++) {
                                 // get the data of the cell
                                 HSSFCell cell = row.getCell(j);
                                 if (cell != null) {
                                       switch (cell.getCellType()) {
                                             case HSSFCell.CELL_TYPE_NUMERIC:
                                                   value += cell.getNumericCellValue() + ",";
                                             break; 
                                             case HSSFCell.CELL_TYPE_STRING:
                                                   value += cell.getStringCellValue() + ",";
                                             break;
//                                             default:
//                                                   value += "0";
//                                             break;
                                 }

                                 }  
                     }
                     
                     
                     
                     String[] val = value.split(",");
                     System.out.println(val.length);
                     for(String string : val){
                    	 System.out.println(string);
                     }
                     
                     double doubleId = Double.parseDouble(val[0]);
                     int intId=(int)doubleId;
                     System.out.println(intId);
//                  // load data into mysql database
                     User user = new User();
//                  //now conveting the data
                     user.setId(intId);
                     user.setUsername(val[1]);
                     user.setPassword(val[2]);
                     user.setUsertype(val[3]);
                     
                     System.out.println(user.getId()+user.getPassword()+user.getUsername()+user.getUsertype());
                     }
               }
    	   }catch (FileNotFoundException e) {
               e.printStackTrace();
           }catch (IOException e) {
               e.printStackTrace();
         }

       }
}