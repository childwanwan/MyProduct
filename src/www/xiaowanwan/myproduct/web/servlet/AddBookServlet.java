package www.xiaowanwan.myproduct.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import www.xiaowanwan.myproduct.domain.Product;
import www.xiaowanwan.myproduct.service.ProductService;
import www.xiaowanwan.myproduct.util.UUIDUtil;



public class AddBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//����һ��DiskFileItemFactory����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//����һ��ServletFileUpload����
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8");//����ϴ��ļ�������
		//����request���󣬷������б���
		sfu.setFileSizeMax(1024*1024*20);//��ʾ3M��С
		List<FileItem> fileItems = new ArrayList<FileItem>(0);
		//���ڷ�װ��ͨ���������
		Map<String, String[]> map = new HashMap<String, String[]>();
		try {
			fileItems = sfu.parseRequest(request);
			
			//����fileItems����
			for (FileItem fileItem : fileItems) {
				if(fileItem.isFormField()){
					//��ͨ����
					String name = fileItem.getFieldName();//�õ��ֶε���
					String value = fileItem.getString("UTF-8");//�õ��ֶ�ֵ
					map.put(name, new String[]{value});//��map�и�ֵ
					
				}else{
					//�ļ�����
					InputStream inputStream = fileItem.getInputStream();
					String filename = fileItem.getName();//�õ��ϴ����ļ���
					String extension = FilenameUtils.getExtension(filename);
					if(!("jsp".equals(extension)||"exe".equals(extension))){//�ϴ����ļ�������jsp��exe
						//����Ŀ¼ 
						File storeDirectory = new File(this.getServletContext().getRealPath("/upload"));
						if(!storeDirectory.exists()){
							storeDirectory.mkdirs();//���Ŀ¼�����ڣ��ʹ���
						}
					//�����ļ���
					if(filename!=null){
						filename = FilenameUtils.getName(filename);
					}
					// Ŀ¼��ɢ
					String childDirectory = makeChildDirectory(storeDirectory, filename); // a/b
					
					filename = childDirectory+File.separator+filename;
					//�ļ��ϴ� 
					fileItem.write(new File(storeDirectory,filename));
					fileItem.delete();	//ɾ����ʱ�ļ�
					
					}
					map.put(fileItem.getFieldName(),new String[]{filename});//��ͼƬ�����name��value���浽map��
				}
			}
			
			Product product = new Product();
			BeanUtils.populate(product, map);
			product.setId(UUIDUtil.getUUID());//����ͼ����
			
			//����ҵ���߼�
			ProductService ps = new ProductService();
			//��ȡ����ID
			try {
				if(ps.selectBooksLastId()!=null){

				product.setId(String.valueOf(Integer.parseInt(ps.selectBooksLastId())+1));
				
				}else
					product.setId("1");
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				System.out.println(e1+"   "+"AddBookServlet��ȡ���Idʧ�ܣ�");
			}
			try {
				ps.addBook(product);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e+"  "+"AddBookServlet ���ͼ��ʧ�ܣ�");
			}
			
			//�ַ�ת��
									//��д/�������·��������ڱ����·��
			request.getRequestDispatcher("bookListServlet").forward(request, response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*request.setCharacterEncoding("UTF-8");
		//��ȡ������
		Product Product = new Product();
		try {
			BeanUtils.populate(Product, request.getParameterMap());
			Product.setId(UUIDUtil.getUUID());//�ֶ�����һ�����ID
		} catch (Exception e) {
			e.printStackTrace();
		}
		//����ҵ���߼�
		ProductServiceImpl bs = new ProductServiceImpl();
		bs.addProduct(Product);
		
		//�ַ�ת��
								//��д/�������·��������ڱ����·��
		request.getRequestDispatcher("ProductListServlet").forward(request, response);*/
	}

	// Ŀ¼��ɢ
		private String makeChildDirectory(File storeDirectory, String filename) {
			int hashcode = filename.hashCode();// �����ַ�ת����32λhashcode��
			//System.out.println(hashcode);
			String code = Integer.toHexString(hashcode); // ��hashcodeת��Ϊ16���Ƶ��ַ�
															// abdsaf2131safsd
			//System.out.println(code);
			String childDirectory = code.charAt(0) + File.separator
					+ code.charAt(1); // a/b
			// ����ָ��Ŀ¼
			File file = new File(storeDirectory, childDirectory);
			if (!file.exists()) {
				file.mkdirs();
			}
			return childDirectory;
		}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
