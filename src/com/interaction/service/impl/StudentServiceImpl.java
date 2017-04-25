package com.interaction.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.interaction.dao.ClassDAO;
import com.interaction.dao.CourseDAO;
import com.interaction.dao.SeminarclassDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Class;
import com.interaction.pojo.Course;
import com.interaction.pojo.Seminarclass;
import com.interaction.pojo.Student;
import com.interaction.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Resource
	private StudentDAO studentDAOImpl;
	@Resource
	private SeminarclassDAO seminarclassDAOImpl;
	@Resource
	private ClassDAO classDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	
	
	public int addStudent(Student student) {
		return studentDAOImpl.addStudent(student);
	}

	public Student findById(Integer SId) {
		return studentDAOImpl.findById(SId);
	}
	public List<Student> findAll(){
		return studentDAOImpl.findAll();
	}

	//根据学生用户名查找学生
	@Override
	public Student findBySaccount(String saccount) {
		if (saccount == null) {
			return null;
		}
		return studentDAOImpl.findBySaccount(saccount);
	}

	//学生签到
	@Override
	public int stuSignIn(int cid, int seid, int sid) {
		Seminarclass seminarclass = seminarclassDAOImpl.findByCEE(cid,seid,sid);
		if (seminarclass != null) {
			seminarclass.setIsLogin(1);
			return seminarclassDAOImpl.updateSeminarclass(seminarclass);
		}
		return -1;
	}

	//读取excel中学生信息
	@Override
	public List<Student> readReport(InputStream inputStream) {
		 List<Student> studentList = new ArrayList<Student>();  
		  
	        try {  
	            String cellStr = null;  
	  
	            Workbook wb = WorkbookFactory.create(inputStream);  
	  
	            Sheet sheet = wb.getSheetAt(0);// 取得第一个sheets  
	  
	            //从第四行开始读取数据  
	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  
	  
	                Student student = new Student();  
	                Student addStudent = new Student();  
	  
	                Row row = sheet.getRow(i); // 获取行(row)对象  
	                if (row == null) {  
	                    // row为空的话,不处理  
	                    continue;  
	                }  
	  
	                for (int j = 0; j < row.getLastCellNum(); j++) {  
	  
	                    Cell cell = row.getCell(j); // 获得单元格(cell)对象  
	  
	                    // 转换接收的单元格  
	                    cellStr = ConvertCellStr(cell, cellStr);  
	  
	                    // 将单元格的数据添加至一个对象  
	                    addStudent = addingStudent(j, student, cellStr);  
	  
	                }  
	                // 将添加数据后的对象填充至list中  
	                studentList.add(addStudent);  
	            }  
	  
	        } catch (InvalidFormatException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (inputStream != null) {  
	                try {  
	                    inputStream.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            } else {  
	                
	            }  
	        }  
	        return  studentList;  
	}
	
	/** 
     * 获得单元格的数据添加至computer 
     *  
     * @param j 
     *            列数 
     * @param student 
     *            添加对象 
     * @param cellStr 
     *            单元格数据 
     * @return 
     */  
    private Student addingStudent(int j, Student student, String cellStr) {  
        switch (j) {  
        case 0: 
        	student.setSaccount(cellStr);  
            student.setSpwd(cellStr);
            break;  
        case 1:  
            student.setSname(cellStr);
            break;  
        case 2:  
            break;  
        }
        return student;  
    }  
	
	//把单元格内的数据转换成String
	private String ConvertCellStr(Cell cell, String cellStr) {  
		  
        switch (cell.getCellType()) {  
  
        case Cell.CELL_TYPE_STRING:  
            // 读取String  
            cellStr = cell.getStringCellValue().toString();  
            break;  
  
        case Cell.CELL_TYPE_BOOLEAN:  
            // 得到Boolean对象的方法  
            cellStr = String.valueOf(cell.getBooleanCellValue());  
            break;  
  
        case Cell.CELL_TYPE_NUMERIC:  
  
            // 先看是否是日期格式  
            if (DateUtil.isCellDateFormatted(cell)) {  
  
                // 读取日期格式  
                cellStr = cell.getDateCellValue().toString();  
  
            } else {  
  
                // 读取数字  
                cellStr = String.valueOf(cell.getNumericCellValue());  
            }  
            break;  
  
        case Cell.CELL_TYPE_FORMULA:  
            // 读取公式  
            cellStr = cell.getCellFormula().toString();  
            break;  
        }  
        return cellStr;  
    }  

	//将学生信息插入到学生表中
	@Override
	public int insertStudents(List<Student> list,Integer cid) {
		Course course = courseDAOImpl.findById(cid);
		if (course == null) {
			return -1;
		}
		
		int result = -1;
		int sid = 0;
		for(Student student : list){
			Student judeStudent = studentDAOImpl.findBySaccount(student.getSaccount());
			Class class1 = new Class();
			if (judeStudent == null) {
				sid = studentDAOImpl.addStudent(student);
				if (sid != -1) {
					class1.setCourse(course);
					class1.setStudent(student);
					result = classDAOImpl.addClass(class1);
				}
			}else {
				Class class2 = classDAOImpl.listByCidAndSid(cid,judeStudent.getSid());
				if (class2 == null) {
					class1.setCourse(course);
					class1.setStudent(judeStudent);
					result = classDAOImpl.addClass(class1);
				}
			}
			if (result == -1) {
				return result;
			}
		}
		return result;
	}
	
	@Override
	public List<Student> listStudentByCid(Integer cid) {
		List<Class> classes = classDAOImpl.listByCid(cid);
		List<Student> students = new ArrayList<Student>();
		for(Class class1:classes){
			Student student = studentDAOImpl.findById(class1.getStudent().getSid());
			students.add(student);
		}
		return students;
	}
	
}
