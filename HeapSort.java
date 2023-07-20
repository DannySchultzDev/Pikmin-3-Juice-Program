public class HeapSort{
    private Fruit[][] fruitArr;
    private int arr;
    public HeapSort(Fruit[] fruits) {
        int foo = 1;
        for (int i = fruits.length; i > 1; --i) {
            foo *= i;
        }
        fruitArr = new Fruit[foo][fruits.length];
        arr = 0;
        heapAlgo(fruits, fruits.length, fruits.length);
    }
    private void outputFruit(Fruit[] fruits, int n){
        for (int i = 0; i < n; ++i) {
            //System.out.print(fruits[i] + " ");
            fruitArr[arr][i] = fruits[i];
        }
        ++arr;
        //System.out.println("\n");
    }

    private void heapAlgo(Fruit[] fruits, int size, int n){
        if (size == 1) {
            outputFruit(fruits, n);
        }
        for (int i = 0; i < size; ++i) {
            heapAlgo(fruits, size - 1, n);

            if (size % 2 == 1) {
                Fruit foo = fruits[0];
                fruits[0] = fruits[size - 1];
                fruits[size - 1] = foo;
            } else {
                Fruit foo = fruits[i];
                fruits[i] = fruits[size - 1];
                fruits[size - 1] = foo;
            }
        }
    }

    public Fruit[][] getFruitArr(){
        return fruitArr;
    }
}
