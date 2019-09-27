package view;


import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controller.MemberController;
import controller.MenuController;
import model.vo.Member;

class NullDialog extends JDialog {
   
   Container con = getContentPane();
   
   JLabel nullD = new JLabel("메뉴를 선택하세요");
   JButton nullB = new JButton("확인");
   
   NullDialog(JFrame frame, String title) {

         super(frame,title,true);
         super.add(nullD);
         this.setLayout(null);
         this.add(nullB);
         
         nullD.setBounds(90, 90, 200,30 );
         //complete.setFont(f1);
         nullB.setBounds(110, 170, 60,30);
         
         this.setSize(300,300);
         this.setLocation(500, 300);
     
      

         nullB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
               
              dispose();
               
               
            } 
         });
      }
}
class CompleteDialog extends JDialog {
   
   Container con = getContentPane();
   
   
   Font f1 = new Font("돋움", Font.BOLD, 18);
   
   JLabel complete = new JLabel("결제 완료!");
   JButton comp = new JButton("확인");
   
   CompleteDialog(JFrame frame, String title) {

      super(frame,title,true);
      super.add(complete);
      this.setLayout(null);
      this.add(comp);
      
      complete.setBounds(90, 90, 200,30 );
      complete.setFont(f1);
      comp.setBounds(110, 170, 60,30);
      
      this.setSize(300,300);
      this.setLocation(500, 300);
  
   

      comp.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent ae) {
            
           dispose();
            
            
         } 
      });
   }
   
}

class PointCheckDialog extends JDialog {
   
   MenuController menuC = MenuController.getInstance();
   
   PointDialog pd;
   CompleteDialog ctd;
   Container con = getContentPane();
   
   Font f1 = new Font("돋움", Font.BOLD, 15);

   JLabel check = new JLabel("포인트 적립 하시겠습니까?");
   JButton okBt = new JButton("확인");
   JButton cancleBt = new JButton("취소");
   
   PointCheckDialog(JFrame frame, String title) {

      super(frame,title,true);
      super.add(check);
      this.setLayout(null);
      this.add(okBt);
      this.add(cancleBt);
      check.setBounds(55, 90, 250,30 );
      check.setFont(f1);
      okBt.setBounds(80, 170, 60,30);
      cancleBt.setBounds(145, 170, 60, 30);
      
      this.setSize(300,300);
      this.setLocation(500, 300);

   

      okBt.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent ae) {
            
            dispose();
            pd = new PointDialog(new JFrame(), "포인트 적립");
            pd.setVisible(true);
            
         }
      });
      
      cancleBt.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent ae) {
            
            dispose();
            
            int row = MenuView.model1.getRowCount();

            for(int i=row-1; 0<=i; i--)   {
               MenuView.model1.removeRow(i);
            }

            MenuView.table.updateUI();
            MenuView.resultTable.setValueAt(0, 0, 0);
            MenuView.resultTable.setValueAt(0, 0, 1); 
            dispose();
            
            ctd = new CompleteDialog(new JFrame(), "결제 완료");
            ctd.setVisible(true);
            menuC.getOrderMap().clear();
            
         
            
            
         }
      });
   
   }
   
         
}

class ErrorPointDialog extends JDialog {
   
   Container con = getContentPane();
   
   JLabel Error = new JLabel("잘못 입력 하셨습니다.");
   JButton ErrorB = new JButton("확인");

   ErrorPointDialog(JFrame frame, String title) {

      super(frame,title,true);
      super.add(Error);
      this.setLayout(null);
      Error.setBounds(30, 125, 180,30);
      this.add(ErrorB);
      ErrorB.setBounds(75, 185, 60,30);
      this.setSize(300,300);
      this.setLocation(500, 300);

      

      ErrorB.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent ae) {

            dispose();
         }
      });
   }
}





class CheckDialog extends JDialog {

 
   MenuController mc = MenuController.getInstance();
   
//   MemberController mb = MemberController.getInstance();
//   ArrayList <Member> memberList = mb.getMemberList();
   
   CompleteDialog ctd;
   
  
   int total= mc.calcOrder();
   double total2 = total*0.1;
 

   Font f1 = new Font("돋움", Font.BOLD, 15);

   JLabel namelb = new JLabel(PointDialog.uName);
   JLabel accountLb = new JLabel(" "+total2);
   JLabel name = new JLabel("님 !");
   JLabel account = new JLabel("점 적립되셨습니다.");
   
//  JLabel totalpt = new JLabel(""+mb.getMemberPoint()) ;
    
   JButton ok = new JButton("확인");

   CheckDialog(JFrame frame, String title) {
      super(frame,title,true);

      System.out.println(total);
      System.out.println(total2);

      super.add(namelb);
      namelb.setFont(f1);
      namelb.setBounds(60, 80, 150, 40);
      namelb.setForeground(Color.BLUE);

      super.add(name);
      name.setFont(f1);
      name.setBounds(200, 80, 150, 40);


      super.add(accountLb);
      accountLb.setFont(f1);
      accountLb.setBounds(40, 100, 150, 40);
      accountLb.setForeground(Color.BLUE);

      super.add(account);
      account.setFont(f1);
      account.setBounds(120, 100, 150, 40);
      
//      super.add(totalpt);
//      totalpt.setBounds(40,150,150,40);
      
      
      this.add(ok);
      ok.setBounds(110, 185, 60,30);

      JLabel adf = new JLabel();
      this.add(adf);
      adf.setVisible(false);

      this.setSize(300, 300);
      this.setLocation(500, 300);
      //this.setLayout(new FlowLayout());
   

      ok.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent ae) {
            
            dispose();
            
            ctd = new CompleteDialog(new JFrame(), "결제 완료");
            ctd.setVisible(true);
            
            
         }
      });

   }

}



class PointDialog extends JDialog {
   
   PointCheckDialog pcd;
   ErrorPointDialog EPDialog;
   CheckDialog cDialog;
   MyActionListener ml;
   Container con = getContentPane();

   MemberController memberC = MemberController.getInstance();
   MenuController menuC = MenuController.getInstance();
   Member m = null;

   static String uName;
   static String uPhone;

   JLabel point = new JLabel("이름을 입력하세요");
   JLabel point2 = new JLabel("핸드폰 번호를 입력하세요");
   JButton ok = new JButton("확인");   
   JButton cancel = new JButton("취소");
   JTextField phone = new JTextField(10);
   JTextField name = new JTextField(10);
   
   Font f1 = new Font("돋움", Font.BOLD, 15);

   PointDialog(JFrame frame, String title) {

      super(frame,title,true);
      super.add(point);
      super.add(point2);
      this.setLayout(null);
      this.add(name);
      this.add(phone);
      this.add(ok);
      this.add(cancel);
      point.setBounds(30, 20, 300, 40);
      point.setFont(f1);
      name.setBounds(30, 55, 180,30 );
      point2.setBounds(30, 90, 300, 40);
      point2.setFont(f1);
      phone.setBounds(30, 125, 180,30 );
      ok.setBounds(75, 185, 60,30);
      cancel.setBounds(145, 185, 60, 30);
      
      this.setSize(300,300);
      this.setLocation(500, 300);
      

      ok.addActionListener(new ActionListener() {


         public void actionPerformed(ActionEvent ae) {

            uName = name.getText();
            uPhone = phone.getText();     

            if(uName.length() == 0 || uPhone.length() == 0)
            {
               //EPDialog.setVisible(true);
               EPDialog = new ErrorPointDialog(new JFrame(), "EPDialog");
               EPDialog.setVisible(true);
            }


            else
            {
               addMembership(uName, uPhone);
               
               int row = MenuView.model1.getRowCount();

               for(int i=row-1; 0<=i; i--)   {
                  MenuView.model1.removeRow(i);
               }
               MenuView.table.updateUI();
               MenuView.resultTable.setValueAt(0, 0, 0);
               MenuView.resultTable.setValueAt(0, 0, 1); 
               
               dispose();
               
               cDialog = new CheckDialog(new JFrame(), "CheckDialog");
               cDialog.setVisible(true);
               
               menuC.getOrderMap().clear();
            }

         }
      });
      
      cancel.addActionListener(new ActionListener() {


         public void actionPerformed(ActionEvent ae) {
            
            dispose();
            pcd = new PointCheckDialog(new JFrame(), "포인트 적립");
            pcd.setVisible(true);
            
         }
      });

   }



   public void addMembership(String name, String phone)   {
      m = new Member(name, phone);
      int sum = menuC.calcOrder();

      //System.out.println(m);
      //System.out.println(sum);

      if(!m.equals(null))
         memberC.sendSavingMethod(m, (int)(sum*0.1));
      //memberC.fileSaveMemberList();

      for(Member mem : memberC.getMemberList()) {
         //System.out.println(mem);
      }

   }
}


class MyActionListener extends JFrame implements ActionListener {

   MenuController menuC = MenuController.getInstance();
   //PointDialog dialog;
   PointCheckDialog pcd;
   NullDialog nd;
   
   Container con;
   String[] payment = {"현금", "카드", "취소"};

   JLabel label2 = new JLabel(" 현금 결제 하시겠습니까?");
   JLabel label3  =new JLabel(" 카드 결제 하시겠습니까?");
   JLabel label4 = new JLabel("취소하시겠습니까? ");


   JButton b = new JButton("확인");
   JButton b2 = new JButton("취소");

   Font f1 = new Font("돋움", Font.BOLD, 15);

   MyActionListener() {

      super("결제창");
      con = getContentPane();
      //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //JButton btn = new JButton("Show Dialog");
   }


   public void actionPerformed(ActionEvent e) {

      //pcd = new PointDialog(this,"포인트적립");
      if(menuC.calcOrder()==0)
      {
         nd = new NullDialog(new JFrame(), "잘못입력하셨습니다.");
           nd.setVisible(true);
      }
      else
     {

      if(e.getActionCommand().equals(payment[0]))
      {

         b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

               dispose(); //현재창닫기 
               pcd = new PointCheckDialog(new JFrame(), "포인트 적립");
               pcd.setVisible(true);
               //JButton b = (JButton)ae.getSource();

            }
         });

         b2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
               dispose();
               //setVisible(false);

            }
         });

         //super.add(label);
         super.add(label2);
         label2.setBounds(35, 40, 300, 40);
         label2.setFont(f1);
         this.setLayout(null);

         this.add(b);
         this.add(b2);
         b.setBounds(55, 100, 60, 30);
         b2.setBounds(125, 100, 60,30 );

         this.setSize(250,200);
         this.setVisible(true);
         this.setLocation(500, 300);
         

      }
      else if (e.getActionCommand().equals(payment[1]))
      {
         b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

               dispose();
               
               pcd = new PointCheckDialog(new JFrame(), "포인트 적립");
               pcd.setVisible(true);
               
               //JButton b = (JButton)ae.getSource();

            }
         });

         b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               //setVisible(false);
               dispose();
            }
         });


         //super.add(label);
         super.add(label3);
         label3.setBounds(35, 40, 300, 40);
         label3.setFont(f1);
         this.setLayout(null);

         this.add(b);
         this.add(b2);
         b.setBounds(55, 100, 60, 30);
         //         b.setBounds(100, 230, 40,20 );
         b2.setBounds(125, 100, 60,30 );

         this.setSize(250,200);
         this.setVisible(true);
         this.setLocation(500, 300);
   

      }

      else if (e.getActionCommand().equals(payment[2]))
      {
         menuC.getOrderMap().clear();

         int row = MenuView.model1.getRowCount();
         for(int i=row-1; 0<=i; i--)   {

            MenuView.model1.removeRow(i);
         }

         MenuView.table.updateUI();
         MenuView.resultTable.setValueAt(0, 0, 0);
         MenuView.resultTable.setValueAt(0, 0, 1);         
      }
   }
   }
}