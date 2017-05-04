package com.interaction.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.interaction.dao.SeminarscoreDAO;
import com.interaction.dao.SpocdiscussDAO;
import com.interaction.dao.SpocscoreDAO;
import com.interaction.dao.StudentDAO;
import com.interaction.pojo.Seminarscore;
import com.interaction.pojo.Spocdiscuss;
import com.interaction.pojo.Spocscore;
import com.interaction.pojo.Student;
import com.interaction.service.SpocdataService;
import com.interaction.utils.ImportDataUtil;
import com.interaction.vo.SpocDataVo;

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
	@Resource
	private SeminarscoreDAO seminarscoreDAOImpl;
	
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
				Spocscore temp = spocscoreDAOImpl.findBySidAndSeid(spocscore.getStudent().getSid(),seId);
				if (temp == null) {
					result = spocscoreDAOImpl.addSpocscore(spocscore);
				}else{
					spocscore.setSsid(temp.getSsid());
					result = spocscoreDAOImpl.updateSpocscore(spocscore);
				}
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
				spocdiscuss.setCourse(courseDAOImpl.findById(cid));
					result = spocdiscussDAOImpl.addSpocdiscuss(spocdiscuss);
				if (result == -1) {
					break;
				}
			}
		}
		return result;
	}
	
	//获得图表所需数据
    @Override
    public SpocDataVo grenateGraph(Integer seId) {  
    	SpocDataVo bean = new SpocDataVo(); 
    	List<Spocscore> spocscores = spocscoreDAOImpl.listBySeid(seId);
    	int num0=0,num1=0,num2=0,num3=0,num4=0,num5=0;
    	for(Spocscore spocscore:spocscores){
    		if (spocscore.getScore1() == 5) {
				num5++;
			}else if (spocscore.getScore1() == 4) {
				num4++;
			}else if (spocscore.getScore1() == 3) {
				num3++;
			}else if (spocscore.getScore1() == 2) {
				num2++;
			}else if (spocscore.getScore1() == 1) {
				num1++;
			}else {
				num0++;
			}
    	}
    	
        List<String> categories = Arrays.asList("0","1","2","3","4","5");  
        List<Integer> data = Arrays.asList(num0, num1, num2, num3, num4, num5);  
        bean.setCategories(categories);  
        bean.setData(data); 
        return bean;
    	
    }  
    
    @Override
    public List<SpocDataVo> generateDiscussGraph(Integer cid,Integer sid) {
    	List<SpocDataVo> result = new ArrayList<SpocDataVo>();
    	
    	SpocDataVo bean = new SpocDataVo(); 
    	
    	List<Spocdiscuss> spocdiscusses = spocdiscussDAOImpl.findBySidAndCid(sid,cid);
    	String[] cate = new String[spocdiscusses.size()];
    	Integer[] admire = new Integer[spocdiscusses.size()];
    	Integer[] comment = new Integer[spocdiscusses.size()];
    	Integer[] subject = new Integer[spocdiscusses.size()];
    	Integer[] replay = new Integer[spocdiscusses.size()];
    	
    	for (int i = 0; i < spocdiscusses.size(); i++) {
    		cate[i] = "第"+(i+1)+"周";
			subject[i] = spocdiscusses.get(i).getSubject();
			comment[i] = spocdiscusses.get(i).getComment();
			replay[i] = spocdiscusses.get(i).getReplay();
			admire[i] = spocdiscusses.get(i).getAdmire();
		}
    	List<String> categories = Arrays.asList(cate);  
        List<Integer> data = Arrays.asList(subject);   
        bean.setCategories(categories);  
        bean.setData(data); 
        result.add(bean);
        
        data = Arrays.asList(comment); 
        bean = new SpocDataVo();
        bean.setData(data);
        result.add(bean);
        
        data = Arrays.asList(replay); 
        bean = new SpocDataVo();
        bean.setData(data);
        result.add(bean);
        
        data = Arrays.asList(admire); 
        bean = new SpocDataVo();
        bean.setData(data);
        result.add(bean);
        
        return result;
    }

    @Override
    public SpocDataVo generateStuGraph(Integer seId, Integer sid) {

    	SpocDataVo bean = new SpocDataVo(); 
    	
    	List<Seminarscore> seminarscores = seminarscoreDAOImpl.listBySeidAndSid(seId, sid);
    	if (seminarscores != null && seminarscores.size() != 0) {
        	Integer[] index = new Integer[seminarscores.size()];
        	
        	for (int i = 0; i < seminarscores.size(); i++) {
				index[i] = seminarscores.get(i).getSscore().intValue();
			}
        	
        	List<Integer> data = Arrays.asList(index);
            bean.setData(data); 
		}
    	
    	return bean;
    }
}
