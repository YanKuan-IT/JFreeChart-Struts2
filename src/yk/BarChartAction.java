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
 * 保存图片至项目目录下
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
		String []rowKeys = {"苹果","香蕉","橘子","梨子"}; 
		String []columnKeys = {"深圳","厦门","北京","上海"};
		
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		
        chart = ChartFactory.createBarChart3D(
        		"水果产量图", // 图表标题
                "水果", // 目录轴的显示标签
                "产量", // 数值轴的显示标签
                 dataset, // 数据集
                 PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                 true,  // 是否显示图例(对于简单的柱状图必须是 false)
                 false, // 是否创建工具提示 (tooltip) 
                 false  // 是否生成 URL 链接
                 ); 
        
        CategoryPlot plot = chart.getCategoryPlot();
        // 设置网格背景颜色
 		plot.setBackgroundPaint(Color.white);
 		// 设置网格竖线颜色
 		plot.setDomainGridlinePaint(Color.pink);
 		// 设置网格横线颜色
 		plot.setRangeGridlinePaint(Color.pink);
 		
 		// 显示每个柱的数值，并修改该数值的字体属性
 		BarRenderer3D renderer=new BarRenderer3D();
 		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
 		renderer.setBaseItemLabelsVisible(true);
 		
 		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
 		renderer.setItemLabelAnchorOffset(10D);  
 		
 		// 设置平行柱的之间距离
 		renderer.setItemMargin(0.4);
 		plot.setRenderer(renderer);
 		
 		
 		/**
 		 * 保存图片至tomcat服务器中该项目下
 		 */
 		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
 		String realPath2 = request.getSession().getServletContext().getRealPath("/");
// 		C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\JFreeChart_Struts2\
 		
 		 FileOutputStream fos_jpg = null; 
         long currentTimeMillis = System.currentTimeMillis();
         try { 
         	//将图片保存至Tomcat服务器WebRoot下的img目录中
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
