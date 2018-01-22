package com.likang.myutil.util.sort;

/*
排序法	   丨	最差时间分析	    丨	平均时间复杂度	丨	稳定度	丨  空间复杂度
冒泡排序	   丨		O(n2)	    丨		O(n2)		丨	稳定		丨	O(1)
选择排序	   丨		O(n2)	    丨		O(n2)		丨	稳定		丨	O(1)
插入排序	   丨		O(n2)	    丨		O(n2)		丨	稳定		丨	O(1)
快速排序	   丨		O(n2)	    丨		O(n*log2n)	丨	不稳定	丨	O(log2n)~O(n)
二叉树排序丨		O(n2)	    丨		O(n*log2n)	丨	不一定	丨	O(n)
堆排序	   丨		O(n*log2n)丨		O(n*log2n)	丨	不稳定	丨	O(1)
希尔排序	   丨		O		    丨		O			丨	不稳定	丨	O(1)
*/
public class Sort {
	
	/**
	 * 冒泡排序
	 * 比较两个相邻的元素，将值大的元素交换至右端
	 * @param arr
	 * @return
	 */
	static int [] bubbleSort(int arr[]){
		for(int i=0;i<arr.length-1;i++){//
			for(int j=0;j<arr.length-1-i;j++){//
				if(arr[j]>arr[j+1]){
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		} 
		return arr;
	}
	
	
	/**
	 * 选择排序
	 * 每一次从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕
	 * @param arr
	 * @return
	 */
	static int [] selectSort(int arr[]){
		
        for(int i = 0; i < arr.length - 1; i++) {// 做第i次排序
            int k = i;
            for(int j = k + 1; j < arr.length; j++){// 选最小的记录
                if(arr[j] < arr[k]){ 
                    k = j; //记下目前找到的最小值所在的位置
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if(i != k){  //交换a[i]和a[k]
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }    
        }
        return arr;
	}
	
	
	/**
	 * 插入排序
	 * 将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据
	 * @param arrs
	 * @return
	 */
	static int [] insertSort(int arrs[]){
		int len = arrs.length;
        int temp = 0;
        //进行len-1次循环，每次循环都将下标为i的元素插入到它前面已经排好序的队列中
        for(int i=1;i<len;i++){
            if(arrs[i]<arrs[i-1]){
            temp = arrs[i];
                while( i>0 && temp<arrs[i-1]){
                    arrs[i]=arrs[i-1];
                    i--;
                }
                 arrs[i] = temp;
            }
        }
        return arrs;
	}

	public static void main(String[] args) {
		int[] arr={6,3,8,2,9,1};
		insertSort(arr);
		for(int a : arr){
			System.err.print(a + " ");
		}
	}
}