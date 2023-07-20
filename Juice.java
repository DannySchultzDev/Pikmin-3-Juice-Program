public class Juice {
    private int fillAmt;
    private int capacity;
    private FlavorSegment[] flavorSegments;

    /**
     * Constructor for juice object.
     * @param capacity
     */
    public Juice(int capacity) {
        this.capacity = capacity;
        flavorSegments = new FlavorSegment[1];
        flavorSegments[0] = new FlavorSegment(Type.AIR, capacity);
        fillAmt = 0;
    }

    /**
     * Constructor with a flavorSegment Array.
     * @param flavorSegments The array of FlavorSegments.
     */
    public Juice (FlavorSegment[] flavorSegments){
        this.flavorSegments = flavorSegments;
        capacity = flavorSegments.length;
        fillAmt = 0;
        for (int i = 0; i < flavorSegments.length; ++i) {
            if (!flavorSegments[i].getFlavor().equals(Type.AIR)) {
                fillAmt += flavorSegments[i].getAmount();
            }
        }
    }

    /**
     * Turns a fruit into juice.
     * @param fruit Represents the fruit to juice.
     * @return A FlavorSegment representing any overflow.
     * An air segment being returned represents the bottle being full.
     * Null being returned represents the bottle being partially empty.
     */
    public FlavorSegment juiceFruit(Fruit fruit){
        if (fruit.getType() == Type.AIR) {
            return new FlavorSegment(Type.AIR,0);
        }

        if (fruit.getJuiceAmt() + fillAmt == capacity) {
            getAirSegment().setFlavor(fruit.getType());
            fillAmt = capacity;
            compressType(fruit.getType());
            return (new FlavorSegment(Type.AIR, 0));
        } else if (fruit.getJuiceAmt() + fillAmt > capacity) {
            int overflow = fruit.getJuiceAmt() + fillAmt - capacity;
            getAirSegment().setFlavor(fruit.getType());
            fillAmt = capacity;
            compressType(fruit.getType());
            return new FlavorSegment(fruit.getType(), overflow);
        } else {
            FlavorSegment flavorSegment = new FlavorSegment(fruit.getType(),fruit.getJuiceAmt());
            FlavorSegment[] store = new FlavorSegment[flavorSegments.length + 1];
            for (int i = 0; i < flavorSegments.length; ++i) {
                store[i] = flavorSegments[i];
            }
            store[flavorSegments.length] = flavorSegment;
            flavorSegments = store;
            fillAmt += flavorSegment.getAmount();
            getAirSegment().setAmount(getAirSegment().getAmount() - flavorSegment.getAmount());
            compressType(fruit.getType());
            return null;
        }
    }

    /**
     * Compresses a type of juice into a single flavor segment.
     * @param type Represents the type of juice to compress.
     * @return A boolean representing if the type needed any compression.
     */
    public boolean compressType(Type type) {
        FlavorSegment combinedSegment = new FlavorSegment(type, 0);
        boolean compressed = false;
        int counter = 0;
        for (int i = 0; i < flavorSegments.length; ++i){
            if (flavorSegments[i].getFlavor() == type) {
                combinedSegment.setAmount(combinedSegment.getAmount() + flavorSegments[i].getAmount());
                ++counter;
                compressed = (counter > 1);
            }
        }
        if (!compressed) {
            return false;
        }
        FlavorSegment[] store = new FlavorSegment[flavorSegments.length - (counter - 1)];
        int offset = 0;
        for (int i = 0; i < flavorSegments.length; ++i) {
            if (flavorSegments[i].getFlavor() == type) {
                ++offset;
                continue;
            }
            store[i - offset] = flavorSegments[i];
        }
        store[store.length - 1] = combinedSegment;
        flavorSegments = store;
        return true;
    }

    /**
     * Finds a FlavorSegment of a specific type.
     * @param type Represents the type of FlavorSegment to find.
     * @return The FlavorSegment found.
     */
    public FlavorSegment findFlavorSegmentOfType(Type type) {
        for (int i = 0; i < flavorSegments.length; ++i) {
            if (flavorSegments[i].getFlavor() == type) {
                return flavorSegments[i];
            }
        }
        return null;
    }

    /**
     * Compresses juice into its smallest ratio for easier comparisons.
     */
    public void compress() {

        for (int i = capacity; i > 0; --i) {
            if (fillAmt % i != 0) {
                continue;
            }
            boolean modSuccess = true;
            for (int j = 0; j < flavorSegments.length; ++j) {
                if (flavorSegments[j].getFlavor() == Type.AIR) {
                    continue;
                }
                else if(flavorSegments[j].getAmount() % i != 0) {
                    modSuccess = false;
                    break;
                }
            }
            if (!modSuccess) {
                continue;
            }
            else {
                for (int j = 0; j < flavorSegments.length; ++j) {
                    if (flavorSegments[j].getFlavor() == Type.AIR) {
                        continue;
                    }
                    int oldAmt = flavorSegments[j].getAmount();
                    int newAmt = oldAmt/i;
                    flavorSegments[j].setAmount(newAmt);
                    fillAmt -= oldAmt - newAmt;
                    if (getAirSegment() != null) {
                        getAirSegment().setAmount(getAirSegment().getAmount() + oldAmt - newAmt);
                    } else {
                        FlavorSegment flavorSegment = new FlavorSegment(Type.AIR, oldAmt - newAmt);
                        FlavorSegment[] store = new FlavorSegment[flavorSegments.length + 1];
                        for (int k = 0; k < flavorSegments.length; ++k) {
                            store[k] = flavorSegments[k];
                        }
                        store[flavorSegments.length] = flavorSegment;
                        flavorSegments = store;
                    }
                }
            }
        }
        if (capacity == 20) {
            capacity = 40;
            getAirSegment().setAmount(getAirSegment().getAmount() + 20);
            compressType(Type.AIR);
        }
        FlavorSegment store = null;
        int storeIndex;
        for (int i = 0; i < flavorSegments.length; ++i) {
            store = flavorSegments[i];
            storeIndex = i;
            for (int j = i + 1; j < flavorSegments.length; ++j) {
                if (flavorSegments[j].compareTo(store) < 0) {
                    store = flavorSegments[j];
                    storeIndex = j;
                }
            }
            flavorSegments[storeIndex] = flavorSegments[i];
            flavorSegments[i] = store;
        }
    }

    /**
     * Gets the Air in the juice.
     * @return The FlavorSegment representing the air in the juice.
     */
    public FlavorSegment getAirSegment() {
        return findFlavorSegmentOfType(Type.AIR);
    }

    /**
     * Getter for the array of FlavorSegments.
     * @return the array of FlavorSegments.
     */
    public FlavorSegment[] getFlavorSegments() {
        return flavorSegments;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Juice)) {
            return false;
        }
        FlavorSegment[] other = ((Juice) obj).getFlavorSegments();
        if (flavorSegments.length  != other.length) {
            return false;
        }
        for (int i = 0; i < flavorSegments.length; ++i) {
            if (flavorSegments[i].getFlavor().equals(Type.AIR)) {
                continue;
            }
            boolean success = false;
            for (int j = 0; j < other.length; ++j) {
                if (flavorSegments[i].equals(other[j])) {
                    success = true;
                }
            }
            if (!success) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < flavorSegments.length; ++i) {
            output = output + flavorSegments[i] + ((i != flavorSegments.length - 1) ? ", " : "");
        }
        return output;
    }

    public Type getTypeAtIndex (int index) {
        if (index >= flavorSegments.length) {
            return Type.AIR;
        }
        return flavorSegments[index].getFlavor();
    }
}
