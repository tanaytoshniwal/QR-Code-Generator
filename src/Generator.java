import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Generator extends Frame implements ActionListener{
	Label lbl;
	JLabel jLabel;
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
        jLabel = new JLabel();
		txt=new TextField();
		txt.setBackground(Color.GRAY);
		txt.setForeground(Color.BLACK);
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
		jLabel.setBounds(200,250,200,200);
		add(lbl);
		add(txt);
		add(btn1);
		add(btn2);
		add(jLabel);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		String label=e.getActionCommand();
		//System.out.println(label);
		switch(label){
			case "Reset":{
				txt.setText("");
				jLabel.setIcon(null);
				break;
			}
			case "Generate":{
				String qrCodeText = txt.getText();
				int size = 125;
				String fileType = "png";
				try{
					String filePath = "T:\\WorkSpace\\eclipse1\\QR-code-Generator\\src\\Code.png";
					ClassLoader classLoader=getClass().getClassLoader();
					File qrFile = new File(filePath);
					//System.out.println(classLoader.getResource(filePath).getFile());
					createQRImage(qrFile, qrCodeText, size, fileType);
				}
				catch(IOException exp){
					exp.printStackTrace();
				}
				catch(WriterException exp){
					exp.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Your QR-Code is Generated with text \""+txt.getText()+"\"","Congratulations!",JOptionPane.INFORMATION_MESSAGE);
				BufferedImage image = null;
				try{
					image = ImageIO.read(new File("T:\\WorkSpace\\eclipse1\\QR-code-Generator\\src\\Code.png"));
				}
				catch(IOException exp){
					exp.printStackTrace();
				}
				ImageIcon imageIcon = new ImageIcon(image);
		        jLabel.setIcon(imageIcon);
				break;
			}
		}
	}
	public void createQRImage(File qrFile,String qrCodeText,int size,String fileType) throws IOException,WriterException{
		Hashtable hintMap=new Hashtable();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		ByteMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,BarcodeFormat.QR_CODE, size, size, hintMap);
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,BufferedImage.TYPE_INT_RGB);
		image.createGraphics();
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i,j)==0) {
					graphics.fillRect(i, j, 1, 1);
				}
				//System.out.println(byteMatrix.get(i,j));
			}
		}
		try{
			ImageIO.write(image, fileType, qrFile);
		}catch(Exception exp){
			exp.printStackTrace();
		}
	}
	public static void main(String[]  args){
		new Generator("QR Code Generator").setVisible(true);
	}
}