package truck;

import java.util.*;

public class Truck {
	
	private static Integer trucks = 0;
	
	public static void main(String[] args) {

	/*Integer[][] pattern = new Integer[][]{
	        { 1, 0, 0, 0, 0, 0, 0},
	        { 0, 1, 0, 0, 0, 0, 0},
	        { 0, 0, 1, 0, 0, 0, 0},
	        { 0, 0, 0, 1, 0, 0, 0},
	        { 0, 0, 0, 0, 1, 0, 0},
	        { 0, 0, 0, 0, 0, 1, 0},
	        { 0, 0, 0, 0, 0, 0, 1},
	};
	*/
	Integer[][] pattern = new Integer[][]{
        { 1, 0, 0, 0},
        { 0, 1, 0, 0},
        { 0, 0, 0, 0},
        { 0, 0, 1, 0},
        { 0, 0, 0, 1},
        { 0, 0, 0, 0},
	};
	
	int rows = pattern.length;
	int cols = pattern.length;
			
	
	List<List<Integer>> city = new ArrayList<>();
	for (Integer[] ints : pattern) {
		city.add(Arrays.asList(ints));
	}
		
	
	for (int i = 0; i < city.size(); i++) {
		
		for (int j = 0; j < city.get(i).size(); j++) {
			
			System.out.print(city.get(i).get(j));
			
		}
		
		System.out.println();
	}
	
	
	
	int size = breathFirstSearch(rows,cols,city);
	
	System.out.println();
	System.out.println("Result: " + size);
	

	}

	private static int breathFirstSearch(int rows, int cols, List<List<Integer>> city) {
		
		Integer[][] array = new Integer[rows][cols];
		
		//Creates an array than register which nodes we visited
		ArrayList<ArrayList<Integer>> visitedNodes = new ArrayList<>();
		
		for (Integer[] ints : array) {
			ArrayList<Integer> zerosList = new ArrayList<Integer>();
			for (int i = 0; i < ints.length; i++) {
				zerosList.add(0);
			}
			visitedNodes.add( zerosList );
		}
		
	
		//iterate over BiDimensional array
		for (int i = 0; i < city.size(); i++) {

			for (int j = 0; j < city.get(i).size(); j++) {
				
				if(visitedNodes.get(i).get(j) == 0) {
	
					moveThrough(city, i, j, city.size(), city.get(i).size() , visitedNodes);
					
					if(city.get(i).get(j) == 2) {
						trucks++;
					}
					
				}
				
				
			}
		
		}	
	
		return trucks;
	}



	private static ArrayList<ArrayList<Integer>> moveThrough(List<List<Integer>> city, int rows, int cols, int rows_max, int cols_max,  ArrayList<ArrayList<Integer>> visitedNodes) {
	

		if((cols <= -1 || cols >= cols_max)  || (rows <= -1 || rows >= rows_max)) {
			 return null;
		}
		
		
		if(visitedNodes.get(rows).get(cols) == 0 ) {
			visitedNodes.get(rows).set(cols, 3);
		}

		
		//if have "1" visit closers node
		if(city.get(rows).get(cols) == 1 && rows_max >= rows && cols_max >= cols && rows > -1 && cols > -1  ) {
			
			city.get(rows).set(cols, 2);
			
			//move to  right
			moveThrough(city, rows, cols + 1, rows_max,cols_max,visitedNodes);
			
			//move to down
			moveThrough(city, rows + 1, cols, rows_max, cols_max, visitedNodes);
				
			//move left
			moveThrough(city, rows, cols - 1, rows_max, cols_max, visitedNodes);
	
			
		}

		
		return visitedNodes;
	}

	

	

	

}