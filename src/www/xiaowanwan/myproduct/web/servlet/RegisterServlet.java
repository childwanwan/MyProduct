package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import www.xiaowanwan.myproduct.domain.User;
import www.xiaowanwan.myproduct.exception.UserException;
import www.xiaowanwan.myproduct.service.UserService;



public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������֤��
		String ckcode = request.getParameter("ckcode");
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		if(!checkcode_session.equals(ckcode)){//���������֤�벻һ�£�����ע����
			request.setAttribute("ckcode_msg", "��֤�����");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return ;
		}
		//��ȡ��������
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setActiveCode(UUID.randomUUID().toString());//�ֶ����ü�����
			//����ҵ���߼�
			UserService us = new UserService();
			us.regist(user);
			//�ַ�ת��
			//Ҫ���û��������ܵ�¼�����Բ��ܰ��û���Ϣ����session��
			//request.getSession().setAttribute("user", user);//���û���Ϣ��װ��session������
			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
		}catch(UserException e){
			request.setAttribute("user_msg", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}