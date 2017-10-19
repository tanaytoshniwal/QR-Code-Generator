import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Generator extends Frame implements ActionListener{
	Label lbl;
	TextField txt;
	Button btn1,btn2;
	public Generator(String label){
		super(label);
		setSize(500,500);
		setLocationRelativeTo(this);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		setLayout(null);
		lbl=new Label("Input Text Here:");
		txt=new TextField();
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		btn1=new Button("Generate");
		btn2=new Button("Reset");
		btn1.setBackground(Color.DARK_GRAY);
		btn2.setBackground(Color.DARK_GRAY);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		lbl.setBounds(100,100,100,20);
		txt.setBounds(200,100,200,20);
		btn1.setBounds(150,150,200,20);
		btn2.setBounds(150,200,200,20);
		add(lbl);
		add(txt);
		add(btn1);
		add(btn2);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		String label=e.getActionCommand();
		//System.out.println(label);
		switch(label){
			case "Reset":{
				txt.setText("");
				break;
			}
			case "Generate":{
				String str=txt.getText();
				
				break;
			}
		}
	}
	public static void main(String[]  args){
		new Generator("QR Code Generator").setVisible(true);
	}
}