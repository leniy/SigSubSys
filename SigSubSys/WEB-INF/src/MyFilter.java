//�Զ���Filter,�����û�����
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
public class MyFilter implements Filter
{
	private FilterConfig config;
	//��ʼ����
	public void init(FilterConfig config)
	{
		this.config = config;
	}
	//���ٷ���	
	public void destroy()
	{
		this.config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		//��ȡ��Filter�����ò���
		String encoding = config.getInitParameter("encoding");
		String loginPage = config.getInitParameter("loginPage");
		String proLogin = config.getInitParameter("proLogin");
		//����request�����õ��ַ���
		request.setCharacterEncoding(encoding);			//��
		HttpServletRequest requ = (HttpServletRequest)request;
		HttpSession session = requ.getSession(true);
		//��ȡ�ͻ������ҳ��
		String requestPath = requ.getServletPath();
		//���session��Χ��userΪnull��������û�е�¼
		//���û�����ļȲ��ǵ�¼ҳ�棬Ҳ���Ǵ����¼��ҳ��
		if( session.getAttribute("user") == null
			&& !requestPath.endsWith(loginPage)
			&& !requestPath.endsWith(proLogin))
		{
			//forward����¼ҳ��
			request.setAttribute("tip" , "����û�е�¼");
			request.getRequestDispatcher(loginPage)
				.forward(request, response);
		}
		//"����"����
		else
		{
			chain.doFilter(request, response);
		}
	}
}