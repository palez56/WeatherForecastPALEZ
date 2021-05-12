import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

public class weather{

	public static void main(String[] args)  {
		JFrame fr = new JFrame("Weather");
		
		//text field
		JTextField city = new JTextField();
		city.setBounds(50,50,275,25);
		
		//labels
		Label temp = new Label("Температура: ");
		temp.setBounds(50,130,150,20);
		Label max_temp = new Label("Максимальная температура: ");
		max_temp.setBounds(50,180,200,20);
		Label min_temp = new Label("Минимальная температура: ");
		min_temp.setBounds(50,230,200,20);
				
		
		//buttons
		JButton getData = new JButton("Search");
		getData.setBounds(50,350,100,30);
		JButton faq = new JButton("?");
		faq.setBounds(295,350,30,30);
		
		//dialog
		faq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(fr, "Для получения информации о погоде введите в текстовое поле название города и нажмите кнопку Search. Выполнено Антоном Клименко.");
			}
		});
		
		//searching weather
		
		
		getData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = null;
				String getUserCity = city.getText().trim();
				if(!getUserCity.equals("")) {
					output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=445ca64da7bb0077e1bfe71e50873a60&units=metric");
				};
				
				if(!output.isEmpty()){
					JSONObject obj = new JSONObject(output); 
					temp.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
					max_temp.setText("Максимальная температура: " + obj.getJSONObject("main").getDouble("temp_max"));
					min_temp.setText("Минимальная температура: " + obj.getJSONObject("main").getDouble("temp_min"));
				}
				
			}
		});
				
		//lines
		Canvas lines = new Canvas() {
			private static final long serialVersionUID = 1;

			@Override
			public void paint(Graphics g) {
				g.drawLine(50, 150, 325, 150);
				g.drawLine(50, 200, 325, 200);
				g.drawLine(50, 250, 325, 250);
			}
		};
		
		//frame settings
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Stepee\\eclipse-workspace\\Weather\\img\\icon.png");
		fr.setIconImage(icon);
		
		fr.setSize(400,500);
		fr.setVisible(true);
		fr.setResizable(false);
		fr.setLocationRelativeTo(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//adding components
		fr.add(getData); fr.add(faq); fr.add(city); fr.add(temp); fr.add(max_temp); fr.add(min_temp); fr.add(lines); 

	}
	
	private static String getUrlContent(String urlAdress) {
		StringBuffer content = new StringBuffer();
		
		try {
			URL url = new URL(urlAdress);
			URLConnection urlCon = url.openConnection();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			
			String line;
			while((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return content.toString();
	}


}