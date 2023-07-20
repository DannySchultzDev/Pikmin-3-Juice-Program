public class GrapeBunch {
    private int redAmt;
    private int whiteAmt;

    public GrapeBunch(int redAmt, int whiteAmt) {
        this.redAmt = redAmt;
        this.whiteAmt = whiteAmt;
    }

    /**
     * Adds a grape to a bunch.
     * @param typeOfGrape boolean representing the type of grape to add. 0 represents red grapes, 1 represents white grapes.
     */
    public void addGrape(boolean typeOfGrape) {
        if (!typeOfGrape) {
            ++redAmt;
        } else {
            ++whiteAmt;
        }
    }

    public int getRedAmt() {
        return redAmt;
    }

    public int getWhiteAmt() {
        return whiteAmt;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof  GrapeBunch)) {
            return false;
        }
        if (redAmt != ((GrapeBunch) other).getRedAmt()) {
            return false;
        } else if (whiteAmt != ((GrapeBunch) other).getWhiteAmt()) {
            return false;
        }
        return  true;
    }
}
