package yk;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.opensymphony.xwork2.ActionSupport;

public class BarChartAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private JFreeChart chart;
	public JFreeChart getChart() {
		return chart;
	}

	@Override
	public String execute() throws Exception {
		
		DefaultPieDataset data = new DefaultPieDataset();
		
		data.setValue("苹果",100); 
		data.setValue("梨子",200); 
		data.setValue("葡萄",300); 
		data.setValue("香蕉",400); 
		data.setValue("荔枝",500); 
        
        chart = ChartFactory.createPieChart3D(
        		"水果产量图",  		// 图表标题
		        data, 
		        true, 			// 是否显示图例
		        false, 			// 是否创建工具提示 (tooltip) 
                false  			// 是否生成 URL 链接
		        ); 
        
        // 副标题
		chart.addSubtitle(new TextTitle("2013年度"));
		//显示百分比
		PiePlot pieplot = (PiePlot)chart.getPlot();
        pieplot.setLabelFont(new Font("宋体", 0, 12));
        pieplot.setNoDataMessage("无数据");
        pieplot.setCircular(true);
        pieplot.setLabelGap(0.02D);
        pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2}",NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));
        
        PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot(); 
		//设置开始角度  
		pieplot3d.setStartAngle(120D);  
		//设置方向为”顺时针方向“  
		pieplot3d.setDirection(Rotation.CLOCKWISE);  
		//设置透明度，0.5F为半透明，1为不透明，0为全透明  
		pieplot3d.setForegroundAlpha(0.7F); 
		
		return SUCCESS;
	}
	
}
