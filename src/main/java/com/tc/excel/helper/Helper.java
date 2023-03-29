package com.tc.excel.helper;

import com.tc.excel.entity.Student;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

public class Helper {
    public static boolean checkExcelFotmat(MultipartFile file){
        String contentType= file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        }else{
            return false;
        }
    }
    public static List<Student> convertExcelToListOfStudent(InputStream is){
        try{
            XSSFWorkbook workbook=new XSSFWorkbook(is);
            is.close();
            XSSFSheet sheet=workbook.getSheet("sheet1");
            XSSFRow row= sheet.getRow(0);
            XSSFCell cell=null;
            Map<Integer,String> hm = new HashMap<Integer,String>();
           for(int i=0;i<sheet.getRow(0).getLastCellNum();i++) {
               cell = row.getCell(i);
               hm.put(i,cell.getStringCellValue());
           }
           for(int i=1;i<=sheet.getLastRowNum();i++){
               row=sheet.getRow(i);
               Student s=new Student();
               for(int j=0;j<row.getLastCellNum();j++){
                   cell= row.getCell(j);
                   int index=cell.getColumnIndex();
                   System.out.println(hm.get(index));
               }
           }



        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
//    public static List<Student> convertExcelToListOfStudents(InputStream is) {
//        List<Student> list = new ArrayList<>();
//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook(is);
//            is.close();
//            XSSFSheet sheet = workbook.getSheet("sheet1");
//            int rowNumber = 0;
//            Iterator<Row> iterator = sheet.iterator();
//            while (iterator.hasNext()) {
//                Row row = iterator.next();
//                if (rowNumber == 0) {
//                    rowNumber++;
//                    continue;
//                }
//                Iterator<Cell> cells = row.iterator();
//                int cid = 0;
//                Student s = new Student();
//                while (cells.hasNext()) {
//                    Cell cell = cells.next();
//                    switch (cid) {
//                        case 0:
//                            s.setStudentId((int) cell.getNumericCellValue());
//                            break;
//                        case 1:
//                            s.setStudentName(cell.getStringCellValue());
//                            break;
//                        case 2:
//                            s.setStudentDept(cell.getStringCellValue());
//                            break;
//                        default:
//                            break;
//                    }
//                    cid++;
//                }
//                list.add(s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}
