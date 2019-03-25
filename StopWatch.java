/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Stopwatch/Timer class
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

/** Stopwatch/Timer class -> used to time sort run time */
public class StopWatch {

	private long elapsedTime = 0;
	private long startTime;
	private boolean isRunning;

	// constructor
	public StopWatch(){
		resetStopWatch();
	}

	// start stop watch
	public void start() {

		if (isRunning)
			return;
		isRunning = true;
		startTime = System.currentTimeMillis();
	}

	// stop stop watch
	public void stop(){
		if (!isRunning)
			return;
		isRunning = false;
		long endTime = System.currentTimeMillis();
		elapsedTime = 0;
		elapsedTime = endTime - startTime;
	}

	// get run time
	public long getElapsedTime(){
		if (isRunning){
			long endTime = System.currentTimeMillis();
			elapsedTime = (endTime - startTime);
			return elapsedTime;
		}
		else return elapsedTime;
	}

	// reset timer to zero
	public void resetStopWatch(){
		elapsedTime = 0;
		isRunning = false;
	}
}
