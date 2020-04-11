package io.mykit.concurrent.blockingqueue.priority;

import java.util.Random;

public class Inventory {

	private static final String[] items = { "shoes", "hanger", "blanket", "soap", "sticky note", "sketch pad",
			"television", "nail file", "clothes" };

	public static String getRandomItem() {
		int index = (new Random()).nextInt(items.length);
		if (index == items.length)
			index = (items.length - 1);
		return items[index];
	}
}