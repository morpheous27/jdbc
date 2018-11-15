package org.nitin.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.nitin.jdbc.dbutil.ConnectionPool;
import org.nitin.jdbc.dbutil.impl.ConnectionPoolSemaphore;
import org.nitin.jdbc.entity.Department;

public class DepartmentRepo {

	private ConnectionPool pool = new ConnectionPoolSemaphore();
	private static final String SELECT = "SELECT DEPARTMENT_ID, DEPT_NAME FROM DEPARTMENT WHERE DEPARTMENT_ID = ?";

	public void create(Department department) throws InterruptedException, SQLException {

		// connection = pool.getConnection();
		// Statement stmt = connection.createStatement();

	}

	public Department read() throws SQLException, InterruptedException {

		/*
		 * Acquiring the connection.
		 */
		Connection conn = pool.getConnection();
		PreparedStatement ps = conn.prepareStatement(SELECT);
		ps.setInt(1, 1);
		ResultSet rs = ps.executeQuery();

		Thread.sleep(10000);
		/*
		 * Releasing the connection.
		 */
		pool.releaseConnection(conn);

		Department department = new Department();
		while (rs.next()) {
			long dept_id = rs.getInt(1);
			String dept_name = rs.getString(2);
			System.out.println("Thread- "+Thread.currentThread().getName()+" Department id = " + dept_id + " Dept name = " + dept_name);
			department.setDepartmentId(dept_id);
			department.setDepartmentName(dept_name);
		}
		return department;
	}
}
