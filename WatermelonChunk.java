public class WatermelonChunk {
    private int[] amt;
    private int size;
    public WatermelonChunk (int initialAmt){
        amt = new int[6];
        amt[0] = initialAmt;
        size = 1;
    }

    public void addWaterMelonPiece(int pieceSize) {
        amt[size] = pieceSize;
        ++size;
    }

    public void sort() {
        int[] store = amt;
        amt = new int[6];
        int highestIndex = 0;
        for (int i = 0; i < size; ++i) {
            highestIndex = i;
            for (int j = i + 1; j < size; ++j) {
                if (store[j] > store[highestIndex]){
                    highestIndex = j;
                }
            }
            amt[i] = store[highestIndex];
            store[highestIndex] = 0;
        }
    }

    public int getAmount (int index) {
        return amt[index];
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof WatermelonChunk)) {
            return false;
        }
        for (int i = 0; i < 6; ++i) {
            if (amt[i] != ((WatermelonChunk) other).getAmount(i)) {
                return false;
            }
        }
        return true;
    }
}
