package www.xiaowanwan.myproduct.service;

import java.sql.SQLException;

import www.xiaowanwan.myproduct.dao.UserDao;
import www.xiaowanwan.myproduct.domain.User;
import www.xiaowanwan.myproduct.exception.UserException;
import www.xiaowanwan.myproduct.util.SendJMail;



public class UserService {
UserDao ud = new UserDao();
	
	public void regist(User user) throws UserException {
		try {
			ud.addUser(user);//�û�ע��
			
			String emailMsg = "ע��ɹ�����<a href='http://www.baidu.com/activeServlet?activeCode="+user.getActiveCode()+"'>����</a>���¼";
			SendJMail.sendMail(user.getEmail(), emailMsg);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("ע��ʧ�ܣ�");
		}
	}

	public void activeUser(String activeCode) throws UserException {
		//���ݼ���������û�
		try {
			User user = ud.findUserByActiveCode(activeCode);
			if(user!=null){
				//�����û�
				
				ud.activeCode(activeCode);
				return;
			}
			throw new UserException("����ʧ��!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("����ʧ��!");
		}
	}

	public User login(String username, String password) throws UserException {
		User user=null;
		try {
			user = ud.findUserByUserNameAndPassword(username,password);
			if(user==null){
				throw new UserException("�û������������!");
			}
			if(user.getState()==0){
				throw new UserException("�û�δ����!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("�û������������!");
		}
		return user;
	}

	public User findUserById(String id) throws UserException {
		try {
			return ud.findUserById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("�û���ѯʧ�ܣ�");
		}
		
	}

	public void modifyUser(User user) throws UserException {
		try {
			ud.modifyUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("�޸�ʧ�ܣ�");
		}
		
	}

}
