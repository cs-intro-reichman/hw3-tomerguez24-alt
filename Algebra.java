// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
    public static void main(String args[]) {
        System.out.println(plus(2,3));
        System.out.println(minus(7,2));
        System.out.println(minus(2,7));
        System.out.println(times(3,4));
        System.out.println(plus(2,times(4,2)));
        System.out.println(pow(5,3));
        System.out.println(pow(3,5));
        System.out.println(div(12,3));
        System.out.println(div(5,5));
        System.out.println(div(25,7));
        System.out.println(mod(25,7));
        System.out.println(mod(120,6));
        System.out.println(sqrt(36));
        System.out.println(sqrt(263169));
        System.out.println(sqrt(76123));
    }

    private static int subzero(int x) {
        int result = 0;
        if (x > 0) {
            while (x != 0) {
                x--;
                result--;
            }
        } else {
            while (x != 0) {
                x++;
                result++;
            }
        }
        return result;
    }

    public static int plus(int x1, int x2) {
        if (x2 > 0) {
            while (x2 != 0) {
                x1++;
                x2--;
            }
        } else {
            while (x2 != 0) {
                x1--;
                x2++;
            }
        }
        return x1;
    }

    public static int minus(int x1, int x2) {
        if (x2 > 0) {
            while (x2 != 0) {
                x1--;
                x2--;
            }
        } else {
            while (x2 != 0) {
                x1++;
                x2++;
            }
        }
        return x1;
    }

    public static int times(int x1, int x2) {
        boolean negative = false;

        if (x1 < 0) {
            x1 = subzero(x1);
            negative = !negative;
        }
        if (x2 < 0) {
            x2 = subzero(x2);
            negative = !negative;
        }

        int res = 0;
        int i = 0;
        while (i < x2) {
            res = plus(res, x1);
            i++;
        }

        if (negative) {
            res = subzero(res);
        }
        return res;
    }

    public static int pow(int x, int n) {
        int result = 1;
        int i = 0;
        while (i < n) {
            result = times(result, x);
            i++;
        }
        return result;
    }

    public static int div(int x1, int x2) {
        if (x2 == 0) {
            throw new IllegalArgumentException("Division by zero");
        }

        boolean negative = false;

        if (x1 < 0) {
            x1 = subzero(x1);
            negative = !negative;
        }
        if (x2 < 0) {
            x2 = subzero(x2);
            negative = !negative;
        }

        int count = 0;
        while (x1 >= x2) {
            x1 = minus(x1, x2);
            count++;
        }

        if (negative) {
            count = subzero(count);
        }
        return count;
    }

    public static int mod(int x1, int x2) {
        if (x2 == 0) {
            throw new IllegalArgumentException("Modulo by zero");
        }

        boolean negativeDividend = false;

        if (x1 < 0) {
            x1 = subzero(x1);
            negativeDividend = true;
        }
        if (x2 < 0) {
            x2 = subzero(x2);
        }

        while (x1 >= x2) {
            x1 = minus(x1, x2);
        }

        if (negativeDividend) {
            x1 = subzero(x1);
        }
        return x1;
    }

    public static int sqrt(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Negative number");
        }
        int r = 0;
        while (times(r + 1, r + 1) <= x) {
            r++;
        }
        return r;
    }
}

