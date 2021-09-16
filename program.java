/* 
 * License: read `license` at the root of the project.
 */

abstract class animal {
	abstract void say();
	abstract void feed();
}

final class cow extends animal {
	void say() {
		System.out.printf("Mooo!\n");
	}
	void feed() {
	}
}

public class program {
	public static void main(String[] args) {
		animal farm[] = new animal[2];
		farm[0] = new cow();
		farm[1] = new cow();

		int i;
		for (i = 0; i < farm.length; ++i) {
			farm[i].say();
		}
	}
}
