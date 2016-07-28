package com.s4you.flybeau.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//import bank.admin.utility.logtracking.LogUtil;

public class SystemProperties
    extends Properties {

  /**
	 * 
	 */
	private static final long serialVersionUID = -4124404154962127773L;
private String SYSTEM_PROPERTIES_FILE_NAME =
      "/bank/admin/utility/config/PhoneAdmin.properties";
  private static SystemProperties _instance = new SystemProperties();
  private static int _counter = 0;

  private SystemProperties() {
    reload();
  }

  protected void finalize() throws Throwable {
    super.finalize();
  }

  public static int getCounter() {
    return _counter;
  }

  public static SystemProperties getInstance() {
    _counter++;
    return _instance;
  }

  public void reload() {
		try {
			java.io.InputStream is = null;
			try {
				is = this.getClass().getResourceAsStream(
						SYSTEM_PROPERTIES_FILE_NAME);
				// is = new FileInputStream(SYSTEM_PROPERTIES_FILE_NAME);
				load(is);
			} catch (FileNotFoundException e) {
				/*
				 * LogUtil.info("@@@Catch : " +
				 * "SYSTEM PROPERTIES FILE NOT FOUND, file = " +
				 * SYSTEM_PROPERTIES_FILE_NAME, "ADMIN");
				 */
				e.printStackTrace();
			}
		} catch (IOException e) {
			/*
			 * LogUtil.info("@@@Catch : " + "File I/O Error, file = " +
			 * SYSTEM_PROPERTIES_FILE_NAME, "ADMIN");
			 */
			e.printStackTrace();
		}
  }

  public static void main(String[] args) {
    new SystemProperties().reload();
  }
}
