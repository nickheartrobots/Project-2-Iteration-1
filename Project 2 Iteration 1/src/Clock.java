/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 * 
 *            Redistribution and use with or without modification, are permitted
 *            provided that the following conditions are met:
 *
 *            - the use is for academic purpose only - Redistributions of source
 *            code must retain the above copyright notice, this list of
 *            conditions and the following disclaimer. - Neither the name of
 *            Brahma Dathan or Sarnath Ramnath may be used to endorse or promote
 *            products derived from this software without specific prior written
 *            permission.
 *
 *            The authors do not make any claims regarding the correctness of
 *            the code in this module and are not responsible for any loss or
 *            damage resulting from its use.
 *            
 *            
 * @author Nick Clarity Alicia Struble, Maya Gaforova
 * 		Modified for use in ICS372 Project 2 Iteration 1           
 */

import java.util.Observable;

/**
 * Implements a clock as a Runnable. Extends Observable to ease communication
 *
 */
public class Clock extends Observable implements Runnable {
	private Thread thread = new Thread(this);
	private static Clock instance;

	public enum Events {
		CLOCK_TICKED_EVENT
	};

	/**
	 * Start the thread
	 */
	private Clock() {
		thread.start();
	}

	/**
	 * To get the instance
	 * 
	 * @return returns the clock
	 */
	public static Clock instance() {
		if (instance == null) {
			instance = new Clock();
		}
		return instance;
	}

	/**
	 * Infinite loop to generate the clock ticks Notify all users when clock
	 * ticks
	 */
	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
				setChanged();
				notifyObservers(Events.CLOCK_TICKED_EVENT);
			}
		} catch (InterruptedException ie) {
		}
	}
}
