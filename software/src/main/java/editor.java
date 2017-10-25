import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
class editor{
	JPanel jpBody;
	JFrame frame;
	int subtcnt = 0;
	int substcnt = 0;
	int subpcnt = 0;
	int imgcnt = 0;
	int codecnt = 0;
	int precnt = 0;
	int ytbcnt = 0;
	ArrayList<JTextArea> txtTitle = new ArrayList<JTextArea>();
	ArrayList<JTextArea> txtSubTitle = new ArrayList<JTextArea>();
	ArrayList<JTextArea> txtArticle = new ArrayList<JTextArea>();
	ArrayList<JTextArea> txtImage = new ArrayList<JTextArea>();
	ArrayList<JTextArea> txtCode = new ArrayList<JTextArea>();
	ArrayList<JTextArea> txtPre = new ArrayList<JTextArea>();
	ArrayList<JTextArea> txtYT = new ArrayList<JTextArea>();
	String order = "-";
	JTextArea jtImage;
	JTextArea jtTitle;
	JTextArea jtIntro;
	JTextArea plantillaPost = mkTextArea(1,12);
	public editor(){
		mkGUI();
	}
	void mkGUI(){
		frame = new JFrame("Blogger GremioGeek");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(mkMenu());

		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());

		frame.add(new JScrollPane(jp));
		
		JPanel jpIntro = new JPanel();
		jpIntro.setLayout(new BoxLayout(jpIntro, BoxLayout.Y_AXIS));
		jtImage = mkTextArea(1,12);
		jtTitle = mkTextArea(1,12);
		jtIntro = mkTextArea(5, 10);
		jtImage.setBackground(new Color(255,255,138,100));
		jpIntro.add(jtImage);
		jpIntro.add(jtTitle);
		jpIntro.add(jtIntro);
		
		jpBody = new JPanel();
		jpBody.setLayout(new BoxLayout(jpBody, BoxLayout.Y_AXIS));

		JPanel jpFooter = new JPanel();
		JButton button = new JButton("Generar");
		button.addActionListener(new ActionListener() { 
   			public void actionPerformed(ActionEvent e) {
   				genBlog();
   			}
		});
		jpFooter.add(button);

		jp.add(jpIntro, BorderLayout.NORTH);
		jp.add(jpBody, BorderLayout.CENTER);
		jp.add(jpFooter, BorderLayout.SOUTH);

		frame.setVisible(true);
	}
	void genBlog(){
		int tcont = 0;
		int stcont = 0;
		int temp = 0;
		int cnt_st = 0;
		String[] subTitle = new String[txtTitle.size()];
		String[] subSubTitle = new String[txtSubTitle.size()];
		String plantilla =  "<picture class=\"p-img\">\n"+
							"	<img src=\""+jtImage.getText()+"\"alt=\""+jtTitle.getText()+"\"/>\n"+
						   	"</picture>\n"+
						   	"<h1>"+jtTitle.getText()+"</h1>\n"+
						   	"<p>"+jtIntro.getText()+"</p>\n"+
						   	"<!--more-->\n"+
						   	"<div id=\"indx\">\n"+
						   	"	<div>&Iacute;ndice<span>[<span id=\"toggleIndx\" onclick=\"toggleIndx()\">ocultar</span>]</span></div>\n"+
						   	"	<ul>\n";
		for (int a = 0; a < order.split("-").length; a++){
			if(order.split("-")[a].equals("t") || order.split("-")[a].equals("st")){
				if(order.split("-")[a].equals("t")){
					if(temp == 1)
						plantilla +="		</ul>\n";
					plantilla += "		<li><a href=\"#sub-"+(tcont+1)+"\">"+txtTitle.get(tcont).getText()+"</a></li>\n";
					subTitle[tcont] = "<h2 id=\"sub-"+(tcont+1)+"\">"+txtTitle.get(tcont).getText()+"</h2>";
					temp = 0;
					tcont++;
				}
				if(order.split("-")[a].equals("st")){
					if(temp == 0){
						plantilla +="		<ul>\n";
						stcont = 0;
						temp = 1;
					}
					if(temp == 1){
						plantilla += "			<li><a href=\"#sub-"+(stcont+1)+"-"+tcont+"\">"+txtSubTitle.get(cnt_st).getText()+"</a></li>\n";
						subSubTitle[cnt_st] = "<h3 id=\"sub-"+(stcont+1)+"-"+tcont+"\">"+txtSubTitle.get(cnt_st).getText()+"</h3>";
						cnt_st++;
						stcont++;
					}
				}
			}
		}
		if(temp == 1)
			plantilla +="		</ul>\n";
		plantilla +="	</ul>\n"+
					"</div>\n";
		int c_t = 0;
		int c_st = 0;
		int c_p = 0;
		int c_i = 0;
		int c_c = 0;
		int c_pr = 0;
		int c_y = 0;

		for (int a = 0; a < order.split("-").length; a++){
			if(order.split("-")[a].equals("t")){
				plantilla += subTitle[c_t]+"\n";
				c_t++;
			}
			if(order.split("-")[a].equals("st")){
				plantilla += subSubTitle[c_st]+"\n";
				c_st++;
			}
			if(order.split("-")[a].equals("p")){
				plantilla += "<p>"+txtArticle.get(c_p).getText()+"</p>\n";
				c_p++;
			}
			if(order.split("-")[a].equals("pr")){
				plantilla += "<pre>"+txtPre.get(c_pr).getText()+"</pre>\n";
				c_pr++;
			}
			if(order.split("-")[a].equals("i")){
				plantilla += "<picture class=\"s-img\">\n"
							+"	<img src=\""+txtImage.get(c_i).getText()+"\"/>\n"
							+"</picture>\n";
				c_i++;
			}
			if(order.split("-")[a].equals("c")){
				plantilla += "<script src=\"https://gist.github.com/scysco/"+txtCode.get(c_c).getText()+"\"></script>";
				c_c++;
			}
			if(order.split("-")[a].equals("y")){
				plantilla += "<iframe width=\"420\" height=\"370\" src=\"https://www.youtube.com/embed/"+txtYT.get(c_y).getText()+"\">\n"
							+"</iframe>\n";
				c_y++;
			}
		}
		plantillaPost.setText(plantilla);
		frameExt().setVisible(true);
		System.out.println(plantilla);

	}
	JFrame frameExt(){
		JFrame frameExt = new JFrame("To post");
		frameExt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameExt.add(new JScrollPane(plantillaPost));
		return frameExt;
	}
	void addSubP(){
		txtArticle.add(subpcnt, mkTextArea(1,10));
		txtArticle.get(subpcnt).setBackground(new Color(242,242,242,100));
		jpBody.add(txtArticle.get(subpcnt));
		subpcnt++;
		if(order.equals("-")){
			order = "p";
		}else{
			order += "-p";
		}
	}
	void addSubT(){
		txtTitle.add(subtcnt, mkTextArea(1,12));
		txtTitle.get(subtcnt).setBackground(new Color(230,230,230,100));
		jpBody.add(txtTitle.get(subtcnt));
		subtcnt++;
		if(order.equals("-")){
			order = "t";
		}else{
			order += "-t";
		}
	}
	void addSubST(){
		txtSubTitle.add(substcnt, mkTextArea(1,12));
		txtSubTitle.get(substcnt).setBackground(new Color(220,220,220,100));
		jpBody.add(txtSubTitle.get(substcnt));
		substcnt++;
		if(order.equals("-")){
			order = "st";
		}else{
			order += "-st";
		}
	}
	void addSubI(){
		txtImage.add(imgcnt, mkTextArea(1,12));
		txtImage.get(imgcnt).setBackground(new Color(255,255,138,100));
		jpBody.add(txtImage.get(imgcnt));
		imgcnt++;
		if(order.equals("-")){
			order = "i";
		}else{
			order += "-i";
		}
	}
	void addSubC(){
		txtCode.add(codecnt, mkTextArea(1,12));
		txtCode.get(codecnt).setBackground(new Color(26,26,26,100));
		jpBody.add(txtCode.get(codecnt));
		codecnt++;
		if(order.equals("-")){
			order = "c";
		}else{
			order += "-c";
		}
	}
	void addSubPr(){
		txtPre.add(precnt, mkTextArea(1,12));
		txtPre.get(precnt).setBackground(new Color(14,74,23,100));
		jpBody.add(txtPre.get(precnt));
		precnt++;
		if(order.equals("-")){
			order = "pr";
		}else{
			order += "-pr";
		}
	}
	void addSubY(){
		txtYT.add(ytbcnt, mkTextArea(1,12));
		txtYT.get(ytbcnt).setBackground(new Color(136,31,31,100));
		jpBody.add(txtYT.get(ytbcnt));
		ytbcnt++;
		if(order.equals("-")){
			order = "y";
		}else{
			order += "-y";
		}
	}
	JTextArea mkTextArea(int size, int font){
		JTextArea jt = new JTextArea(size, 0);
		jt.setLineWrap(true);
		jt.setWrapStyleWord(true);
		jt.setBorder(BorderFactory.createLineBorder(Color.black));
		jt.setFont(new Font("Arial",Font.PLAIN,font));
		return jt;
	}
	JMenuBar mkMenu(){
		JMenuBar menuBar;
		JMenu menu, submenu;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Insertar");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "add items");
		menuBar.add(menu);

		//a group of JMenuItems
		JMenuItem menuItem = new JMenuItem("SubTitulo",
		                         KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "nuevo subtitulo");
		menuItem.addActionListener(new ActionListener() { 
   			public void actionPerformed(ActionEvent e) {
   				addSubT();
   				SwingUtilities.updateComponentTreeUI(frame);
   			}
		});
		menu.add(menuItem);

		JMenuItem menuItem1 = new JMenuItem("Sub-SubTitulo",
		                         KeyEvent.VK_T);
		menuItem1.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem1.getAccessibleContext().setAccessibleDescription(
		        "nuevo subtitulo");
		menuItem1.addActionListener(new ActionListener() { 
   			public void actionPerformed(ActionEvent e) {
   				addSubST();
   				SwingUtilities.updateComponentTreeUI(frame);
   			}
		});
		menu.add(menuItem1);

		JMenuItem menuItem2 = new JMenuItem("Parrafo",
		                         KeyEvent.VK_T);
		menuItem2.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem2.getAccessibleContext().setAccessibleDescription(
		        "nuevo Parrafo");
		menuItem2.addActionListener(new ActionListener() { 
   			public void actionPerformed(ActionEvent e) {
   				addSubP();
   				SwingUtilities.updateComponentTreeUI(frame);
   			}
		});
		menu.add(menuItem2);

		JMenuItem menuItem3 = new JMenuItem("Imagen",
		                         KeyEvent.VK_T);
		menuItem3.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem3.getAccessibleContext().setAccessibleDescription(
		        "nueva imagen");
		menuItem3.addActionListener(new ActionListener() { 
   			public void actionPerformed(ActionEvent e) {
   				addSubI();
   				SwingUtilities.updateComponentTreeUI(frame);
   			}
		});
		menu.add(menuItem3);

		JMenuItem menuItem4 = new JMenuItem("Codigo",
		                         KeyEvent.VK_T);
		menuItem4.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem4.getAccessibleContext().setAccessibleDescription(
		        "nuevo codigo");
		menuItem4.addActionListener(new ActionListener() { 
   			public void actionPerformed(ActionEvent e) {
   				addSubC();
   				SwingUtilities.updateComponentTreeUI(frame);
   			}
		});
		menu.add(menuItem4);

		JMenuItem menuItem5 = new JMenuItem("Comando",
		                         KeyEvent.VK_T);
		menuItem5.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem5.getAccessibleContext().setAccessibleDescription(
		        "nuevo comando");
		menuItem5.addActionListener(new ActionListener() { 
   			public void actionPerformed(ActionEvent e) {
   				addSubPr();
   				SwingUtilities.updateComponentTreeUI(frame);
   			}
		});
		menu.add(menuItem5);

		JMenuItem menuItem6 = new JMenuItem("Video",
		                         KeyEvent.VK_T);
		menuItem6.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem6.getAccessibleContext().setAccessibleDescription(
		        "nuevo video");
		menuItem6.addActionListener(new ActionListener() { 
   			public void actionPerformed(ActionEvent e) {
   				addSubY();
   				SwingUtilities.updateComponentTreeUI(frame);
   			}
		});
		menu.add(menuItem6);
		
		//a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);

		//Build second menu in the menu bar.
		menu = new JMenu("Another Menu");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);
		return menuBar;
	}
}