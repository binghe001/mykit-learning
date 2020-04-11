package io.mykit.concurrent.blockingqueue.array;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class MyArrayBlockingQueue {

 	BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(5);
	BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(5);

	public boolean addByOffer(String s) {
		return blockingQueue1.offer(s);
	}

	public boolean addByAdd(String s) {
		return blockingQueue2.add(s);
	}

	public String removeByPoll() {
		return blockingQueue1.poll();
	}

	public String removeByRemove() {
		return blockingQueue2.remove();
	}
}

public class TestArrayBlockingQueue {
	public static void main(String args[]) {
		MyArrayBlockingQueue myArrayBlockingQueue = new MyArrayBlockingQueue();
		System.out.println("Adding 6 elements by offer ()");
		for (int i = 0; i < 6; i++) {
			System.out.println(
					"Element no :" + (i + 1) + "adding by offer() : " + myArrayBlockingQueue.addByOffer("String" + i));
		}

		System.out.println("=============================");

		System.out.println("Adding 6 elements by add ()");
		for (int i = 0; i < 5; i++) {
			System.out.println(
					"Element no :" + (i + 1) + "adding by add() : " + myArrayBlockingQueue.addByAdd("String" + i));
		}
		
  
		System.out.println("=============================");
        System.out.println("Removing 6 elements by poll ()");
        for (int i = 0; i < 6; i++) {
            System.out.println("Element no :" + (i+1) + " removed by poll() : " + myArrayBlockingQueue.removeByPoll());
        }
        
 
		System.out.println("=============================");
        System.out.println("Removing 6 elements by remove ()");
        for (int i = 0; i < 6; i++) {
            System.out.println("Element no :" + (i+1) + " removed by remove() : " + myArrayBlockingQueue.removeByRemove());
        }

	}
}