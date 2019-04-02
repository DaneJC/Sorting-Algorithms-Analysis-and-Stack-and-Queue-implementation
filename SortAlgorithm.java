/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Sort algorithm abstract parent class -> extends StopWatch for analysis purposes
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

/** Sort algorithm abstract parent class -> extends StopWatch for analysis purposes */
public abstract class SortAlgorithm extends StopWatch{

	// every sort algorithm implemented through out this program possesses the following properties:
	private String algoName, dataSet, dataSize;
	private int comparisons, swaps;

	// constructor
	public SortAlgorithm() {resetSwapsComps();}

	// restet swaps & comparisons to zero
	public void resetSwapsComps() {

		this.setComparisons(0);
		this.setSwaps(0);
	}

	// getters and setters
	public String getAlgoName() {
		return algoName;
	}

	public void setAlgoName(String algoName) {
		this.algoName = algoName;
	}

	public String getDataSet() {
		return dataSet;
	}

	public void setDataSet(String dataSet) {
		this.dataSet = dataSet;
	}

	public String getDataSize() {
		return dataSize;
	}

	public void setDataSize(String dataSize) {
		this.dataSize = dataSize;
	}

	public int getComparisons() {
		return comparisons;
	}

	public void setComparisons(int comparisons) {
		this.comparisons = comparisons;
	}

	public int getSwaps() {
		return swaps;
	}

	public void setSwaps(int swaps) {
		this.swaps = swaps;
	}

	// incrementer's
	public void incComparisons() {
		this.comparisons++;
	}

	public void incSwaps() {
		this.swaps++;
	}

	/* ===== Every algorithm possesses a sort method  ===== */
	public abstract void Sort(int[] array);

	/* ===== Some algorithms may be enhanced ===== */
	public abstract void SortENH(int[] array);
}
