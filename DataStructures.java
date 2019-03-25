/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: DataStructure class -> used to guarantee consistent sort analysis by returning cloned instance arrays
 * 				for each and every sort execution, ensuring each algorithm processes the same data sets every sort.
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

package sample;

/** DataStructure class -> used to guarantee consistent sort analysis by ensuring each algorithm processes the same data set.
 *  Achieved by returning cloned instances of the identical data sets for every sort execution */
public class DataStructures {

	// sorting/reseting data sets
	private int[] sorted1000, sorted10000, sorted100000;
	private int[] inverse1000, inverse10000, inverse100000;
	private int[] random1000, random10000, random100000;

	// constructor
	public DataStructures(){
		
		propagateAll();
	}
	/* ===== data generation methods used to propagate instance arrays in propagateAll() ===== */
	// return sorted array of defined size
	private int[] propagateSorted(int arrayLength) {

		int[] sortedArray = new int[arrayLength];
		for(int i=0; i<arrayLength; i++)
			sortedArray[i] = i+1;
		return sortedArray;
	}

	// return inverse array of defined size
	private int[] propagateInverse(int arrayLength) {

		int[] inverseArray = new int[arrayLength];
		int values = arrayLength;
		for(int i=0; i<arrayLength; i++) {
			inverseArray[i] = values;
			values--;
		}
		return inverseArray;
	}

	// return random array of defined size
	private int[] propagateRandom(int arrayLength) {

		int[] randomArray = new int[arrayLength];
		for(int i=0; i<arrayLength; i++)
			randomArray[i] = (int)(Math.random() * arrayLength + 1);
		return randomArray;
	}

	// generate datasets data
	private void propagateAll()
	{
		sorted1000 = propagateSorted(1000);
		sorted10000 = propagateSorted(10000);
		sorted100000 = propagateSorted(100000);
		inverse1000 = propagateInverse(1000);
		inverse10000 = propagateInverse(10000);
		inverse100000 = propagateInverse(100000);
		random1000 = propagateRandom(1000);
		random10000 = propagateRandom(10000);
		random100000 = propagateRandom(100000);
	}

	// getters - return cloned instances of DataStructures object properties
	public int[] getSorted1000() {
		return sorted1000.clone();
	}

	public int[] getSorted10000() {
		return sorted10000.clone();
	}

	public int[] getSorted100000() {
		return sorted100000.clone();
	}

	public int[] getInverse1000() {
		return inverse1000.clone();
	}

	public int[] getInverse10000() {
		return inverse10000.clone();
	}

	public int[] getInverse100000() {
		return inverse100000.clone();
	}

	public int[] getRandom1000() {
		return random1000.clone();
	}

	public int[] getRandom10000() {
		return random10000.clone();
	}

	public int[] getRandom100000() {
		return random100000.clone();
	}
}
