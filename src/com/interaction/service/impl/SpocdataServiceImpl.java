package com.interaction.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.interaction.dao.CourseDAO;
import com.interaction.dao.SeminarDAO;
import com.interaction.dao.SpocdiscussDAO;
import com.interaction.dao.SpocscoreDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Course;
import com.interaction.pojo.Spocdiscuss;
import com.interaction.pojo.Spocscore;
import com.interaction.pojo.Student;
import com.interaction.service.SpocdataService;
import com.interaction.utils.ImportDataUtil;

@Service
public class SpocdataServiceImpl implements SpocdataService{
	
	@Resource
	private SpocscoreDAO spocscoreDAOImpl;
	@Resource
	private SpocdiscussDAO spocdiscussDAOImpl;
	@Resource
	private StudentDAO studentDAOImpl;
	@Resource
	private SeminarDAO seminarDAOImpl;
	@Resource
	private CourseDAO courseDAOImpl;
	
	//读取excel表格中内容，转换成List<Spocscore>
	@Override
	public List<Spocscore> importSpocscore(InputStream inputStream){
		 List<Spocscore> spocscores = new ArrayList<Spocscore>();  
		  
	        try {  
	            String cellStr = null;  
	  
	            Workbook wb = WorkbookFactory.create(inputStream);  
	  
	            Sheet sheet = wb.getSheetAt(0);// 取得第一个sheets  
	  
	            //从第四行开始读取数据  
	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  
	  
	            	Spocscore spocscore = new Spocscore();  
	            	Spocscore addSpocscore = new Spocscore();  
	  
	                Row row = sheet.getRow(i); // 获取行(row)对象  
	                if (row == null) {  
	                    // row为空的话,不处理  
	                    continue;  
	                }  
	  
	                for (int j = 0; j < row.getLastCellNum(); j++) {  
	  
	                    Cell cell = row.getCell(j); // 获得单元格(cell)对象  
	  
	                    // 转换接收的单元格  
	                    cellStr = ImportDataUtil.ConvertCellStr(cell, cellStr);  
	  
	                    // 将单元格的数据添加至一个对象  
	                    addSpocscore = addingSpocscore(j, spocscore, cellStr);  
	  
	                }  
	                // 将添加数据后的对象填充至list中  
	                if (addSpocscore.getStudent() != null) {
	                	spocscores.add(addSpocscore);
					}
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
	        return  spocscores;  
	}
	//excel中的每一行转换成Spocscore实体
	private Spocscore addingSpocscore(int j, Spocscore spocscore, String cellStr) {
		
		switch (j) {  
        case 0: 
            break;  
        case 1:  
            break;  
        case 2: 
        	Student student = studentDAOImpl.findBySaccount(cellStr);
        	if (student != null) {
				spocscore.setStudent(student);
			}
            break;
        case 3:
        	break;
        case 4:
        	break;
        case 5:
        	spocscore.setScore1(Double.parseDouble(cellStr));
        	break;
        case 6:
        	spocscore.setScore2(Double.parseDouble(cellStr));
        	break;
        }
		
        return spocscore;  
	}
	
	//将list插入到spocscore表中
	@Override
	public int insertSpocscore(List<Spocscore> spocscores, Integer seId) {
		int result = -1;
		for(Spocscore spocscore:spocscores){
			if (spocscore != null) {
				spocscore.setSeminar(seminarDAOImpl.findById(seId));
				result = spocscoreDAOImpl.addSpocscore(spocscore);
				if (result == -1) {
					break;
				}
			}
		}
		return result;
	}
	
	//导入学生讨论区数据
	@Override
	public List<Spocdiscuss> importSpocdiscuss(InputStream inputStream) {
		List<Spocdiscuss> spocdiscusses = new ArrayList<Spocdiscuss>();  
		  
        try {  
            String cellStr = null;  
  
            Workbook wb = WorkbookFactory.create(inputStream);  
  
            Sheet sheet = wb.getSheetAt(0);// 取得第一个sheets  
  
            //从第四行开始读取数据  
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  
  
            	Spocdiscuss spocdiscuss = new Spocdiscuss();  
            	Spocdiscuss addSpocdiscuss = new Spocdiscuss();  
  
                Row row = sheet.getRow(i); // 获取行(row)对象  
                if (row == null) {  
                    // row为空的话,不处理  
                    continue;  
                }  
  
                for (int j = 0; j < row.getLastCellNum(); j++) {  
  
                    Cell cell = row.getCell(j); // 获得单元格(cell)对象  
  
                    // 转换接收的单元格  
                    cellStr = ImportDataUtil.ConvertCellStr(cell, cellStr);  
  
                    // 将单元格的数据添加至一个对象  
                    addSpocdiscuss = addingSpocdiscuss(j, spocdiscuss, cellStr);  
  
                }  
                // 将添加数据后的对象填充至list中  
                if (addSpocdiscuss.getStudent() != null) {
                	spocdiscusses.add(addSpocdiscuss);
				}
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
        return  spocdiscusses; 
	}
	
	private Spocdiscuss addingSpocdiscuss(int j, Spocdiscuss spocdiscuss, String cellStr) {
		switch (j) {  
        case 0: 
            break;  
        case 1:  
            break;  
        case 2: 
        	Student student = studentDAOImpl.findBySaccount(cellStr);
        	if (student != null) {
        		spocdiscuss.setStudent(student);
			}
            break;
        case 3:
        	break;
        case 4:
        	spocdiscuss.setSubject(Integer.parseInt(cellStr));
        	break;
        case 5:
        	spocdiscuss.setReplay(Integer.parseInt(cellStr));
        	break;
        case 6:
        	spocdiscuss.setComment(Integer.parseInt(cellStr));
        	break;
        case 7:
        	spocdiscuss.setNote(Integer.parseInt(cellStr));
        	break;
        case 8:
        	spocdiscuss.setAdmire(Integer.parseInt(cellStr));
        	break;
        }
		
        return spocdiscuss;  
	}
	//插入学生讨论区数据
	@Override
	public int insertSpocdiscuss(List<Spocdiscuss> spocdiscusses,Integer cid) {
		int result = -1;
		for(Spocdiscuss spocdiscuss:spocdiscusses){
			if (spocdiscuss != null) {
				Course course = courseDAOImpl.findById(cid);
				spocdiscuss.setCourse(course);
				result = spocdiscussDAOImpl.addSpocdiscuss(spocdiscuss);
				if (result == -1) {
					break;
				}
			}
		}
		return result;
	}
	
}
