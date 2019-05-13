package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import controller.Menucontroller;
import model.vo.Susi;

public class MenuView extends JFrame {
	Menucontroller menucontroller = new Menucontroller();
	Container con;
	JPanel panel1_1;	// 메뉴
	JPanel panel1_2;	// 메뉴
	JPanel panel2;	// 계산
	JPanel panel3;	// 주문
	JPanel panel4;	// 메뉴카테고리
	JPanel panel5;
	
	String[] payment = {"현금", "카드", "취소"};
	String[] header = {"품목", "수량", "가격", "변경"};
	String[][] contents = new String[15][4];
	
	Font f_contents = new Font("Verdana", Font.PLAIN, 24);
	Font f_header = new Font("맑은 고딕", Font.PLAIN, 24);
 
	JTable table = new JTable(contents, header);
	JScrollPane scrollPane = new JScrollPane(table);
//	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	public MenuView()	{
		super("GUI_TEST_miniProject");
		super.setSize(1295, 950);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrollPane.setSize(480, 600);
		scrollPane.setLocation(800, 100);
		
		JTableHeader jth = table.getTableHeader();
		jth.setFont(f_header);
		
		table.setFont(f_contents);
		table.setRowHeight(30);
		
		panel1_1 = new LeftPanel();
		panel1_2 = new LeftPanel();
		panel2 = new RightPanel();		
		panel3 = new BottomPanel();
		panel4 = new UpPanel();		
		panel5 = new JPanel();
		
//		tabbedPane.add("tab1", panel1_1);
//		tabbedPane.add("tab1", panel1_2);
		
		con = getContentPane();
		createMenu();
		insertTable();

//		con.add(tabbedPane);
		con.add(scrollPane);
		con.add(panel1_1);
		con.add(panel1_2);
		con.add(panel2);
		con.add(panel3);
		con.add(panel4);
		con.add(panel5);
		

//		ImageIcon imageIcon = new ImageIcon("Image/button.png");
//		for(int i=0; i<14; i++)
//			panel1_1.add(new JButton());
		
		for(Susi s : menucontroller.getSusiList()){
			ImageIcon imageIcon = new ImageIcon("D:\\JavaProject\\miniProject\\src\\susiImages\\"+s.getMmenuImage());
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance( 270, 240,  java.awt.Image.SCALE_SMOOTH ) ;  
			imageIcon = new ImageIcon( newimg );
			panel1_1.add(new JButton("", imageIcon));
		}

		
		for(String str:payment)
			panel3.add(new JButton(str));
		
		for(int i=0; i<5; i++)
			panel4.add(new JButton());
		
		super.setVisible(true);
	}

	class UpPanel extends JPanel	{
		private MenuView gt;
		public UpPanel()	{
			setSize(800, 100);
			setLocation(0, 0);

			//setBackground(Color.BLACK);
			setLayout(new GridLayout(1, 5));
		}
		
	}

	class LeftPanel extends JPanel	{
		public LeftPanel()	{
			setSize(800, 800);
			setLocation(0, 100);

			//setBackground(Color.LIGHT_GRAY);
			setLayout(new GridLayout(4, 4));		

		}

	}

	class RightPanel extends JPanel	{
		public RightPanel()	{
			setSize(480, 100);
			setLocation(800, 0);

			setBackground(Color.CYAN);
			setLayout(null);
		}

	}

	class BottomPanel extends JPanel	{
		public BottomPanel()	{
			setSize(480, 200);
			setLocation(800, 700);

			//setBackground(Color.YELLOW);
			setLayout(new GridLayout(1, 3));
		}
	}

	void createMenu()	{
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");

		fileMenu.add(new JMenuItem("New"));
		fileMenu.add(new JMenuItem("Open"));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem("Save"));
		fileMenu.add(new JMenuItem("Save as"));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem("Restart"));
		fileMenu.add(new JMenuItem("Exit"));

		editMenu.add(new JMenuItem("Undo"));
		editMenu.add(new JMenuItem("Rddo"));
		editMenu.addSeparator();
		editMenu.add(new JMenuItem("Cut"));
		editMenu.add(new JMenuItem("Copy"));
		editMenu.add(new JMenuItem("Copy Qualified Name"));
		editMenu.add(new JMenuItem("Paste"));
		editMenu.addSeparator();
		editMenu.add(new JMenuItem("Delete"));
		editMenu.add(new JMenuItem("Selcet All"));

		helpMenu.add(new JMenuItem("Welcome"));
		helpMenu.addSeparator();
		helpMenu.add(new JMenuItem("Help Contents"));

		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(helpMenu);

		setJMenuBar(mb);

	}
	
	void insertTable()	{
		for(int i=0; i<contents.length; i++)	{
			for(int j=0; j<contents[i].length; j++)	{
				contents[i][j] = "test";
			}
		}
	}


}
