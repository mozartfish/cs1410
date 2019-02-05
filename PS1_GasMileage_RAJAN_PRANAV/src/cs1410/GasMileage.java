package cs1410;

import cs1410lib.Dialogs;

/**
 * The GasMileage program implements a series of pop-up dialog boxes that prompt
 * a user for a car model, the number of miles driven since the last time the
 * car's gas tank was filled, the price of a gallon of gasoline and the number
 * of gallons that would be required to fill the car's gas tank. After receiving
 * this information, the program displays a report in the console and in a
 * single pop-up dialog box the model of the car, the cost to fill the gas tank,
 * the miles per gallon since the the last fill-up, and the cost of gas per mile
 * since the last fill-up.
 * 
 * @author Pranav Rajan
 */

public class GasMileage {

	/**
	 * A method that takes a series of inputs and performs operations on them and
	 * prints a summary of the results.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		// A type of car (a String)
		String carType = Dialogs.showInputDialog("Enter car type: ");

		// The number of miles driven since the last time the car's tank was filled (an
		// int)
		String milesDriven = Dialogs
				.showInputDialog("Enter the number of miles driven since the last time the car's tank was filled: ");
		int numMiles = Integer.parseInt(milesDriven);

		// The price in dollars of a gallon of gasoline (a double)
		String costOfGas = Dialogs.showInputDialog("Enter the price in dollars of a gallon of gasoline: ");
		double priceOfGas = Double.parseDouble(costOfGas);

		// The number of gallons that would be required to fill the car's tank (a
		// double)
		String gallons = Dialogs
				.showInputDialog("Enter the number of gallons that would be required to fill the car's tank: ");
		double numGallons = Double.parseDouble(gallons);

		// Operations performed on expressions
		double costToFillTankDouble = priceOfGas * numGallons;
		double milesPerGallonDouble = numMiles / numGallons;
		double gasCostPerMileDouble = costToFillTankDouble / numMiles;

		// convert double to string for formatting to two decimal places
		String costToFillTank = String.format("%.2f", costToFillTankDouble);
		String milesPerGallon = String.format("%.2f", milesPerGallonDouble);
		String gasCostPerMile = String.format("%.2f", gasCostPerMileDouble);

		// Display result in a dialog box
		Dialogs.showMessageDialog(carType + '\n' + "Cost to fill tank: $" + costToFillTank + '\n'
				+ "Miles per gallon since last fill-up: " + milesPerGallon + '\n'
				+ "Gas cost per mile since last fill-up: $" + gasCostPerMile);

		// Display result in the console
		System.out.println(carType + '\n' + "Cost to fill tank: $" + costToFillTank + '\n'
				+ "Miles per gallon since last fill-up: " + milesPerGallon + '\n'
				+ "Gas cost per mile since last fill-up: $" + gasCostPerMile);

	}
}
