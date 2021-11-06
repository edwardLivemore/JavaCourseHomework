public class Hello {
    public static void main(String[] args) {
        byte num1 = 1;
        int num2 = 2;
        long num3 = 3L;
        double num4 = 4D;

        for(int i = 0; i < 10; i++){
            double value = (num1 + num2 + num3 + num4) * i;
            if(value <= 50){
                System.out.println("current i : " + i + " , value = " + value);
            }
        }
    }
}
