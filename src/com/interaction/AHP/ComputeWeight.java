package com.interaction.AHP;

import java.math.BigDecimal;

public class ComputeWeight {
	
	//可用！！
	public static boolean calculWeight(Double[][] matrix, int N) {
		 for(int i=0;i<N;i++){
	            matrix[i][i]=1.0;
	        }
		 //根据输入值填写矩阵剩余项
        for(int i=N-1;i>=0;i--){
            for(int j=N-1;j>=0;j--){
                matrix[i][j]=1/matrix[j][i];
            }
        }
      
        //获得行数组
        Double[] line=new Double[N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
            	if(line[i]!=null){
                    line[i]=line[i]+matrix[i][j];
                }else{
                    line[i]=matrix[i][j];
                }
            }
            line[i] = line[i]/N;
        }
        //行归一化
        Double[] w=new Double[N];
        Double sum=0.0;
        for(int i=0;i<N;i++){
            sum=sum+line[i];
        }
        for(int i=0;i<N;i++){
            w[i]=line[i]/sum;                    //特征向量
        }
        
        
        Double[] bw=new Double[N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
            	if(bw[i]!=null){
                    bw[i]=bw[i]+matrix[i][j]*w[j];
                }else{
                    bw[i]=matrix[i][j]*w[j];
                } 
            }
        }
        Double sumR=0.0;                        //最大特征跟R
        for(int i=0;i<N;i++){
            sumR=sumR+bw[i]/(N*w[i]);
        }
        Double ci=(sumR-N)/(N-1);                //矩阵一致性指标
        System.out.println("计算出的矩阵一致性指标:CI="+ci);
        Double cr=ci/getRI(N);                      
        System.out.println("CR= " + cr+"\n");        
        if(cr>=0.1){
            System.out.println("权重设置不合理");
            return false;
        }else{
            //输出特征向量
            for(int i=0;i<N;i++){
            	w[i] = round(w[i],3);
                System.out.println("特征"+i+"的权重："+w[i]);
            }
            return true;
        }
		
	}

	 public static double round(double v, int scale) {  
	        if (scale < 0) {  
	            throw new IllegalArgumentException(  
	                    "The scale must be a positive integer or zero");  
	        }  
	        BigDecimal b = new BigDecimal(Double.toString(v));  
	        BigDecimal one = new BigDecimal("1");  
	        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    }  
	private static Double getRI(int n) {
		if (n == 3) {
			return 0.58;
		}else if (n == 4) {
			return 0.90;
		}else if (n == 5) {
			return 1.12;
		}else if (n == 6) {
			return 1.24;
		}else if (n == 7) {
			return 1.32;
		}else if (n == 8) {
			return 1.41;
		}else if (n == 9) {
			return 1.45;
		}
		return 1.0;
	}
	
	public static void main(String[] args) {
	      int N = 5;		 
		  Double[][] matrix=new Double[N][N];
//	        for(int i=0;i<N;i++){
//	            matrix[i][i]=1.0;
//	        }
	        //数组0,1 位置存放的是影响因素0相对于影响因素1的重要程度 。
	        //0.25代表 影响因素0的重要程度为影响因素1的重要程度的4分之1。
	        matrix[0][1]=3.0;            
	        matrix[0][2]=3.0;
	        matrix[0][3]=2.0;
	        matrix[0][4]=4.0;
	        matrix[1][2]=1.0;
	        matrix[1][3]=(double)1/3;
	        matrix[1][4]=3.0;
	        matrix[2][3]=(double)1/3;
	        matrix[2][4]=3.0;
	        matrix[3][4]=4.0;
	       calculWeight(matrix,N);
//		 double a = (double)1/3;
//		 System.out.println(a);
	    }
}
