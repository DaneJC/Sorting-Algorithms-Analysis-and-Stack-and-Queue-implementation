/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Selection sort algorithm class -> extends SortAlgorithm
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

/** Selection sort algorithm class -> extends SortAlgorithm */
public class SelectionSort extends SortAlgorithm{

	/** SortAlgorithm inherited sort method implementation */
	@Override
	public void Sort(int[] array) {

		super.resetSwapsComps();
		// start at the beginning of the whole array
		for (int i = 0; i < array.length - 1; i++) {

			// (1) default value of the 1st element index 
			// use this to test against every other element.
			int minimum = i;

			// (2) loop from the beginning of unsorted zone to the end of the array.
			for (int j = i + 1; j < array.length; j++) {
				super.incComparisons();
				if (array[j] < array[minimum]) {    // compare current element to minimum

					minimum = j;            // if it's lower, it becomes the new minimum
				}
			}

			// swap the two values
			int temp = array[i];
			array[i] = array[minimum];
			array[minimum] = temp;
			super.incSwaps();


		}
	}

	/** SortAlgorithm inherited enhanced sort method implementation */
	@Override
	public void SortENH(int[] array){
		//Enhance if possible
	}
}
