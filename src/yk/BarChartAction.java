package yk;

import java.awt.Color;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * ����ͼƬ����ĿĿ¼��
 *
 */
public class BarChartAction extends ActionSupport{

	private JFreeChart chart;
	public JFreeChart getChart() {
		return chart;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String execute() throws Exception {
		
		double [][]data = new double[][]{{1320,1000,800,700},{720,600,900,200},{830,1100,850,790},{1400,1050,200,450}};
		String []rowKeys = {"ƻ��","�㽶","����","����"}; 
		String []columnKeys = {"����","����","����","�Ϻ�"};
		
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		
        chart = ChartFactory.createBarChart3D(
        		"ˮ������ͼ", // ͼ�����
                "ˮ��", // Ŀ¼�����ʾ��ǩ
                "����", // ��ֵ�����ʾ��ǩ
                 dataset, // ���ݼ�
                 PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                 true,  // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������ false)
                 false, // �Ƿ񴴽�������ʾ (tooltip) 
                 false  // �Ƿ����� URL ����
                 ); 
        
        CategoryPlot plot = chart.getCategoryPlot();
        // �������񱳾���ɫ
 		plot.setBackgroundPaint(Color.white);
 		// ��������������ɫ
 		plot.setDomainGridlinePaint(Color.pink);
 		// �������������ɫ
 		plot.setRangeGridlinePaint(Color.pink);
 		
 		// ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
 		BarRenderer3D renderer=new BarRenderer3D();
 		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
 		renderer.setBaseItemLabelsVisible(true);
 		
 		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
 		renderer.setItemLabelAnchorOffset(10D);  
 		
 		// ����ƽ������֮�����
 		renderer.setItemMargin(0.4);
 		plot.setRenderer(renderer);
 		
 		
 		/**
 		 * ����ͼƬ��tomcat�������и���Ŀ��
 		 */
 		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
 		String realPath2 = request.getSession().getServletContext().getRealPath("/");
// 		C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\JFreeChart_Struts2\
 		
 		 FileOutputStream fos_jpg = null; 
         long currentTimeMillis = System.currentTimeMillis();
         try { 
         	//��ͼƬ������Tomcat������WebRoot�µ�imgĿ¼��
             fos_jpg = new FileOutputStream(realPath2+currentTimeMillis+".jpg");
             ChartUtilities.writeChartAsJPEG(fos_jpg,1,chart,500,400,null); 
         } catch (Exception e) {
         	System.out.println("error");
 		} finally { 
             try { 
                 fos_jpg.close(); 
             } catch (Exception e) {
             	System.out.println("error2");
             } 
         }
 		
		
		return SUCCESS;
	}
	
}
