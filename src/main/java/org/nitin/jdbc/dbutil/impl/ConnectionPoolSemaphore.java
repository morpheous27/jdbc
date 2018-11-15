package org.nitin.jdbc.dbutil.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.nitin.jdbc.dbutil.ConnectionPool;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class ConnectionPoolSemaphore implements ConnectionPool {

	private Semaphore semaphore = new Semaphore(3);

	private static final int DEFAULT_POOL_SIZE = 3;
	private static int POOL_SIZE;
	private static final String DB_URL = "jdbc:mysql://localhost/springuser";
	private static final String USERNAME = "springuser";
	private static final String PASSWORD = "springuser";
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private List<Connection> connectionList;

	public ConnectionPoolSemaphore() {
		this(DEFAULT_POOL_SIZE);

	}

	public ConnectionPoolSemaphore(int poolSize) {
		
		POOL_SIZE = poolSize;
		connectionList = new ArrayList<>(POOL_SIZE);
		registerDriver();
		initializeConnectionPool();
		
	}


	public  Connection getConnection() throws InterruptedException {

		//System.out.println("acquiring lock..by "+Thread.currentThread().getName());
		semaphore.acquire();
		//System.out.println("lock acquired..by "+Thread.currentThread().getName());
		return connectionList.get(0);
		
	}

	public void releaseConnection(Connection connection) {

		connectionList.add(connection);
		semaphore.release();

	}

	private void registerDriver() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to register the drive for the db.");
			e.printStackTrace();
		}
	}

	private void initializeConnectionPool() {
		System.out.println("initializing connection pool...");
		try {
			for (int i = 0; i < POOL_SIZE; i++)
				connectionList.add(DriverManager.getConnection(DB_URL, USERNAME, PASSWORD));

		} catch (SQLException e) {
			System.err.println("Unable to create a connection.");
			e.printStackTrace();
		}
	}

}
