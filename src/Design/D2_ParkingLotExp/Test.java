package Design.D2_ParkingLotExp;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		// 4 levels, 10 spots each level
		// 	5 compact, 5 large among these 10 spots
		// 一共40个车位，20个compact，20个large
		ParkingLot lot = new ParkingLot(4, 10);

		List<Vehicle> list = new ArrayList<>();
		System.out.println("Car Parking");
		for (int i = 0; i < 50; i++) {
			// create and add car/truck alternately
			final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
			list.add(v);
			boolean hasSpot = lot.hasSpot(v);

			System.out.println(i +"th car, find spot: " + hasSpot);
			System.out.println("park state: " + lot.park(v));
			System.out.println("");

		}
		System.out.println("------------------------------------------------");
		System.out.println("Car leaving");
		int i = 0;
		for (Vehicle v : list) {
			System.out.println(i + "th car, leave state: " + lot.leave(v));
			System.out.println("");
			i++;
		}
	}
}
