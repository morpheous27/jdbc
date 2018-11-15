package org.nitin.jdbc.pooltest;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.nitin.jdbc.entity.Department;
import org.nitin.jdbc.repository.DepartmentRepo;

public class ConnectionPoolTest {

	public static void main(String ags[])
	{
		ExecutorService es = Executors.newFixedThreadPool(10);
		DepartmentRepo dr = new DepartmentRepo();
		
		for(int i =0 ; i< 10; i++)
		es.execute(() -> {
			try {
				Department dept = dr.read();
				System.out.println(dept.getDepartmentId() +" "+dept.getDepartmentName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
}
