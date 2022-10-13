import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inversions {

    private static final boolean debug = true;
    private static class InvertedList {
        List<Integer> list;
        int numInversions;

        public InvertedList(List<Integer> list, int numInversions){
            this.list = list;
            this.numInversions = numInversions;
        }

        public List<Integer> getList() {
            return list;
        }

        public int getNumInversions() {
            return numInversions;
        }
    }

    public static InvertedList CountSort(List<Integer> list){
        if(list.size() == 1){
            return new InvertedList(list, 0);
        }

        InvertedList lower = CountSort(list.subList(0, list.size() / 2));
        InvertedList upper = CountSort(list.subList((list.size() / 2), list.size()));
        InvertedList newList = MergeCount(lower.getList(), upper.getList());
        return new InvertedList(newList.getList(),
                lower.getNumInversions() + upper.getNumInversions() + newList.getNumInversions());
    }

    private static InvertedList MergeCount(List<Integer> A, List<Integer> B) {
        ArrayList<Integer> S = new ArrayList<>();
        int numInversions = 0;

        int indexA = 0;
        int indexB = 0;

        while(indexA < A.size() && indexB < B.size()){
            if(A.get(0) < B.get(0)){
                S.add(A.get(indexA));
                indexA += 1;
            }
            else{
                S.add(B.get(indexB));
                indexB += 1;
                numInversions += (A.size() - indexA);
            }
        }
        if(indexA < A.size()){
            for(int i = indexA; i < A.size(); i += 1){
                S.add(A.get(i));
            }
        }
        else{
            for(int i = indexB; i < B.size(); i += 1){
                S.add(B.get(i));
            }
        }

        return new InvertedList(S, numInversions);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int instances = in.nextInt();
        for(int i = 0; i < instances; i += 1){
            int numElements = in.nextInt();

            ArrayList<Integer> list = new ArrayList<>();
            for(int j = 0; j < numElements; j += 1){
                list.add(in.nextInt());
            }
            if(debug){
                System.out.println("list: " + list);
            }
            System.out.println(CountSort(list).getNumInversions());
        }
    }
}
