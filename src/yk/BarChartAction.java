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
		
		data.setValue("ƻ��",100); 
		data.setValue("����",200); 
		data.setValue("����",300); 
		data.setValue("�㽶",400); 
		data.setValue("��֦",500); 
        
        chart = ChartFactory.createPieChart3D(
        		"ˮ������ͼ",  		// ͼ�����
		        data, 
		        true, 			// �Ƿ���ʾͼ��
		        false, 			// �Ƿ񴴽�������ʾ (tooltip) 
                false  			// �Ƿ����� URL ����
		        ); 
        
        // ������
		chart.addSubtitle(new TextTitle("2013���"));
		//��ʾ�ٷֱ�
		PiePlot pieplot = (PiePlot)chart.getPlot();
        pieplot.setLabelFont(new Font("����", 0, 12));
        pieplot.setNoDataMessage("������");
        pieplot.setCircular(true);
        pieplot.setLabelGap(0.02D);
        pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2}",NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));
        
        PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot(); 
		//���ÿ�ʼ�Ƕ�  
		pieplot3d.setStartAngle(120D);  
		//���÷���Ϊ��˳ʱ�뷽��  
		pieplot3d.setDirection(Rotation.CLOCKWISE);  
		//����͸���ȣ�0.5FΪ��͸����1Ϊ��͸����0Ϊȫ͸��  
		pieplot3d.setForegroundAlpha(0.7F); 
		
		return SUCCESS;
	}
	
}
