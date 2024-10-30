public class question2 {

    public static void main(String[] args) {

    }

    static int CalcOpt(int n){
        return 0;
    }

    static int[] PostProcess(int n, int[] M){
        int[] S = new int[n];






        int[] sNoNulls = new int[S.length];
        //copy S to sNoNulls
        int k = 0;
        for (int p : S) {
            if (p != 0) {
                sNoNulls[k] = p;
                k++;
            }
        }
        return sNoNulls;
    }

}