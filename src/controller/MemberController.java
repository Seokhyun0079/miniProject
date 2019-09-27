package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import model.vo.Member;

public class MemberController {
   private ArrayList<Member> memberList = new ArrayList<>();// <Member>객체만 받을 수 있는 어레이 리스트 선언
   private static final MemberController MEMBER_CONTROLLER = new MemberController();
   private MemberController() {
      memberInit("./src/memberTextFiles/member.txt", memberList);
   }

   private void memberInit(String address, ArrayList<Member> memberList) {
      try(BufferedReader memberBufferedReader 
            = new BufferedReader(new FileReader(new File(address)));)
      {
         // 텍스트파일에 입력된 정보를 임시로 저장할 변수 선언
         String memberName;
         String memberNumber;
         String memberPoint;
         //고명빈,"01000000000",30000 형식으로 텍스트 파일 저장되있음
         while ((memberName = memberBufferedReader.readLine()) != null) {
            String[] spt = memberName.split(",");
            memberName = spt[0];
            memberNumber = spt[1];
            memberPoint = spt[2];
            memberList.add(new Member(memberName, memberNumber, Integer.parseInt(memberPoint)));
         }//','를 기준으로 split해서 각각의 Name, Number, Point에 저장

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public ArrayList<Member> getMemberList() {
      return memberList;
   }

   ////////////////////////////////////////포인트 적립/////////////////////////////////////////////
   public void sendSavingMethod(Member m, int totalAmount) {
      int i = memberCheck(m.getMemberNumber());
      if (i==-1) {
         newPointSaving(m, totalAmount);
         System.out.println("신규회원 메소드 실행");
      } else {
         visitedPointSaving(i, totalAmount);
         System.out.println("기존회원 메소드 실행");
      }
   }

   public int memberCheck(String number) {// 기존회원인지 신규회원인지 판별하는 메소드 
                                 // 기존회원이면 리턴값으로 기존회원 인덱스값을 반환한다

      int check = -1;//index값 0부터 시작하기때문에 -1로 초기화

      for (int i = 0; i < memberList.size(); i++) {
         if (memberList.get(i).getMemberNumber().equals(number)) {
            check = i;// 동일한 폰번호를 가진 맴버 index값을 저장
            break;
         }
      }
      return check;
   }

   //////////////////////////////////회원 포인트 갱신 메소드///////////////////////////////////////
   public void visitedPointSaving(int index, int totalAmount) {// 기존회원 포인트 적립
      memberList.get(index).setMemberPoint(memberList.get(index).getMemberPoint() + (int)(totalAmount *0.1));
   }

   public void newPointSaving(Member m, int totalAmount) {// 신규회원 포인트 적립
      m.setMemberPoint((int) (totalAmount * 0.1));
      memberList.add(new Member(m.getMemberName(), m.getMemberNumber(), m.getMemberPoint()));
   }

  

   
   public void fileSaveMemberList () {
      try (BufferedWriter memberBufferedWriter = new BufferedWriter(new FileWriter("./src/memberTextFiles/member.txt"));
            ) {
         for (int i = 0; i < getMemberList().size(); i++) {
            memberBufferedWriter.write(memberList.get(i).getMemberName()+",");
            memberBufferedWriter.write(memberList.get(i).getMemberNumber()+",");
            memberBufferedWriter.write(memberList.get(i).getMemberPoint()+(i != getMemberList().size()-1 ? "\n" : ""));
            System.out.println(memberList.get(i)+"입력완료");
         }


      } catch (Exception e) {
         e.printStackTrace();
      }

   }
   
   public static MemberController getInstance() {
	      return MEMBER_CONTROLLER;
	   }

}