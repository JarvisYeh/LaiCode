package Design.D2_ParkingLotExp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level {
	private final List<ParkingSpot> spots;

	Level(int numOfSpots) {
		List<ParkingSpot> list = new ArrayList<>(numOfSpots);

		// add parking spot
		// half compact, half large
		int i = 0;
		for (; i < numOfSpots/2; i++) {
			list.add(new ParkingSpot(VehicleSize.Compact));
		}

		for (; i < numOfSpots; i++) {
			list.add(new ParkingSpot(VehicleSize.Large));
		}

		// 创建新的不可修改的list
		// 使用add(), set(), delete()之类的method会throw exception
		spots = Collections.unmodifiableList(list);
	}

	boolean hasSpot(Vehicle v) {
		for (ParkingSpot s : spots) {
			if (s.fit(v)) {
				return true;
			}
		}
		return false;
	}

	boolean park(Vehicle v) {
		for (ParkingSpot s : spots) {
			if (s.fit(v)) {
				s.park(v);
				return true;
			}
		}
		return false;
	}

	boolean leave(Vehicle v) {
		for (ParkingSpot s : spots) {
			if (s.getVehicle() == v) {
				s.leave();
				return true;
			}
		}
		return false;
	}




}
