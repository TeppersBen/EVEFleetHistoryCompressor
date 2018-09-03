package com.handlers;

import com.Settings;
import com.swing.nodes.DataSetNode;
import com.swing.nodes.LogNode;
import com.utils.Logger;

public class ThreadHandler {
	
	private static LogNode paid_payments;
	private static LogNode unpaid_payments;
	private static DataSetNode overall_dataset;
	
	public static synchronized void start_main_thread() {
		Thread thread;
		try {
			thread = create_main_thread();
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Thread create_main_thread() throws Exception {
		Logger.log(ThreadHandler.class.getSimpleName(), "Starting main thread");
		while (true) {
			if (!containsNull()) {
				if (paid_payments.isActive())
					paid_payments.updateAll();
				else if (unpaid_payments.isActive())
					unpaid_payments.updateAll();
				else if (overall_dataset.isActive())
					overall_dataset.updateView("Overall");
				else
					throw new Exception("Unknown object selected in Main-Thread");
			}
			
			try {
				long delay = Settings.MAIN_THREAD_SPEED * 1000;
				Thread.sleep(delay);
				Logger.log(ThreadHandler.class.getSimpleName(), "Delay next main_thread_refresh for " + delay + "ms | " + (delay / 1000) + "sec.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean containsNull() {
		return paid_payments == null || unpaid_payments == null || overall_dataset == null;
	}
	
	public static void assemble(DataSetNode node_overall, LogNode node_unfinishedPayments, LogNode node_finishedPayments) {
		paid_payments = node_finishedPayments;
		unpaid_payments = node_unfinishedPayments;
		overall_dataset = node_overall;
	}
	
}