public class Koppai {
    private Fruit[] fruits;

    private Fruit[] pastFruits;

    private boolean[] occupiedFruits;
    private Juice juices;

    private Juice[][][][][] uniqueJuiceMatrix;
    private int[][][][] uniqueJuiceMatrixTails;

    private  int totalTail;

    private Juice[] uniqueJuices;

    private double[] percents;
    private long pass = 0;

    private boolean passNegative;
    private int tail;

    private Type secondaryTarget;

    private int focusLevel;

    private GrapeVine[] grapeVines;

    private WatermelonVine[] watermelonVines;

    private int printReq;

    public static void main(String[] args) {
        Koppai koppai = new Koppai();
    }

    public Koppai (){
        fruits = (new Fruits()).getFruits();
        occupiedFruits = new boolean[fruits.length];
        tail = 0;
        printReq = 0;
        passNegative = false;
        Fruit[] availableFruits = new Fruit[40];
        percents = new double[40];
        uniqueJuiceMatrix = new Juice[31][31][31][31][1];
        uniqueJuiceMatrixTails = new int[31][31][31][31];
        for (int i = 0; i < 31; ++i) {
            for (int j = 0; j < 31; ++j) {
                for (int k = 0; k < 31; ++k) {
                    for (int l = 0; l < 31; ++l) {
                        uniqueJuiceMatrixTails[i][j][k][l] = 0;
                        uniqueJuiceMatrix[i][j][k][l][0] = null;
                    }
                }
            }
        }
        uniqueJuices = new Juice[1];

        //SET FOCUS LEVEL HERE!
        focusLevel = 0;

        secondaryTarget = fruits[1].getType();
        int foo = 2;
        while (secondaryTarget == fruits[0].getType()) {
            secondaryTarget = fruits[foo].getType();
            ++foo;
        }

        grapeVines = new GrapeVine[40];
        watermelonVines = new WatermelonVine[40];

        totalTail = 0;

        pastFruits = new Fruit[40];
        recursiveSelect(availableFruits, 0, 20, 0);
        pastFruits = new Fruit[40];
        recursiveSelect(availableFruits, 0, 40, 0);
        System.out.println(pass + " juice sets generated.");


        for (int i = 0; i < 31; ++i) {
            for (int j = 0; j < 31; ++j) {
                for (int k = 0; k < 31; ++k) {
                    for (int a = 0; a < 31; ++a) {
                        for (int l = 0; l < uniqueJuiceMatrixTails[i][j][k][a]/*[b][c]*/; ++l) {
                            System.out.println(uniqueJuiceMatrix[i][j][k][a]/*[b][c]*/[l]);
                        }
                    }
                }
            }
        }

        if (focusLevel == 0) {
            System.out.print(totalTail + " Unique juices. " + pass + " passes.");
        } else if (focusLevel == 1) {
            System.out.print(fruits[0].getType() + ": " + totalTail + " Unique juices. " + pass + " passes.");
        } else {
            System.out.print(fruits[0].getType() + " " + secondaryTarget + ": " + totalTail + " Unique juices. " + pass + " passes.");
        }
    }

    private void addUniqueJuices() {
        juices.compress();
        boolean unique = true;
        int[] ID = {juices.getTypeAtIndex(0).ordinal(), juices.getTypeAtIndex(1).ordinal(), juices.getTypeAtIndex(2).ordinal(), juices.getTypeAtIndex(3).ordinal()/*, juices.getTypeAtIndex(4).ordinal(), juices.getTypeAtIndex(5).ordinal()*/};

        for (int j = 0; j < uniqueJuiceMatrixTails[ID[0]][ID[1]][ID[2]][ID[3]]; ++j) {
            if (juices.equals(uniqueJuiceMatrix[ID[0]][ID[1]][ID[2]][ID[3]][j])) {
                unique = false;
                break;
            }
        }
        if (unique) {
            addUniqueJuice(ID, juices);
            //System.out.println(juices);
        }
    }


    /*private void recursiveSelect(Fruit[] availableFruits, int index, int capacity) {
        for (int i = 0; i < fruits.length; ++i)
        {
            while (i < fruits.length - 1 && ((fruits[i].equals(fruits[i + 1]) && !occupiedFruits[i + 1]) || (occupiedFruits[i]))) {
                ++i;
            }

            if (!occupiedFruits[i]) {
                availableFruits[index] = fruits[i];
                occupiedFruits[i] = true;
            } else {
                return;
            }

            if (availableFruits[index].getType() == Type.RED_GRAPE || availableFruits[index].getType() == Type.WHITE_GRAPE) {
                GrapeBunch grapeBunchToAdd = new GrapeBunch(availableFruits[index].getType() == Type.RED_GRAPE ? 1 : 0, availableFruits[index].getType() == Type.RED_GRAPE ? 0 : 1);
                int curIndex = index;
                while (curIndex > 0 && (availableFruits[curIndex - 1].getType() == Type.RED_GRAPE || availableFruits[curIndex - 1].getType() == Type.WHITE_GRAPE)) {
                    --curIndex;
                    grapeBunchToAdd.addGrape(availableFruits[curIndex].getType() == Type.WHITE_GRAPE);
                }
                if (curIndex == index) {
                    if (grapeVines[index] == null) {
                        grapeVines[index] = new GrapeVine();
                    }
                    grapeVines[index].addBunch(grapeBunchToAdd);
                } else {
                    if (!grapeVines[curIndex].addBunch(grapeBunchToAdd)) {
                        occupiedFruits[i] = false;
                        continue;
                    }
                }
            } else {
                if (grapeVines[index] != null) {
                    grapeVines[index] = null;
                }
            }

            //WATERMELON STUFF
            if (availableFruits[index].getType() == Type.WATERMELON) {
                WatermelonChunk watermelonChunkToAdd = new WatermelonChunk(availableFruits[index].getJuiceAmt());
                int curIndex = index;
                while (curIndex > 0 && (availableFruits[curIndex - 1].getType() == Type.WATERMELON)) {
                    --curIndex;
                    watermelonChunkToAdd.addWaterMelonPiece(availableFruits[curIndex].getJuiceAmt());
                }
                if (curIndex == index) {
                    if (watermelonVines[index] == null) {
                        watermelonVines[index] = new WatermelonVine();
                    }
                    watermelonVines[index].addChunk(watermelonChunkToAdd);
                } else {
                    if (!watermelonVines[curIndex].addChunk(watermelonChunkToAdd)) {
                        occupiedFruits[i] = false;
                        continue;
                    }
                }
            } else {
                if (watermelonVines[index] != null) {
                    watermelonVines[index] = null;
                }
            }

            if (++printReq > 99999) {
                String fruitsPresent = "";
                for (int j = 0; j < index; ++ j) {
                    fruitsPresent += (availableFruits[j].getType().toString().substring(0, 3) + ", ");
                }
                fruitsPresent += fruits[i].getType().toString().substring(0, 3) + ".";
                System.out.println("Pass: " + pass + ". Fruits present: " + fruitsPresent);
                printReq = 0;
            }

            if (focusLevel > 0 && fruits[0].getJuiceAmt() >= 40 && !(availableFruits[0].equals(fruits[0]))) {
                occupiedFruits[i] = false;
                return;
            }

            int minimumJuice = 1;
            for (int j = 1; j <= index; ++j) {
                minimumJuice += availableFruits[j].getJuiceAmt();
            }
            if (availableFruits[index].getType() == Type.AIR || minimumJuice >= capacity) {

                if (focusLevel <= 1) {
                    tryJuice(availableFruits, capacity);
                } else {
                    boolean success1 = false;
                    boolean success2 = false;
                    for (int j = 0; j <= index; ++j) {
                        if (availableFruits[j].getType() == fruits[0].getType()) {
                            success1 = true;
                        } else if (availableFruits[j].getType() == secondaryTarget) {
                            success2 = true;
                        }
                    }
                    if (success1 && success2) {
                        tryJuice(availableFruits,capacity);
                    }
                }
            }
            else {
                recursiveSelect(availableFruits, index + 1, capacity);
            }
            occupiedFruits[i] = false;
        }
    }*/

    /*private void recursiveSelect(Fruit[] availableFruits, int index, int capacity, int numberOfFocusedFruit) {
        for (int i = 0; i < fruits.length; ++i)
        {
            while (i < fruits.length - 1 && ((fruits[i].equals(fruits[i + 1]) && !occupiedFruits[i + 1]) || (occupiedFruits[i]))) {
                ++i;
            }

            if (!occupiedFruits[i]) {
                availableFruits[index] = fruits[i];
                if (focusLevel >= 1 && availableFruits[index].getType() == fruits[0].getType()) {
                    ++numberOfFocusedFruit;
                }
                occupiedFruits[i] = true;
            } else {
                return;
            }

            if (availableFruits[index].getType() == Type.RED_GRAPE || availableFruits[index].getType() == Type.WHITE_GRAPE) {
                GrapeBunch grapeBunchToAdd = new GrapeBunch(availableFruits[index].getType() == Type.RED_GRAPE ? 1 : 0, availableFruits[index].getType() == Type.RED_GRAPE ? 0 : 1);
                int curIndex = index;
                while (curIndex > 0 && (availableFruits[curIndex - 1].getType() == Type.RED_GRAPE || availableFruits[curIndex - 1].getType() == Type.WHITE_GRAPE)) {
                    --curIndex;
                    grapeBunchToAdd.addGrape(availableFruits[curIndex].getType() == Type.WHITE_GRAPE);
                }
                if (curIndex == index) {
                    if (grapeVines[index] == null) {
                        grapeVines[index] = new GrapeVine();
                    }
                    grapeVines[index].addBunch(grapeBunchToAdd);
                } else {
                    if (!grapeVines[curIndex].addBunch(grapeBunchToAdd)) {
                        occupiedFruits[i] = false;
                        continue;
                    }
                }
            } else {
                if (grapeVines[index] != null) {
                    grapeVines[index] = null;
                }
            }

            //WATERMELON STUFF
            if (availableFruits[index].getType() == Type.WATERMELON) {
                WatermelonChunk watermelonChunkToAdd = new WatermelonChunk(availableFruits[index].getJuiceAmt());
                int curIndex = index;
                while (curIndex > 0 && (availableFruits[curIndex - 1].getType() == Type.WATERMELON)) {
                    --curIndex;
                    watermelonChunkToAdd.addWaterMelonPiece(availableFruits[curIndex].getJuiceAmt());
                }
                watermelonChunkToAdd.sort();
                if (curIndex == index) {
                    if (watermelonVines[index] == null) {
                        watermelonVines[index] = new WatermelonVine();
                    }
                    watermelonVines[index].addChunk(watermelonChunkToAdd);
                } else {
                    if (!watermelonVines[curIndex].addChunk(watermelonChunkToAdd)) {
                        occupiedFruits[i] = false;
                        continue;
                    }
                }
            } else {
                if (watermelonVines[index] != null) {
                    watermelonVines[index] = null;
                }
            }

            if (++printReq > 99999) {
                String fruitsPresent = "";
                for (int j = 0; j < index; ++ j) {
                    fruitsPresent += (availableFruits[j].getType().toString().substring(0, 3) + ", ");
                }
                fruitsPresent += fruits[i].getType().toString().substring(0, 3) + ".";
                System.out.println("Pass: " + pass + ". Fruits present: " + fruitsPresent);
                printReq = 0;
            }

            if (focusLevel > 0 && fruits[0].getJuiceAmt() >= 40 && !(availableFruits[0].equals(fruits[0]))) {
                occupiedFruits[i] = false;
                return;
            }

            int minimumJuice = 1;
            for (int j = 1; j <= index; ++j) {
                minimumJuice += availableFruits[j].getJuiceAmt();
            }
            if (availableFruits[index].getType() == Type.AIR || minimumJuice >= capacity) {

                if (focusLevel <= 1) {
                    tryJuice(availableFruits, capacity);
                } else {
                    boolean success1 = false;
                    boolean success2 = false;
                    for (int j = 0; j <= index; ++j) {
                        if (availableFruits[j].getType() == fruits[0].getType()) {
                            success1 = true;
                        } else if (availableFruits[j].getType() == secondaryTarget) {
                            success2 = true;
                        }
                    }
                    if (success1 && success2) {
                        tryJuice(availableFruits,capacity);
                    }
                }
            } else {
                if (focusLevel >= 1) {
                    if (numberOfFocusedFruit >= 1) {
                        recursiveSelect(availableFruits, index + 1, capacity, numberOfFocusedFruit);
                    } else {
                        int takenSpace = 1;
                        for (int j = 1; j < index; ++j){
                            takenSpace = takenSpace += availableFruits[j].getJuiceAmt();
                        }
                        if (takenSpace + fruits[0].getJuiceAmt() < capacity) {
                            recursiveSelect(availableFruits, index + 1, capacity, numberOfFocusedFruit);
                        }
                    }
                } else {
                    recursiveSelect(availableFruits, index + 1, capacity, numberOfFocusedFruit);
                }
            }
            occupiedFruits[i] = false;
            if (focusLevel >= 1 && availableFruits[index].getType() == fruits[0].getType()) {
                --numberOfFocusedFruit;
            }
        }
    }*/

    private void recursiveSelect(Fruit[] availableFruits, int index, int capacity, int numberOfFocusedFruit) {
        for (int i = 0; i < fruits.length; ++i)
        {
            while (i < fruits.length - 1 && ((fruits[i].equals(fruits[i + 1]) && !occupiedFruits[i + 1]) || (occupiedFruits[i]))) {
                ++i;
            }

            if (!occupiedFruits[i]) {
                availableFruits[index] = fruits[i];
                if (focusLevel >= 1 && availableFruits[index].getType() == fruits[0].getType()) {
                    ++numberOfFocusedFruit;
                }
                occupiedFruits[i] = true;
            } else {
                return;
            }

            if (availableFruits[index].getType() == Type.RED_GRAPE || availableFruits[index].getType() == Type.WHITE_GRAPE) {
                if (focusLevel >= 1) {
                    if (index == 1) {

                    } else if (index > 0 && (availableFruits[index - 1].getType() == Type.RED_GRAPE || availableFruits[index - 1].getType() == Type.WHITE_GRAPE)){
                        if (availableFruits[index].getType() == Type.RED_GRAPE && availableFruits[index - 1].getType() == Type.WHITE_GRAPE) {
                            occupiedFruits[i] = false;
                            continue;
                        }
                    } else {
                        occupiedFruits[i] = false;
                        continue;
                    }
                } else {
                    GrapeBunch grapeBunchToAdd = new GrapeBunch(availableFruits[index].getType() == Type.RED_GRAPE ? 1 : 0, availableFruits[index].getType() == Type.RED_GRAPE ? 0 : 1);
                    int curIndex = index;
                    while (curIndex > 0 && (availableFruits[curIndex - 1].getType() == Type.RED_GRAPE || availableFruits[curIndex - 1].getType() == Type.WHITE_GRAPE)) {
                        --curIndex;
                        grapeBunchToAdd.addGrape(availableFruits[curIndex].getType() == Type.WHITE_GRAPE);
                    }
                    if (curIndex == index) {
                        if (grapeVines[index] == null) {
                            grapeVines[index] = new GrapeVine();
                        }
                        grapeVines[index].addBunch(grapeBunchToAdd);
                    } else {
                        if (!grapeVines[curIndex].addBunch(grapeBunchToAdd)) {
                            occupiedFruits[i] = false;
                            continue;
                        }
                    }
                }
            } else {
                if (grapeVines[index] != null) {
                    grapeVines[index] = null;
                }
            }

            //WATERMELON STUFF
            if (availableFruits[index].getType() == Type.WATERMELON) {
                WatermelonChunk watermelonChunkToAdd = new WatermelonChunk(availableFruits[index].getJuiceAmt());
                int curIndex = index;
                while (curIndex > 0 && (availableFruits[curIndex - 1].getType() == Type.WATERMELON)) {
                    --curIndex;
                    watermelonChunkToAdd.addWaterMelonPiece(availableFruits[curIndex].getJuiceAmt());
                }
                watermelonChunkToAdd.sort();
                if (curIndex == index) {
                    if (watermelonVines[index] == null) {
                        watermelonVines[index] = new WatermelonVine();
                    }
                    watermelonVines[index].addChunk(watermelonChunkToAdd);
                } else {
                    if (!watermelonVines[curIndex].addChunk(watermelonChunkToAdd)) {
                        occupiedFruits[i] = false;
                        continue;
                    }
                }
            } else {
                if (watermelonVines[index] != null) {
                    watermelonVines[index] = null;
                }
            }

            if (/*++printReq > 99999*/true) {
                String fruitsPresent = "";
                for (int j = 0; j < index; ++ j) {
                    fruitsPresent += (availableFruits[j].getType().toString().substring(0, 3) + ", ");
                }
                fruitsPresent += fruits[i].getType().toString().substring(0, 3) + ".";
                System.out.println("Pass: " + pass + ". Fruits present: " + fruitsPresent);
                printReq = 0;
            }

            if (focusLevel > 0 && fruits[0].getJuiceAmt() >= 40 && !(availableFruits[0].equals(fruits[0]))) {
                occupiedFruits[i] = false;
                return;
            }

            int minimumJuice = 1;
            for (int j = 1; j <= index; ++j) {
                minimumJuice += availableFruits[j].getJuiceAmt();
            }
            if (availableFruits[index].getType() == Type.AIR || minimumJuice >= capacity) {

                if (focusLevel <= 1) {
                    tryJuice(availableFruits, capacity);
                } else {
                    boolean success1 = false;
                    boolean success2 = false;
                    for (int j = 0; j <= index; ++j) {
                        if (availableFruits[j].getType() == fruits[0].getType()) {
                            success1 = true;
                        } else if (availableFruits[j].getType() == secondaryTarget) {
                            success2 = true;
                        }
                    }
                    if (success1 && success2) {
                        tryJuice(availableFruits,capacity);
                    }
                }
            } else {
                if (focusLevel >= 1) {
                    if (numberOfFocusedFruit >= 1) {
                        recursiveSelect(availableFruits, index + 1, capacity, numberOfFocusedFruit);
                    } else {
                        int takenSpace = 1;
                        for (int j = 1; j < index; ++j){
                            takenSpace = takenSpace += availableFruits[j].getJuiceAmt();
                        }
                        if (takenSpace + fruits[0].getJuiceAmt() < capacity) {
                            recursiveSelect(availableFruits, index + 1, capacity, numberOfFocusedFruit);
                        }
                    }
                } else {
                    recursiveSelect(availableFruits, index + 1, capacity, numberOfFocusedFruit);
                }
            }
            occupiedFruits[i] = false;
            if (focusLevel >= 1 && availableFruits[index].getType() == fruits[0].getType()) {
                --numberOfFocusedFruit;
            }
        }
    }

    /*private void tryJuice(Fruit[] availableFruits, int capacity) {
        ++pass;
        for (int i = 1; i <= availableFruits[0].getJuiceAmt(); ++i) {
            juices = new Juice(capacity);
            if (juices.juiceFruit(new Fruit(availableFruits[0].getType(),i)) != null) {

                if (focusLevel == 0) {
                    addUniqueJuices();
                } else if (focusLevel == 1) {
                    if (juices.findFlavorSegmentOfType(fruits[0].getType()) != null) {
                        addUniqueJuices();
                    }
                } else {
                    if (juices.findFlavorSegmentOfType(fruits[0].getType()) != null && juices.findFlavorSegmentOfType(secondaryTarget) != null) {
                        addUniqueJuices();
                    }
                }

                return;
            }
            for (int j = 1; j < availableFruits.length; ++j) {
                if (juices.juiceFruit(availableFruits[j]) != null) {

                    if (focusLevel == 0) {
                        addUniqueJuices();
                    } else if (focusLevel == 1) {
                        if (juices.findFlavorSegmentOfType(fruits[0].getType()) != null) {
                            addUniqueJuices();
                        }
                    } else {
                        if (juices.findFlavorSegmentOfType(fruits[0].getType()) != null && juices.findFlavorSegmentOfType(secondaryTarget) != null) {
                            addUniqueJuices();
                        }
                    }
                    break;
                }
            }
        }
    }*/

    private void tryJuice(Fruit[] availableFruits, int capacity) {
        ++pass;

        int maxChange = 0;
        boolean fail = false;
        for (int i = 0; i < availableFruits.length; ++i) {
            if (fail) {

            } else if (pastFruits[i] == null || !pastFruits[i].equals(availableFruits)) {
                fail = true;
            } else {
                if (i == 0) {
                    ++maxChange;
                } else {
                    maxChange += availableFruits[i].getJuiceAmt();
                }
            }
            if (availableFruits[i] == null && pastFruits == null) {
                break;
            }
            pastFruits[i] = availableFruits[i];
        }

        maxChange = capacity - maxChange;

        for (int i = 1; i <= availableFruits[0].getJuiceAmt() && i <= maxChange; ++i) {
            juices = new Juice(capacity);
            if (juices.juiceFruit(new Fruit(availableFruits[0].getType(),i)) != null) {

                if (focusLevel == 0) {
                    addUniqueJuices();
                } else if (focusLevel == 1) {
                    if (juices.findFlavorSegmentOfType(fruits[0].getType()) != null) {
                        addUniqueJuices();
                    }
                } else {
                    if (juices.findFlavorSegmentOfType(fruits[0].getType()) != null && juices.findFlavorSegmentOfType(secondaryTarget) != null) {
                        addUniqueJuices();
                    }
                }

                return;
            }
            for (int j = 1; j < availableFruits.length; ++j) {
                if (juices.juiceFruit(availableFruits[j]) != null) {

                    if (focusLevel == 0) {
                        addUniqueJuices();
                    } else if (focusLevel == 1) {
                        if (juices.findFlavorSegmentOfType(fruits[0].getType()) != null) {
                            addUniqueJuices();
                        }
                    } else {
                        if (juices.findFlavorSegmentOfType(fruits[0].getType()) != null && juices.findFlavorSegmentOfType(secondaryTarget) != null) {
                            addUniqueJuices();
                        }
                    }
                    break;
                }
            }
        }
    }

    public void addUniqueJuice (int[] ID, Juice juice) {

        if (uniqueJuiceMatrixTails[ID[0]][ID[1]][ID[2]][ID[3]] >= uniqueJuiceMatrix[ID[0]][ID[1]][ID[2]][ID[3]].length) {
            Juice[] store = uniqueJuiceMatrix[ID[0]][ID[1]][ID[2]][ID[3]];
            uniqueJuiceMatrix[ID[0]][ID[1]][ID[2]][ID[3]] = new Juice[store.length * 2];
            for (int i = 0; i < store.length; ++i) {
                uniqueJuiceMatrix[ID[0]][ID[1]][ID[2]][ID[3]][i] = store[i];
            }
        }
        uniqueJuiceMatrix[ID[0]][ID[1]][ID[2]][ID[3]][uniqueJuiceMatrixTails[ID[0]][ID[1]][ID[2]][ID[3]]] = juice;
        uniqueJuiceMatrixTails[ID[0]][ID[1]][ID[2]][ID[3]]++;
        ++totalTail;
    }
}
