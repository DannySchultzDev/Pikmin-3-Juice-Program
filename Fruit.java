public class Fruit {
	private Type type;
	private int juiceAmt;

	private static int ID = 0;
	private int fruitID;

	/**
	 * Basic constructor
	 * @param type
	 * @param juiceAmt
	 */
	public Fruit (Type type, int juiceAmt) {
		this.type = type;
		this.juiceAmt = juiceAmt;
		fruitID = ID;
		++ID;
	}

	/**
	 * Getter for the type of fruit.
	 * @return The type of fruit.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Getter for the amount of juice the fruit creates.
	 * @return The amount of juice the fruit creates.
	 */
	public int getJuiceAmt() {
		return juiceAmt;
	}

	/**
	 * Getter for FruitID.
	 * @return The fruit's ID.
	 */
	public int getFruitID() {
		return fruitID;
	}

	@Override
	public String toString() {
		return (type + " " + juiceAmt);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Fruit)) {
			return false;
		}
		return (juiceAmt == ((Fruit) obj).getJuiceAmt() && type == ((Fruit) obj).getType());
	}
}