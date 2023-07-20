public class GrapeVine {
    GrapeBunch[] grapeBunches;
    int tail = 0;

    public GrapeVine(){
        grapeBunches = new GrapeBunch[1];
    }

    public boolean addBunch(GrapeBunch bunchToAdd) {
        boolean unique = true;
        for (int i = 0; i < tail; ++i) {
            if (bunchToAdd.equals(grapeBunches[i])) {
                unique = false;
                break;
            }
        }

        if (unique) {
            if (tail >= grapeBunches.length) {
                GrapeBunch[] store = grapeBunches;
                grapeBunches = new GrapeBunch[store.length * 2];
                for (int i = 0; i < tail; ++i) {
                    grapeBunches[i] = store[i];
                }
            }
            grapeBunches[tail] = bunchToAdd;
            ++tail;
        }

        return unique;
    }
}
