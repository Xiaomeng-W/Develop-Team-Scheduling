package wxm.junit;

import org.junit.Test;

import wxm.domain.Employee;
import wxm.service.NameListService;
import wxm.service.TeamException;

public class NameListServiceTest {
	@Test
	public void testGetAllEmployees() {
		NameListService service = new NameListService();
		Employee[] employees = service.getAllEmployees();
		for (int i=0; i<employees.length; i++) {
			System.out.println(employees[i]);
		}
	}
	
	@Test
	public void testGetEmployee() {
		NameListService service = new NameListService();
		int id = 10;
		try {
			Employee employee = service.getEmployee(id);
			System.out.println(employee);
		}catch (TeamException e) {
			System.out.println(e.getMessage());
		}
	}
}
