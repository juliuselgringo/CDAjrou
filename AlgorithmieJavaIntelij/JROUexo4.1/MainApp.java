class MainApp {
    public static void main(String[] args) {
        int[] note = {0, 7, 2, 12, 9, 15, 12, 17, 20};
        int myArrayLength = note.length;
        int sum = 0;
        int myAverage = 0;
        for (int i = 0; i < note.length; i++) {
            sum = sum + note[i];
        }
        System.out.println("La somme des " + myArrayLength + " notes est : " + sum);
        myAverage = sum / note.length;
        System.out.println("La moyenne est de : " + myAverage + "/20");
    }
}