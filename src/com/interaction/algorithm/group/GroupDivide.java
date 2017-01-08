package com.interaction.algorithm.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.interaction.pojo.Seminarclass;

public class GroupDivide {
	
	public  static List<List<Seminarclass>> divideGroup(List<Seminarclass> seminarclasses, Integer grpNum){
		if (grpNum <= 0) {
			return null;
		}
		Integer eachGrpNum = seminarclasses.size()/grpNum;
		Integer extraPerson = seminarclasses.size()%grpNum;
		List<List<Seminarclass>> result = new ArrayList<List<Seminarclass>>();
		
		
		int[] groupIndex = getRandomIndex(seminarclasses.size());  //获得随机下标
		int  index = 0;
		
		for (int i = 0; i < grpNum; i++) {
			List<Seminarclass> oneGroup = new ArrayList<Seminarclass>();
			for (int j = 0; j < eachGrpNum; j++) {
				oneGroup.add(seminarclasses.get((--groupIndex[index++])));
			}
			if (extraPerson > 0) {
				oneGroup.add(seminarclasses.get((--groupIndex[index++])));
				extraPerson--;
			}
			result.add(oneGroup);
		}
		return result;
	}

	//将0到number-1个数随机排列
	public static int[] getRandomIndex(int number) {
		int[] result = new int[number];
		while ((number--) > 0) {
			int temp = getNum(result.length);
			while (true && number >= 0) {
				if (check(temp, result)) {
					temp = getNum(result.length);
				} else {
					result[number] = temp;
					break;
				}
			}
		}
		return result;
	}

	private static boolean check(int t, int[] result) {
		boolean flag = false;
		for (int i = 0; i < result.length; i++) {
			if (t == result[i]) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private static int getNum(int number) {
		Random random = new Random();
		return random.nextInt(number)+1;
	}
	public static void main(String[] args) {

		List<Seminarclass> seminarclasses = new ArrayList<>();
		
		Seminarclass sc1 = new Seminarclass();
		sc1.setScid(1);
		seminarclasses.add(sc1);
		
		Seminarclass sc2 = new Seminarclass();
		sc2.setScid(2);
		seminarclasses.add(sc2);
		
		Seminarclass sc3 = new Seminarclass();
		sc3.setScid(3);
		seminarclasses.add(sc3);
		
		Seminarclass sc4 = new Seminarclass();
		sc4.setScid(4);
		seminarclasses.add(sc4);
		
		Seminarclass sc5 = new Seminarclass();
		sc5.setScid(5);
		seminarclasses.add(sc5);
		
		Seminarclass sc6 = new Seminarclass();
		sc6.setScid(6);
		seminarclasses.add(sc6);
		
		Seminarclass sc7 = new Seminarclass();
		sc7.setScid(7);
		seminarclasses.add(sc7);
		
		Seminarclass sc8 = new Seminarclass();
		sc8.setScid(8);
		seminarclasses.add(sc8);
		
		Seminarclass sc9 = new Seminarclass();
		sc9.setScid(9);
		seminarclasses.add(sc9);
		
		Seminarclass sc10 = new Seminarclass();
		sc10.setScid(10);
		seminarclasses.add(sc10);
		
		Seminarclass sc11 = new Seminarclass();
		sc11.setScid(11);
		seminarclasses.add(sc11);
		
		Seminarclass sc12 = new Seminarclass();
		sc12.setScid(12);
		seminarclasses.add(sc12);
		
		Seminarclass sc13 = new Seminarclass();
		sc13.setScid(13);
		seminarclasses.add(sc13);
		
		Seminarclass sc14 = new Seminarclass();
		sc14.setScid(14);
		seminarclasses.add(sc14);
		
		Seminarclass sc15 = new Seminarclass();
		sc15.setScid(15);
		seminarclasses.add(sc15);
		
		
		
		List<List<Seminarclass>> result = divideGroup(seminarclasses, 16);
		int grp = 1;
		for(int i = 0 ;i < result.size();i++){
			System.out.print("第"+grp+"组：");
			for(int j =0;j<result.get(i).size();j++){
				System.out.print(result.get(i).get(j).getScid()+" ");
			}
			System.out.println();
			grp++;
		}
	}
}
