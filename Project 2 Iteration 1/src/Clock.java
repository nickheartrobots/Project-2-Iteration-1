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
 */
public class Clock implements Runnable {
	private static Refrigerator refrigerator;
	
	/**
	 * Creates the Refrigerator instance and starts the thread
	 */
	public Clock() {
		refrigerator = Refrigerator.instance();
		new Thread(this).start();
	}

	/**
	 * Keep ticking every second and call the Refrigerator system's clockTicked method
	 * 
	 */
	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
				refrigerator.clockTicked();
				
			}
		} catch (InterruptedException ie) {
		}
	}
}
