package Design.D2_ParkingLotExp;

public class ParkingLot {
	private final Level[] levels;

	public ParkingLot(int numLevels, int numSpotsPerLevel) {
		// array of level
		levels = new Level[numLevels];

		// 设置每层的车位
		for (int i = 0; i < numLevels; i++) {
			levels[i] = new Level(numSpotsPerLevel);
		}
	}

	// check each level, at least one level has spot available
	// return true
	public boolean hasSpot(Vehicle v) {
		for (Level l : levels) {
			if (l.hasSpot(v)) {
				return true;
			}
		}
		return false;
	}

	public boolean park(Vehicle v) {
		for (Level l: levels) {
			if (l.park(v)) {
				return true;
			}
		}
		return false;
	}

	public boolean leave(Vehicle v) {
		for (Level l: levels) {
			if (l.leave(v)) {
				return true;
			}
		}
		return false;
	}
}
