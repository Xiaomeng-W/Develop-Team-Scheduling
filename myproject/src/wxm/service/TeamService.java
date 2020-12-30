package wxm.service;

/**
 * 
 * @author xmwang 
 * @Description 关于开发团队成员的管理：添加、删除等
 * @date 2020年12月30日
 */

import wxm.domain.Architect;
import wxm.domain.Designer;
import wxm.domain.Employee;
import wxm.domain.Programmer;

public class TeamService {
	private static int counter = 1; // 给memberId赋值
	private final int MAX_MEMBER = 5; // 开发团队最大成员数
	private Programmer[] team = new Programmer[MAX_MEMBER]; // 保存开发团队成员
	private int total; // 记录开发团队中实际人数

	/**
	 * 
	 * @author xmwang
	 * @Description 获取开发团队中的所有成员
	 * @date 2020年12月30日
	 * @return
	 */
	public Programmer[] getTeam() {
		Programmer[] team = new Programmer[total];
		for (int i = 0; i < team.length; i++) {
			team[i] = this.team[i];
		}
		return team;
	}

	/**
	 * 
	 * @author xmwang
	 * @Description 将指定的员工添加到开发团队中
	 * @date 2020年12月30日
	 * @param e
	 * @throws TeamException
	 */
	public void addMember(Employee e) throws TeamException {
		if (total >= MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加");
		}
		if (!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}
		if (isExist(e)) {
			throw new TeamException("该员工已在开发团队中");
		}
		Programmer p = (Programmer) e; // 一定不会出现类型转换异常
		if ("BUSY".equals(p.getStatus().getNAME())) {
			throw new TeamException("该员工已是某团队成员");
		} else if ("VOCATION".equals(p.getStatus().getNAME())) {
			throw new TeamException("该员工正在休假，无法添加");
		}

		// 获取team已有成员中架构师、设计师和程序员的人数
		int numOfArch = 0, numOfDes = 0, numOfPro = 0;
		for (int i = 0; i < total; i++) {
			if (team[i] instanceof Architect) {
				numOfArch++;
			} else if (team[i] instanceof Designer) {
				numOfDes++;
			} else if (team[i] instanceof Programmer) {
				numOfPro++;
			}
		}

		if (p instanceof Architect) {
			if (numOfArch >= 1) {
				throw new TeamException("团队中至多只能有一名架构师");

			}
		} else if (p instanceof Designer) {
			if (numOfDes >= 2) {
				throw new TeamException("团队中至多只能有两名设计师");
			}
		} else if (p instanceof Programmer) {
			if (numOfPro >= 3) {
				throw new TeamException("团队中至多只能有三名程序员");
			}
		}

		// 将p添加到现有的team中
		team[total++] = p;
		// p的属性赋值
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
	}

	/**
	 * 
	 * @author xmwang
	 * @Description 判断指定的员工是否已存在开发团队中
	 * @date 2020年12月30日
	 * @param e
	 * @return
	 */
	private boolean isExist(Employee e) {
		for (int i = 0; i < total; i++) {
			if (team[i].getId() == e.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @author xmwang
	 * @Description 从团队中删除成员
	 * @date 2020年12月30日
	 * @param memberID
	 */
	public void removeMember(int memberId) throws TeamException{
		int i = 0;
		for (; i<total; i++) {
			if (team[i].getMemberId()==memberId) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		
		if (i==total) {
			throw new TeamException("找不到指定memberId的员工，删除失败");
		}
		
		// 后边元素覆盖前边元素
		for (int j=i+1; j<total; j++) {
			team[j-1] = team[j];
		}
		team[--total] = null;
		
	}
}
