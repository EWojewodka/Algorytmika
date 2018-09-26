/**
 * 
 */
package com.wojewodka.algoritmics.timer;

/**
 * @author Emil Wojewódka
 *
 * @since 26 wrz 2018
 */
public abstract class AlgorithmTimer<T> {

	private long startTime;

	protected abstract T execute() throws Exception;

	public T runAlgorithm() throws Exception {
		this.startTime = System.nanoTime();
		T result = execute();
		long executeTime = System.nanoTime() - startTime;
		System.out.println("Czas wykonywania algorytmu w nanosekundach: " + executeTime + " ns");
		System.out.println("Czas wykonywania algorytmu w milisekundach: " + (executeTime / 1000000) + " ms");
		return result;
	}

}
