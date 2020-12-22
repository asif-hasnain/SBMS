package util;


import org.apache.commons.lang.time.DateUtils;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestMain {
    public static void main(String[] args) {
        //List<List<Integer>> list = new ArrayList<>();
        //List<Integer> temp1 = new ArrayList<>(Arrays.asList(0,2));
        String a = "https://d1te02aoyf.execute-api.us-east-2.amazonaws.com/dev/brokerage/{brokerageid}/customers";
        String[] s = a.split("/");
        System.out.println(new Date(Calendar.getInstance().getTimeInMillis()-40000));
    }
    public static String ArrayChallenge(int[] arr){
        if(arr.length == 0) return "";
        int sum = 0;
        for(Integer a : arr) sum+=a;
        int half = sum/2;
        System.out.println("half: " + half);
        String result = "-1";
        Set<Integer> l = helper(arr,0,half,new HashSet<>());
        System.out.println("l: " + l);
        if(l != null){
            int len = arr.length/2;
            List<Integer> first = new ArrayList<>();
            List<Integer> second = new ArrayList<>();
            for(int i = 0 ; i< arr.length;i++){
                if(l.contains(i)) first.add(arr[i]);
                else second.add(arr[i]);
            }
            Collections.sort(first);
            Collections.sort(second);
            StringBuilder builder = new StringBuilder();
            String prefix = "";
            if(first.get(0)<second.get(0)){
                for(Integer i : first){
                    builder.append(prefix);
                    prefix = ",";
                    builder.append(i);
                }
                for(Integer i : second){
                    builder.append(prefix);
                    builder.append(i);
                }
            } else {
                for(Integer i : second){
                    builder.append(prefix);
                    prefix = ",";
                    builder.append(i);
                }
                for(Integer i : first){
                    builder.append(prefix);
                    builder.append(i);
                }
            }
            result = builder.toString();
        }
        return result;
    }
    public static Set<Integer> helper(int[] arr, int pos, int half, Set<Integer> temp){
        //System.out.println(half);
        if(half == 0 && temp.size()== arr.length/2) return temp;
        else if(half<0 ) return null;
        if(temp.size() >= arr.length/2) return null;
        if(pos >= arr.length) return null;
        temp.add(pos);
        Set<Integer> l1 = helper(arr, pos+1, half-arr[pos], temp);
        //System.out.println(l1);
        if(l1 != null) return l1;
        temp.remove(pos);
        Set<Integer> l2 = helper(arr, pos+1, half, temp);
        //System.out.println(l2);
        if(l2 != null) return l2;
        return null;
    }
    public static char slowestKey(List<List<Integer>> keyTimes){

        int max = keyTimes.get(0).get(1);
        int key = keyTimes.get(0).get(0);
        int prev = keyTimes.get(0).get(1);
        for(int i = 1;i<keyTimes.size();i++){
            int curr = keyTimes.get(i).get(1) - prev;
            if(curr > max){
                max = curr;
                key = keyTimes.get(i).get(0);
            }
        }
        return (char)(key + 97);
    }
    public static int numPlayers(int k, List<Integer> scores){
        Collections.sort(scores,Collections.reverseOrder());
        System.out.println(scores);
        int rank = 0;
        int count  = 0;
        int prev = -1;
        while(rank <= k  && count<scores.size() && scores.get(count) != 0){
            if(scores.get(count) != prev){
                rank = count+1;
                if(rank>k) return count;
            }
            prev = scores.get(count);
            count++;
        }
        return count;
    }
    public static List<Integer> commonPrefix(List<String> inputs){
        List<Integer> res = new ArrayList<>();
        for(int i = 0;i< inputs.size();i++){
            res.add(findPrefixLength(inputs.get(i)));
        }
        return res;
    }
    public static int findPrefixLength(String input){
        int sum = input.length();
        for(int i = 1 ; i< input.length();i++){
            String suffix = input.substring(i);
            int count = 0;
            for(int j = 0 ; j< suffix.length();j++){
                if(suffix.charAt(j) == input.charAt(j)) count++;
            }
            sum += count;
        }
        return sum;
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


