/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Bubble sort algorithm class -> extends SortAlgorithm
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

package sample;

/** Bubble sort algorithm class -> extends SortAlgorithm */
public class BubbleSort extends SortAlgorithm{

	/** SortAlgorithm inherited sort method implementation */
	@Override
	public void Sort(int[] array)
	{
		super.resetSwapsComps();
		// temp variable for bubble sort
		int temp = 0;

		// bubble sort implementation
		for(long i = 0; i < array.length-1; i++)
		{
			for(long j = 0; j < array.length-1; j++)
			{
				super.setComparisons(super.getComparisons()+1);
				if(array[(int) j] > array[(int) (j+1)])
				{
					temp = array[(int) j];
					array[(int) j] = array[(int) (j+1)];
					array[(int) (j+1)] = temp;
					super.setSwaps(super.getSwaps()+1);
				}
			}
		}
	}

	/** SortAlgorithm inherited enhanced sort method implementation */
	@Override
	public void SortENH(int[] array)
	{
		super.resetSwapsComps();
		// temp variable for bubble sort and boolean sorted flag
		int temp = 0;
		boolean sorted;

		// bubble sort implementation
		for(int i = array.length-1; i > 0; i--)
		{
			sorted = true;

			for(int j = 0; j < i; j++)
			{
				super.setComparisons(super.getComparisons()+1);
				if(array[j] > array[j+1])
				{
					sorted = false;
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					super.setSwaps(super.getSwaps()+1);
				}
			}
			if(sorted)
				return;
		}
	}

	
}
