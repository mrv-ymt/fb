package com.s4you.flybeau.dbconnection;

import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;

import com.s4you.flybeau.utils.ConstantUtil;

/**
 * 
 * DatabaseConnection Date: 01/7/2016 ThienMV
 * 
 * */
public class DatabaseConnection {

	private static DatabaseConnection INSTANCE;
	private DataSource dataSource;

	/**
	 * Get Instance of DatabaseConnection
	 * 
	 * @return DatabaseConnection
	 */
	public static DatabaseConnection getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new DatabaseConnection();
		}

		try {

			ResourceBundle config = ResourceBundle
					.getBundle(ConstantUtil.SYSTEM_PROPERTIES_FILE_NAME);
	        PGPoolingDataSource dataSource = new PGPoolingDataSource();
	        dataSource.setUrl(config.getString("jdbc.url"));
	        dataSource.setUser(config.getString("jdbc.username"));
	        dataSource.setPassword(config.getString("jdbc.password"));
	        dataSource.setMaxConnections(Integer.parseInt(config.getString("jdbc.pool.max")));
	        dataSource.setInitialConnections(5);
	        dataSource.setConnectTimeout(60000);

	 
	        INSTANCE.dataSource = dataSource;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return INSTANCE;
	}

	/**
	 * Constructor DatabaseConnection
	 */
	private DatabaseConnection() {

	}
	
	public DataSource getDataSource(){
		return this.dataSource;
	}

}
