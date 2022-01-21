package com.pattern;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Map<keyŸ��, valueŸ��>
	// Key Ÿ�� -> String ����
	// why? ������� ��û�� ������ "/insert.do"�� ���� �ĺ����� Ȱ���ؼ� ��û�� ���� ����
	// "/insert.do" ��û�� ������ InsertService ��ü�� ����
	// "/update.do" ��û�� ������ UpdateService ��ü�� ����
	private Map<String, Command> map;

	@Override
	public void init() throws ServletException {
		map = new HashMap<String, Command>();

		// map.put(��û �ĺ���, �ĺ����� ������ ��ü)
		map.put("/insert.do", new InsertService());
		map.put("/update.do", new UpdateService());
		map.put("/select.do", new SelectService());
		map.put("/delete.do", new DeleteService());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// FrontController ����
		// : ������� ��� ��û�� �� ������ ������ �� �ְ� �ϴ� ����
		// : URLMapping�� ��θ� "*.do" ����
		// : ��� ��û�� .do�� �ٿ��� �ϳ��� �������� ���� ��
		// ����
		// : �� ���� �߾�����ȭ �Ǹ鼭 ������ ������� �ϳ��� ����� ������ �����
		// ��� ����� ����� �� ���� �ȴٴ� �������� �߻� -> �Ϲ� Ŭ���� ���Ϸ� ��ɵ��� �и�

		// �Ϲ� Ŭ���� vs ����
		// : HttpServlet�� ��ӹ޾Ҵ����� ���� ����
		// : ���������� ������ �޸𸮸� ����ϴ� �Ϳ� ���� ����

		// �������̽��� Ȱ���ؼ� �Ϲ� Ŭ���� ����
		// : ���Ŀ� ������� ���񽺿� ���ؼ� ������ �޼ҵ�� ������ ����

		// Command ����
		// : ������� ��û�� ���� ó���� �� �ִ� �Ϲ� Ŭ�������� ����� �޼ҵ�� ���� �� �� �ֵ��� �ϴ� ����
		// : execute(HttpServletRequest, HttpServletResponse) �߻� �޼ҵ�� ����
		// : �Ϲ� Ŭ������ implements Ű���带 �̿��ؼ� �������̽��� ����

		// reqURI : /DesignPattern/insert.do
		// contextPath : /DesignPattern
		// command : /insert.do
//		String reqURI = request.getRequestURI();
//		String contextPath = request.getContextPath();
//		String command = reqURI.substring(contextPath.length());

		// ���ϰ�� (���� ���ε� ���) �� ��ȯ
		String command = request.getServletPath();

		System.out.println("��û�ĺ���>>" + command);

		Command com = map.get(command);
		com.execute(request, response);

//		if(command.equals("/insert.do")) {
//			// ������ �߰�
//			Command insert = new InsertService();
//			insert.execute(request,  response);
//		}else if(command.equals("/update.do")) {
//			// ������ ����
//			Command update = new UpdateService();
//			update.execute(request, response);
//		}else if(command.equals("/delete.do")) {
//			// ������ ����
//			Command delete = new DeleteService();
//			delete.execute(request, response);
//		}else if(command.equals("/select.do")) {
//			// ������ ��ȸ
//			Command select = new SelectService();
//			select.execute(request, response);
//		}

	}

}
