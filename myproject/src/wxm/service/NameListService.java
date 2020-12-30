package wxm.service;

import wxm.domain.Architect;
import wxm.domain.Designer;
import wxm.domain.Employee;
import wxm.domain.Equipment;
import wxm.domain.NoteBook;
import wxm.domain.PC;
import wxm.domain.Printer;
import wxm.domain.Programmer;

import static wxm.service.Data.*;

/**
 * 
 * @author xmwang 
 * @Description 负责将Data中的数据封装到Employee[][]数组中，同时提供相关操作Employee[]的方法。
 * @date 2020年12月29日
 */
public class NameListService {
	private Employee[]  employees;
	/**
	 * 给employees及数组元素进行初始化
	 */
	public NameListService() {
		super();
		employees = new Employee[EMPLOYEES.length];
		for (int i=0; i<employees.length; i++) {
			int type = Integer.parseInt(EMPLOYEES[i][0]); // 获取员工的类型
			// 获取员工的四个基本信息
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			
			Equipment equipment;
			double bonus;
			int stock;
			
			switch (type) {
			case EMPLOYEE:
				employees[i] = new Employee(id, name, age, salary);
				break;
			case PROGRAMMER:
				equipment = createEquipment(i);
				employees[i] = new Programmer(id, name, age, salary, equipment);
				break;
			case DESIGNER:
				equipment = createEquipment(i);
				bonus = Double.parseDouble(EMPLOYEES[i][5]);
				employees[i] = new Designer(id, name, age, salary, equipment, bonus);
				break;
			case ARCHITECT:
				equipment = createEquipment(i);
				bonus = Double.parseDouble(EMPLOYEES[i][5]);
				stock = Integer.parseInt(EMPLOYEES[i][6]);
				employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
				break;
			}
		}
	}
	
	/**
	 * 
	 * @author xmwang
	 * @Description 获取指定index位置上员工的设备
	 * @date 2020年12月29日
	 * @param i
	 * @return
	 */
	private Equipment createEquipment(int i) {
		int key = Integer.parseInt(EQUIPMENTS[i][0]);
		String model = EQUIPMENTS[i][1];
		switch (key) {
		case PC: // 21
			String display = EQUIPMENTS[i][2];
			return new PC(model, display);
		case NOTEBOOK: // 22
			double price = Double.parseDouble(EQUIPMENTS[i][2]);
			return new NoteBook(model, price);
		case PRINTER: // 23
			String type = EQUIPMENTS[i][2];
			return new Printer(model, type);
		}
		return null;
	}

	/**
	 * 
	 * @author xmwang
	 * @Description 获取当前所有的员工
	 * @date 2020年12月29日
	 * @return
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}
	
	/**
	 * 
	 * @author xmwang
	 * @Description 获取指定ID的员工
	 * @date 2020年12月29日
	 * @param id
	 * @return
	 * @throws TeamException 
	 */
	public Employee getEmployee(int id) throws TeamException {
		for (int i=0; i<employees.length; i++) {
			if (employees[i].getId()==id)
				return employees[i];
		}
		throw new TeamException("找不到指定的员工");
	}
}
