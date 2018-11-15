package org.nitin.jdbc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

	@Id
	@GeneratedValue
	@Column(name = "DEPARTMENT_ID")
	private Long departmentId;

	@Column(name = "DEPT_NAME")
	private String departmentName;

	@OneToOne(mappedBy = "departmentbo2o")
	private Employee employee;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Employee getEmployees() {
		return employee;
	}

	public void setEmployees(Employee employees) {
		this.employee = employees;
	}

}