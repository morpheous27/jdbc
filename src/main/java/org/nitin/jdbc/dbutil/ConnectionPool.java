package org.nitin.jdbc.dbutil;

import java.sql.Connection;

/**
 * Contract to create a connection pool.
 * @author nitin
 *
 */
public interface ConnectionPool {

	public Connection getConnection() throws InterruptedException;
	public void releaseConnection(Connection connection);
}
