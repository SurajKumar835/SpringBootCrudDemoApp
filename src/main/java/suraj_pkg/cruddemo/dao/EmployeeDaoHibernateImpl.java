package suraj_pkg.cruddemo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import suraj_pkg.cruddemo.entity.Employee;
@Repository
public class EmployeeDaoHibernateImpl implements EmployeeDao {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDaoHibernateImpl (EntityManager theManager)
	{
		entityManager=theManager;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		Session current=entityManager.unwrap(Session.class);
		Query<Employee> q=current.createQuery("from Employee",Employee.class);
		List<Employee> emp=q.getResultList(); 
		return emp;
	}
	@Override
	public Employee findById(int theId) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Employee theEmployee =
				currentSession.get(Employee.class, theId);
		
		// return the employee
		return theEmployee;
	}


	@Override
	public void save(Employee theEmployee) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee
		currentSession.saveOrUpdate(theEmployee);
	}


	@Override
	public void deleteById(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery(
						"delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	}

}

