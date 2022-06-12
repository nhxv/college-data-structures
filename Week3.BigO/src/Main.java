public class Main {
    public static void main(String[] args) {
        int sum = 0;
        int n = 4;

        // a> O(n)
//        for (int i = 0; i < n; i++) {
//            sum++;
//        }

        // b> O(n^2)
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                sum++;
//            }
//        }

        // c> O(n^3)
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n*n; j++) {
//                sum++;
//            }
//        }

        // d> O(n*(n-1)/2) -- O(n^2)
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < i; j++) {
//                sum++;
//            }
//        }

        // extra O(n^2*(n^2 - 1)/2)
//        for (int n = 0; i < n*n; i++) {
//            for (int j = 0; j < i; j++) {
//                sum++;
//            }
//        }

        // extra n*(n-1)*(2*n-1)/6
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < i*i; j++) {
//                sum++;
//            }
//        }

        // e> O(n^5)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i*i; j++) {
                for (int k = 0; k < j; k++) {
                    sum++;
                }
            }
        }

        // f> O(n^5)
//        for (int i = 1; i < n; i++) {
//            for (int j = 1; j < i * i; j++) {
//                if (j % i == 0) {
//                    for (int k = 0; k < j; k++) {
//                        sum++;
//                    }
//                }
//            }
//        }

        System.out.println(sum);
//        int test = ( n*(n-1)*(2*n-1)/6);
    }
}
