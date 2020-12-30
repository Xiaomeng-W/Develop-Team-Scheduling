package wxm.service;
/**
 * 
 * @author xmwang 
 * @Description 自定义异常类
 * @date 2020年12月29日
 */
public class TeamException extends Exception{
	static final long serialVersionUID = -3387512345124222345L;
	
	public TeamException() {
		
	}
	
	public TeamException(String msg) {
		super(msg);
	}
}
