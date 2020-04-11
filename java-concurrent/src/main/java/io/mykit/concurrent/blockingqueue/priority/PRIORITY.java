package io.mykit.concurrent.blockingqueue.priority;

public enum PRIORITY {

	NORMAL(1), EXPRESS(2), SAME_DAY_DELIVERY(3);

	private final Integer value;

	PRIORITY(int v) {
		value = v;
	}

	public Integer value() {
		return value;
	}

	public static PRIORITY fromValue(int v) {
		for (PRIORITY priority : PRIORITY.values()) {
			if (priority.value == v) {
				return priority;
			}
		}
		throw new IllegalArgumentException(String.valueOf(v));
	}
}