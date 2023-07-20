public class WatermelonVine {
    WatermelonChunk[] watermelonChunks;
    int tail = 0;

    public WatermelonVine(){
        watermelonChunks = new WatermelonChunk[1];
    }

    public boolean addChunk(WatermelonChunk chunkToAdd) {
        boolean unique = true;
        for (int i = 0; i < tail; ++i) {
            if (chunkToAdd.equals(watermelonChunks[i])) {
                unique = false;
                break;
            }
        }

        if (unique) {
            if (tail >= watermelonChunks.length) {
                WatermelonChunk[] store = watermelonChunks;
                watermelonChunks = new WatermelonChunk[store.length * 2];
                for (int i = 0; i < tail; ++i) {
                    watermelonChunks[i] = store[i];
                }
            }
            watermelonChunks[tail] = chunkToAdd;
            ++tail;
        }

        return unique;
    }
}
