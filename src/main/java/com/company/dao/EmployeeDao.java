package com.company.dao;

import com.company.pojo.Department;
import com.company.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();//创建一个部门表
        employees.put(1001, new Employee(1001, "Aa", "123456@qq.com", 1, new Department(101, "市场部")));
        employees.put(1002, new Employee(1002, "Bb", "445566@qq.com", 1, new Department(102, "运营部")));
        employees.put(1003, new Employee(1003, "Cc", "667345@qq.com", 0, new Department(103, "后勤部")));
        employees.put(1004, new Employee(1004, "Dd", "998316@qq.com", 1, new Department(104, "教学部")));
        employees.put(1005, new Employee(1005, "Ee", "100009@qq.com", 0, new Department(105, "公关部")));

    }

    //主键自增
    public static Integer initId = 1006;

    //增加一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //查询全部员工信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    //删除
    public void delete(Integer id) {
        employees.remove(id);
    }

}
