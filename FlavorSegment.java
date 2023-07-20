public class FlavorSegment implements Comparable{
    private Type flavor;
    private int amount;

    /**
     * Basic constructor.
     * @param flavor
     * @param amount
     */
    public FlavorSegment(Type flavor, int amount) {
        this.flavor = flavor;
        this.amount = amount;
    }

    /**
     * Setter for FlavorSegment's flavor or type.
     * @param flavor
     */
    public void setFlavor(Type flavor){
        this.flavor = flavor;
    }

    /**
     * Setter for the amount of juice the FlavorSegment represents.
     * @param amount
     */
    public void setAmount(int amount){
        this.amount = amount;
    }

    /**
     * Getter for the FlavorSegment's flavor or type.
     * @return The FlavorSegments Flavor or Type.
     */
    public Type getFlavor(){
        return flavor;
    }

    /**
     * Getter for the amount of juice the FlavorSegment represents.
     * @return The amount of juice the FlavorSegment represents.
     */
    public int getAmount(){
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FlavorSegment)) {
            return false;
        }
        else if (!flavor.equals(((FlavorSegment) obj).getFlavor())) {
            return false;
        }
        else if (!(amount == ((FlavorSegment) obj).getAmount())) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public  String toString() {
        return flavor + " " + amount;
    }

    public int compareTo(Object T){
        if (T == null || !(T instanceof FlavorSegment)) {
            return -2;
        }
        if (amount < ((FlavorSegment) T).getAmount()) {
            return 1;
        } else if (amount > ((FlavorSegment) T).getAmount()) {
            return  -1;
        } else {
            return flavor.name().compareTo(((FlavorSegment) T).getFlavor().name());
        }
    }
}
