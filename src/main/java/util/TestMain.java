package util;


import org.apache.commons.lang.time.DateUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestMain {
    public static void main(String[] args) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        int n = 0;
        Scanner s = new Scanner(System.in);
        if(s.hasNextInt()) n = s.nextInt();
        int i = 0;
        while (s.hasNextInt()){
            if(i<n)first.add(s.nextInt());
            else second.add(s.nextInt());
        }
        System.out.println(first.toString());
        System.out.println(second.toString());



    }
    public static int minSum(List<Integer> num, int k){
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        q.addAll(num);
        for(int i = 0; i< k;i++){
            int a = q.poll();
            if(a%2==0) q.add(a/2);
            else q.add(a/2 +1);
        }
        int result = 0;
        for(int i = 0; i< num.size();i++){
            result += q.poll();
        }
        return result;
    }
}

class Logger{
    private StringBuilder contents = new StringBuilder();

    public void log(String message){
        contents.append(System.currentTimeMillis());
        contents.append(": ");
        contents.append(Thread.currentThread().getName());
        contents.append(message);
        contents.append("\n");
    }

    public StringBuilder getContents() {
        return contents;
    }
}

class Mythread extends Thread{

    Mythread(){
        System.out.println(" My Thread");
    }

    @Override
    public void run() {
        System.out.println(" bar");
    }

    public void run(String s){
        System.out.println("baz");
    }


    //    public Test t;
//    @Override
//    public void run() {
//
//           t.addNum();
//    }
}
class Test{
    AtomicInteger a = new AtomicInteger(0);
    private Object mutex = new Object();
    public void addNum(){
        for(int i =0; i<10000000;i++) {
                a.addAndGet(1);


            //System.out.println(Thread.currentThread().getName() + " " + a);
        }
    }
}
class Parent{
    int id;
    int val;

    public Parent() {
    }

    public Parent(int id, int val) {
        this.id = id;
        this.val = val;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object obj) {
        Parent in = (Parent)obj;
        return this.id == in.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", val=" + val +
                '}';
    }
}


