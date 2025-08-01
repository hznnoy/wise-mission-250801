package com.back;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Wise> wiselist = new ArrayList<>();
    static class Wise{
        int id;
        String wise;
        String author;

        Wise(int id, String wise, String author){
            this.id = id;
            this.wise = wise;
            this.author = author;
        }
    }
    public static void main(String[] agrs) {
        System.out.println("== 명언 앱 ==");

        int wiseId = 0;

        while(true){
            System.out.print("명령) ");
            String command = sc.next();

            if(command.equals("종료")){
                break;
            }else if (command.equals("등록")) {
                sc.nextLine(); // 버퍼 제거
                wiseId++;
                create(wiseId);
            }else if(command.equals("목록")) {
                wiseList();
            }else if(command.startsWith("삭제")){
                int id = parseId(command); // id 파싱 함수 호출
                delete(id);
            }else if(command.startsWith("수정")){
                int id = parseId(command);
                edit(id);
            }
        }
    }

    public static void create(int wiseId){
        System.out.print("명언 : ");
        String w = sc.nextLine();

        System.out.print("작가 : ");
        String a = sc.nextLine();

        Wise newWise = new Wise(wiseId,w,a);
        wiselist.add(newWise);

        System.out.println(wiseId+"번 명언이 등록되었습니다.");
    }

    public static void wiseList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for(int i=wiselist.size()-1; i>=0; i--) {
            Wise w = wiselist.get(i);
            System.out.println(w.id + " / " + w.author + " / " + w.wise);
        }
    }

    public static void delete(int wiseId){
        for(int i=0; i<wiselist.size(); i++){
            if(wiseId==wiselist.get(i).id){
                wiselist.remove(i);
                System.out.println((i+1)+"번 명언이 삭제되었습니다.");
                return;
            }
        }

        System.out.println(wiseId+"번 명언은 존재하지 않습니다.");  //예외처리

    }

    public static void edit(int wiseId){
        for(int i=0; i<wiselist.size(); i++){
            if(wiseId==wiselist.get(i).id){
                System.out.println("명언(기존) : "+wiselist.get(i).wise);

                sc.nextLine(); // 버퍼 제거

                System.out.print("명언 : ");
                String w = sc.nextLine();

                System.out.println("작가(기존) : "+wiselist.get(i).author);
                System.out.print("작가 : ");
                String a = sc.nextLine();

                wiselist.remove(i); // 기존 명언 삭제
                Wise newWise = new Wise(i,w,a); // 수정된 명언 재등록
                wiselist.add(newWise);

                return;
            }
        }

        System.out.println(wiseId+"번 명언은 존재하지 않습니다.");  //예외처리

    }

    public static int parseId(String command){
        String[] parts = command.split("=");
        return Integer.parseInt(parts[1]);
    }
}
