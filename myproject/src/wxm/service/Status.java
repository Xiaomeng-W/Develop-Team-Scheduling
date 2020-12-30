package wxm.service;

/**
 * 
 * @author xmwang
 * @Description 表示员工的三种状态
 * @date 2020年12月29日
 */
public class Status {
	private final String NAME;

	private Status(String name) {
		this.NAME = name;
	}

	public static final Status FREE = new Status("FREE");
	public static final Status BUSY = new Status("BUSY");
	public static final Status VOCATION = new Status("VOCATION");

	public String getNAME() {
		return NAME;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return NAME;
	}
}
