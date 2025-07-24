class  MainApp {

    public static void main(String[] args) {
        int[][] twoDimArray = {
                { 1, 2, 3, 4, 5, 6 ,7 ,8 ,9,10,11,12},
                {11,12,13,14,15,16,17,18,19,20,21,22},
                {21,22,23,24,25,26,27,28,29,30,31,32},
                {41,42,43,44,45,146,47,48,49,50,51,52},
                {51,52,53,54,55,56,57,58,59,60,61,62},
                {61,62,63,64,65,66,67,68,69,70,71,72},
                {71,72,73,74,75,76,77,78,79,80,81,82},
                {81,82,83,84,85,86,87,88,89,90,91,92},
        };
        int maxVal = 0;
        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length; j++) {
                if(twoDimArray[i][j] > maxVal) {
                    maxVal = twoDimArray[i][j];
                }
            }
        }
        System.out.println("La plus grande valeure est : " + maxVal);
    }
}