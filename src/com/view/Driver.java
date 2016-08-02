package com.view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Driver {
	
	public static void main(String... args) {
		 try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	    	System.err.println("This look and feel is Unsupported. \n Details: ");
	    	e.printStackTrace();
	    }
	    catch (ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
	    catch (InstantiationException e) {
	    	e.printStackTrace();
	    }
	    catch (IllegalAccessException e) {
	    	e.printStackTrace();
	    }
		new MainFrame();
	}
}
