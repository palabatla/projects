package com.restaurant.model;

import java.util.Comparator;

/**
 * This class is used to sort the elements of Item
 * into descending order
 */

public class ItemComparator implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {

		if (o2.getAmtOfsatisfaction() > o1.getAmtOfsatisfaction())
			return 1;

		else if (o2.getAmtOfsatisfaction() < o1.getAmtOfsatisfaction())
			return -1;

		else
			return 0;
	}

}
