import java.io.*;
import java.util.*;

public class Main {
    class Pair {
        public Pair(int x,int y) {
            this.first = x;
            this.second = y;
        }
        public int first;
        public int second;
    }
    
    public static ArrayList<Pair> solve(ArrayList<Integer> nums, int x) {
        HashMap<Integer,Integer> counts = new HashMap<>();
        ArrayList<Pair> results = new ArrayList<>();
        for(int number:nums) {
            if(counts.get(number) == null) {
                counts.put(number,1);
            } else {
                counts.put(number,counts.get(number)+1);
            }
        }
        Iterator it = counts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            int key = (int) pair.getKey();
            int leftN = (int)pair.getValue();
            if(counts.get(x-key) != null) {
                int rightN = counts.get(x-key);
                int min = (leftN<rightN?leftN:rightN);
                for(int i = 0;i<min;i++) {
                    rightN--;
                    leftN--;
                    results.add(new Pair(1,2));
                }
                counts.remove(key);
                counts.remove(x-key);
            }
        }
        return results;
    }
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String s;
    while ((s = in.readLine()) != null) {
        String st[] = s.split(",");
        String last[] = st[st.length-1].split(";");
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i=0;i<st.length-1;i++) {
            nums.add(Integer.valueOf(st[i]));
        }
        nums.add(Integer.valueOf(last[0]));
        int x = Integer.valueOf(last[1]);
        ArrayList<Pair> results = solve(nums,x);
        if(results.size() == 0) {
             System.out.println("NULL");
        }
        for(int i=0;i<results.size();i++) {
            if(i!=0)  System.out.print(";");
            System.out.println(results.get(i).first + "," + results.get(i).second);
        }
    }
  }
}
