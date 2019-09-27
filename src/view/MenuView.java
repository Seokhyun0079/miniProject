package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controller.MemberController;
import controller.MenuController;
import model.vo.Food;

public class MenuView extends JFrame {
	static MenuController menucontroller = MenuController.getInstance();
	static MemberController membercontroller = MemberController.getInstance();
	//GachaDialog gachadialog = new GachaDialog(new JFrame(), "가챠가챠!!");

	static private Container con;
	static private JPanel[] panel1 = new JPanel[4];		// 메뉴
	static private JPanel panel2;		// 계산
	static private JPanel panel3;		// 주문
	static private JPanel panel4;		// 메뉴카테고리
	static private JPanel panel5;		// 전체 패널

	static final private String[] category =	{"초밥", "사이드", "주류", "음료"};
	static final private String[] payment = {"현금", "카드", "취소"};
	static final private String[] header1 = {"품목", "수량", "가격"};
	static final private String[] header2 = {"총 금액", "수량"};
	static final private String[][] contents1 = {};	// 테이블 초기화
	static final private String[][] contents2 = {{"0", "0"}};
	static final private String[] str = new String[4];

	static final private Font f_contents = new Font("맑은 고딕", Font.PLAIN, 24);
	static final private Font f_header = new Font("맑은 고딕", Font.PLAIN, 24);

	@SuppressWarnings("serial")
	static DefaultTableModel model1 = new DefaultTableModel(contents1, header1) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};
	@SuppressWarnings("serial")
	static DefaultTableModel model2 = new DefaultTableModel(contents2, header2) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};

	static JTable table = new JTable(model1);
	static private JScrollPane scrollPane = new JScrollPane(table);

	static JTable resultTable = new JTable(model2);
	static private JScrollPane resultScrollPane = new JScrollPane(resultTable);

	static final private JButton[] susiImg = new JButton[menucontroller.getSusiList().size()];
	static final private JButton[] sideImg = new JButton[menucontroller.getSideList().size()];
	static final private JButton[] drinkImg = new JButton[menucontroller.getDrinkList().size()];
	static final private JButton[] sakeImg = new JButton[menucontroller.getSakeList().size()];
	static final private ImageIcon basicImg = new ImageIcon("./src/menuImages/basicButton.png");

	public MenuView()	{
		super("GUI_TEST_miniProject");
		super.setSize(1295, 950);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		con = getContentPane();

		JTableHeader jth1 = table.getTableHeader();
		JTableHeader jth2 = resultTable.getTableHeader();

		jth1.setFont(f_header);		
		jth2.setFont(f_header);

		table.setFillsViewportHeight(true);
		table.setFont(f_contents);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);

		table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가

		resultTable.setFillsViewportHeight(true);
		resultTable.setFont(f_contents);		
		resultTable.setRowHeight(30);

		resultTable.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		resultTable.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가

		scrollPane.setSize(480, 530);
		scrollPane.setLocation(800, 100);

		resultScrollPane.setSize(480, 70);
		resultScrollPane.setLocation(800, 630);

		for(int i=0; i<panel1.length; i++)
			panel1[i] = new LeftPanel();

		int idx0=0, idx1=0, idx2=0, idx3=0;
		for(Food s : menucontroller.getSusiList()){
			ImageIcon imageIcon = new ImageIcon("./src/menuImages/susi/"+s.getMmenuImage()+".png");
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH );
			imageIcon = new ImageIcon( newimg );

			susiImg[idx0] = new JButton(s.getMenuTitle(), imageIcon);
			susiImg[idx0].setBackground(Color.WHITE);
			susiImg[idx0].addActionListener(new MenuEvent());

			panel1[0].add(susiImg[idx0]);
			idx0++;
		}
		JButton jb_1 = new JButton(basicImg);
		panel1[0].add(jb_1);

		for(Food s : menucontroller.getSideList()){
			ImageIcon imageIcon = new ImageIcon("./src/menuImages/side/"+s.getMmenuImage()+".jpg");
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance( 260, 260,  java.awt.Image.SCALE_SMOOTH );
			imageIcon = new ImageIcon( newimg );

			sideImg[idx1] = new JButton(s.getMenuTitle(), imageIcon);
			sideImg[idx1].addActionListener(new MenuEvent());

			idx1++;
		}

		for(Food s : menucontroller.getSakeList()){
			ImageIcon imageIcon = new ImageIcon("./src/menuImages/sake/"+s.getMmenuImage()+".jpg");
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance( 260, 260,  java.awt.Image.SCALE_SMOOTH ) ;  
			imageIcon = new ImageIcon( newimg );

			sakeImg[idx2] = new JButton(s.getMenuTitle(), imageIcon);
			sakeImg[idx2].addActionListener(new MenuEvent());

			idx2++;
		}

		for(Food s : menucontroller.getDrinkList()){
			ImageIcon imageIcon = new ImageIcon("./src/menuImages/drink/"+s.getMmenuImage()+".png");
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance( 260, 260,  java.awt.Image.SCALE_SMOOTH ) ;  
			imageIcon = new ImageIcon( newimg );

			drinkImg[idx3] = new JButton(s.getMenuTitle(), imageIcon);
			drinkImg[idx3].addActionListener(new MenuEvent());

			idx3++;
		}
		con.add(panel1[0]);	//메뉴판

		panel2 = new RightPanel();	// 팀로고 이미지
		panel3 = new BottomPanel(payment);	// 결제창 패널
		panel4 = new UpPanel();		// 메뉴 카테고리
		panel5 = new JPanel();	// 전체 패널

		con.add(scrollPane);	// 주문서
		con.add(resultScrollPane);	// 총 금액 
		con.add(panel2);
		con.add(panel3);
		con.add(panel4);
		con.add(panel5);

		con.revalidate();
		con.repaint();

		super.setVisible(true);
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {}			
			@Override
			public void windowIconified(WindowEvent e) {}			
			@Override
			public void windowDeiconified(WindowEvent e) {}			
			@Override
			public void windowDeactivated(WindowEvent e) {}			
			@Override
			public void windowClosing(WindowEvent e) {
				membercontroller.fileSaveMemberList();
			}

			@Override
			public void windowClosed(WindowEvent e) {}			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}

	public class MenuEvent implements ActionListener{
		private JButton button;

		@Override
		public void actionPerformed(ActionEvent e) {
			button = (JButton)e.getSource();

			for(Food s: menucontroller.getSusiList())	{
				if(button.getText().equals(s.getMenuTitle()))	{
					menucontroller.orderFood(s);
					model1.addRow(orderMenu(s));
					table.updateUI();
				}
			}

			for(Food s: menucontroller.getSideList())	{
				if(button.getText().equals(s.getMenuTitle()))	{
					menucontroller.orderFood(s);
					model1.addRow(orderMenu(s));
					table.updateUI();
				}
			}

			for(Food s: menucontroller.getSakeList())	{
				if(button.getText().equals(s.getMenuTitle()))	{
					menucontroller.orderFood(s);
					model1.addRow(orderMenu(s));
					table.updateUI();
				}
			}

			for(Food s: menucontroller.getDrinkList())	{
				if(button.getText().equals(s.getMenuTitle()))	{
					menucontroller.orderFood(s);
					model1.addRow(orderMenu(s));
					table.updateUI();
				}
			}

			for(int i=0; i<table.getRowCount(); i++)	{
				for(int j=0; j<i; j++)	{
					if(button.getText().equals(table.getValueAt(j, 0)))
						model1.removeRow(j);
				}
			}

			resultTable.setValueAt(menucontroller.calcOrder(), 0, 0);
			resultTable.setValueAt(menucontroller.sizeOrder(), 0, 1);
			resultTable.updateUI();

		}
	}


	class UpPanel extends JPanel 	{
		public UpPanel()	{
			setSize(800, 100);
			setLocation(0, 0);
			setLayout(new GridLayout(1, 5));

			JButton[] jb= new JButton[4];

			ImageIcon susiButton = new ImageIcon("./src/menuImages/susiButton.png");
			ImageIcon sideButton = new ImageIcon("./src/menuImages/sideButton.png");
			ImageIcon sakeButton = new ImageIcon("./src/menuImages/sakeButton.png");
			ImageIcon drinkButton = new ImageIcon("./src/menuImages/drinkButton.png");

			Image image1 = susiButton.getImage();
			Image image2 = sideButton.getImage();
			Image image3 = sakeButton.getImage();
			Image image4 = drinkButton.getImage();

			Image newimg1 = image1.getScaledInstance( 210, 100,  java.awt.Image.SCALE_SMOOTH ) ;
			Image newimg2 = image2.getScaledInstance( 210, 100,  java.awt.Image.SCALE_SMOOTH ) ;
			Image newimg3 = image3.getScaledInstance( 210, 100,  java.awt.Image.SCALE_SMOOTH ) ;
			Image newimg4 = image4.getScaledInstance( 210, 100,  java.awt.Image.SCALE_SMOOTH ) ;

			susiButton = new ImageIcon( newimg1 );
			sideButton = new ImageIcon( newimg2 );
			sakeButton = new ImageIcon( newimg3 );
			drinkButton = new ImageIcon( newimg4 );

			ImageIcon[] UpImg = {susiButton, sideButton, sakeButton, drinkButton};

			for(int i=0; i<jb.length; i++)	{
				jb[i] = new JButton(category[i], UpImg[i]);
				add(jb[i]);
				jb[i].addActionListener(new UpPanelActionListener());
			}
		}

		class UpPanelActionListener implements ActionListener	{
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton)e.getSource();

				switch(e.getActionCommand())	{
				case "초밥":
					for(JPanel jp:panel1)
						con.remove(jp);
					con.remove(panel5);

					panel1[0] = new LeftPanel();
					for(JButton jb:susiImg)	{
						panel1[0].add(jb);
					}

					JButton jb = new JButton(basicImg);
					panel1[0].add(jb);

					panel1[0].setBackground(Color.WHITE);
					con.add(panel1[0]);
					con.add(panel5);
					con.revalidate();
					con.repaint();
					break;

				case "사이드":
					for(JPanel jp:panel1)
						con.remove(jp);
					con.remove(panel5);

					panel1[1] = new LeftPanel();
					for(JButton jb_1:sideImg)	{
						panel1[1].add(jb_1);
					}

					for(int i=0; i<7; i++)	{	//16개 버튼 채우기
						JButton jb_1 = new JButton(basicImg);
						panel1[1].add(jb_1);
					}
					con.add(panel1[1]);
					con.add(panel5);
					con.revalidate();
					con.repaint();
					break;

				case "주류":
					for(JPanel jp:panel1)
						con.remove(jp);
					con.remove(panel5);

					panel1[2] = new LeftPanel();
					for(JButton jb_2:sakeImg)	{
						panel1[2].add(jb_2);
					}

					JButton jb_2 = new JButton(basicImg);
					panel1[2].add(jb_2);

					con.add(panel1[2]);
					con.add(panel5);
					con.revalidate();
					con.repaint();
					break;

				case "음료":
					for(JPanel jp:panel1)
						con.remove(jp);
					con.remove(panel5);

					panel1[3] = new LeftPanel();
					for(JButton jb_3:drinkImg)	{
						panel1[3].add(jb_3);
					}

					for(int i=0; i<9; i++)	{	//16개 버튼 채우기
						JButton jb_3 = new JButton(basicImg);
						panel1[3].add(jb_3);
					}

					con.add(panel1[3]);
					con.add(panel5);
					con.revalidate();
					con.repaint();
					break;
				}
			}
		}

	}

	class LeftPanel extends JPanel	{		
		public LeftPanel(){
			setSize(800, 800);
			setLocation(0, 100);
			setLayout(new GridLayout(3, 4));

		}
	}

	class RightPanel extends JPanel	{
		public RightPanel()	{
			setSize(480, 100);
			setLocation(800, 0);
			setLayout(null);
		}

		public void paintComponent(Graphics g)	{
			Dimension d = getSize();
			ImageIcon imageIcon = new ImageIcon("./src/menuImages/SalmonIsDigimon.png");
			g.drawImage(imageIcon.getImage(), 0, 0, d.width, d.height, null);
		}

	}

	class BottomPanel extends JPanel	{
		public BottomPanel()	{}
		public BottomPanel(String[] str)	{
			setSize(480, 200);
			setLocation(800, 700);
			setLayout(new GridLayout(1, 3));

			JButton[] jb= new JButton[3];

			ImageIcon cashButton = new ImageIcon("./src/menuImages/cashButton.png");
			ImageIcon cardButton = new ImageIcon("./src/menuImages/cardButton.png");
			ImageIcon cancelButton = new ImageIcon("./src/menuImages/cancelButton.png");

			Image image1 = cashButton.getImage();
			Image image2 = cardButton.getImage();
			Image image3 = cancelButton.getImage();

			Image newimg1 = image1.getScaledInstance( 170, 200,  java.awt.Image.SCALE_SMOOTH ) ;
			Image newimg2 = image2.getScaledInstance( 170, 200,  java.awt.Image.SCALE_SMOOTH ) ;
			Image newimg3 = image3.getScaledInstance( 170, 200,  java.awt.Image.SCALE_SMOOTH ) ;

			cashButton = new ImageIcon( newimg1 );
			cardButton = new ImageIcon( newimg2 );
			cancelButton = new ImageIcon( newimg3 );

			ImageIcon[] bottomImg = {cashButton, cardButton, cancelButton};

			for(int i=0; i<jb.length; i++)	{
				jb[i] = new JButton(str[i], bottomImg[i]);
				add(jb[i]);
				jb[i].addActionListener(new MyActionListener());
			}
		}

		class MyDialog extends JDialog {
			JButton okButton = new JButton(); //버튼생성하기 
			MyDialog(JFrame frame,String title) {
				super();
				this.setSize(200, 100);

				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
						setVisible(false);
					}
				});
			}
		}
	}

	public String[] orderMenu(Food f)	{
		str[0] = f.getMenuTitle();
		str[1] = Integer.toString(menucontroller.getOrderMap().get(f));
		str[2]= Integer.toString(f.getPrice());
		str[3] = "";
		return str;
	}


}
