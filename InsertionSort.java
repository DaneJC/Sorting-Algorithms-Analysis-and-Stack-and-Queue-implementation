/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Insertion sort algorithm class -> extends SortAlgorithm
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

/** Insertion sort algorithm class -> extends SortAlgorithm */
public class InsertionSort extends SortAlgorithm{

	/** SortAlgorithm inherited sort method implementation */
	@Override
	public void Sort(int[] array) {
		
		super.resetSwapsComps();
		for (int i = 1; i < array.length; i++) {

			int next = array[i];

			// find the insertion location while moving all larger element up
			int j = i;
			while (j > 0 && array[j - 1] > next) {
				super.incComparisons();
				super.incSwaps();
				array[j] = array[j - 1];
				j--;
			}

			// insert the element
			array[j] = next;
		}
	}
	/** SortAlgorithm inherited enhanced sort method implementation */
	@Override
	public void SortENH(int[] array){
		//Enhance if possible
	}
}

