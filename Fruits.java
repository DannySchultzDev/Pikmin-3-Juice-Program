public class Fruits {
	private Fruit[] fruits;

	/**
	 * Constructor generates an array of fruits.
	 */
	public Fruits() {
		fruits = new Fruit[0];

		//Full dataset
		/*addCopies(new Fruit(Type.CANTALOUPE, 60), 1);
		addCopies(new Fruit(Type.DRAGON_FRUIT, 50), 1);
		addCopies(new Fruit(Type.MANGO,50 ), 1);
		addCopies(new Fruit(Type.PAPAYA, 50), 1);
		addCopies(new Fruit(Type.BANANA, 50), 1);
		addCopies(new Fruit(Type.TANGELO, 50), 2);
		addCopies(new Fruit(Type.ASIAN_PEAR, 40), 1);
		addCopies(new Fruit(Type.APPLE, 40), 1);
		addCopies(new Fruit(Type.PEACH, 40), 1);
		addCopies(new Fruit(Type.PEAR, 40), 1);
		addCopies(new Fruit(Type.PERSIMMON, 40), 2);
		addCopies(new Fruit(Type.GRAPEFRUIT, 40), 2);
		addCopies(new Fruit(Type.FIG, 30), 2);
		addCopies(new Fruit(Type.AVOCADO, 30), 2);
		addCopies(new Fruit(Type.LIME, 30), 3);
		addCopies(new Fruit(Type.LEMON, 30), 3);
		addCopies(new Fruit(Type.ORANGE, 30), 4);
		addCopies(new Fruit(Type.PLUM, 20), 3);
		addCopies(new Fruit(Type.MANGOSTEEN, 20), 3);
		addCopies(new Fruit(Type.STRAWBERRY, 20), 4);
		addCopies(new Fruit(Type.LOQUAT, 20), 4);
		addCopies(new Fruit(Type.UME, 20), 4);
		addCopies(new Fruit(Type.STAR_FRUIT, 20), 6);
		addCopies(new Fruit(Type.GOLD_KIWI, 15), 2);
		addCopies(new Fruit(Type.GREEN_KIWI, 15), 4);
		addCopies(new Fruit(Type.CHERRY, 10), 3);
		addCopies(new Fruit(Type.RASPBERRY, 10), 4);*/
		/*addCopies(new Fruit(Type.WATERMELON, 30), 1);
		addCopies(new Fruit(Type.WATERMELON, 10), 2);
		addCopies(new Fruit(Type.WATERMELON, 4), 2);
		addCopies(new Fruit(Type.WATERMELON, 2), 1);
		addCopies(new Fruit(Type.RED_GRAPE, 1), 60);
		addCopies(new Fruit(Type.WHITE_GRAPE, 1), 40);
		addCopies(new Fruit(Type.AIR, 1), 1);*/

		//addCopies(new Fruit(Type.WATERMELON, 30), 1);
		//addCopies(new Fruit(Type.WATERMELON, 10), 2);
		//addCopies(new Fruit(Type.WATERMELON, 4), 2);
		//addCopies(new Fruit(Type.WATERMELON, 2), 1);
		/*addCopies(new Fruit(Type.RED_GRAPE, 1), 60);
		addCopies(new Fruit(Type.WHITE_GRAPE, 1), 40);
		addCopies(new Fruit(Type.AIR, 1), 1);
		*/

		/*
		addCopies(new Fruit(Type.STRAWBERRY, 60), 1);
		addCopies(new Fruit(Type.LEMON, 60), 1);
		addCopies(new Fruit(Type.AIR, 1), 1);
		 */

		/*
		addCopies(new Fruit(Type.LEMON, 30), 3);
		addCopies(new Fruit(Type.ORANGE, 30), 4);
		addCopies(new Fruit(Type.MANGOSTEEN, 20), 3);
		addCopies(new Fruit(Type.STRAWBERRY, 20), 4);
		addCopies(new Fruit(Type.LOQUAT, 20), 4);
		addCopies(new Fruit(Type.CHERRY, 10), 3);
		addCopies(new Fruit(Type.AIR, 1), 1);
		*/


		/*
		addCopies(new Fruit(Type.PEAR, 40), 1);
		addCopies(new Fruit(Type.LOQUAT, 20), 2);
		addCopies(new Fruit(Type.CHERRY, 10), 2);
		addCopies(new Fruit(Type.WATERMELON, 30), 1);
		addCopies(new Fruit(Type.WATERMELON, 25), 2);
		addCopies(new Fruit(Type.WATERMELON, 10), 2);
		addCopies(new Fruit(Type.WATERMELON, 5), 1);
		addCopies(new Fruit(Type.RED_GRAPE, 1), 5);
		addCopies(new Fruit(Type.WHITE_GRAPE, 1), 5);
		addCopies(new Fruit(Type.AIR, 1), 1);
		*/

	}
	
	private void addCopies(Fruit fruit, int copies) {
		for(int i = 0; i < copies; ++i) {
			Fruit[] store = new Fruit[fruits.length + 1];
			for (int j = 0; j < fruits.length; ++j) {
				store[j] = fruits[j];
			}
			store[store.length-1] = fruit;
			fruits = store;
		}
	}

	/**
	 * Getter for the array of fruits.
	 * @return An array of fruits.
	 */
	public Fruit[] getFruits() {
		return fruits;
	}
}