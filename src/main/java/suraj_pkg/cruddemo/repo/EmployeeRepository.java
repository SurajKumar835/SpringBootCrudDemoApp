package suraj_pkg.cruddemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import suraj_pkg.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
