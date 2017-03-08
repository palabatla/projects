package com.restaurant.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import com.restaurant.model.Item;
import com.restaurant.model.ItemComparator;

@Service
public class RestaurantServiceImpl {
	
    private boolean hasTimeExceeded = false;
    
    private int maxTime = 0;
    
    private int sumTimeForAllItem=0;
    
    /**
     * This method calculates the maximum satisfaction amount from the available items
     * that reads from file with in the specified time lift and returns the response.
     * 
     * @param customerName
     * @param timeLimit
     * @return
     */
	
	public String calculateMaxSatisfaction(String customerName,int timeLimit){
		
		maxTime = timeLimit * 60;
		
		LinkedList<Item> items = new LinkedList<Item>();
		
		Item result=null;
		
		String response="No maximum satisfaction amount available for "+customerName+" in the given time limit "+maxTime+"secs";
		
		try (BufferedReader br = new BufferedReader(new FileReader(new File("./data/data.txt")))) {

			String line;
			while ((line = br.readLine()) != null) {
				
				Item item = tokenizeLine(line);

				if (!hasTimeExceeded) {
					items.add(item);
				} else {
					break;
				}
			}
		//sort elements into descending order by using comparator	
		Collections.sort(items, new ItemComparator());
		
		if(items.size()>0){
		result = items.get(0);
		response = customerName+" has maximum satisfaction amount of "+result.getAmtOfsatisfaction()+" for eating in "+result.getTime()+"secs with time limit of "+maxTime+"secs";
		}
		
		System.out.println(response);

		} catch (Exception e) {
			System.out.println("Error while reading the file");
			e.printStackTrace();
		} finally {
			sumTimeForAllItem = 0;
			hasTimeExceeded = false;
		}
		
		return response;
	}
	
	
    /**
     * This method split the line using the delimiter specified. 
	 * Validates whether the sum of processed items should not exceed
	 * maximum time limit.
     * 
     * @param line
     * @return Item
     */
	
	private Item tokenizeLine(String line) {

		// itemCount = itemCount + 1;
		int timeTakenPerDish = 0;
		int amtOfSatisfaction=0;
		StringTokenizer tokens = new StringTokenizer(line, " ");
		
		Item item = new Item();
		if(tokens.hasMoreTokens()){
			amtOfSatisfaction = Integer.parseInt(tokens.nextToken());
			timeTakenPerDish = Integer.parseInt(tokens.nextToken());
		}
		
		sumTimeForAllItem = sumTimeForAllItem + timeTakenPerDish;

		if(sumTimeForAllItem > maxTime){
			hasTimeExceeded = true;
			return null;
		}

		item.setAmtOfsatisfaction(amtOfSatisfaction);
		item.setTime(timeTakenPerDish);
		
		return item;
	}
}
