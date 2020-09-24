package Design.D2_ParkingLotExp;

// parking sport no need to be public
// hasSpot(), park(), leave() APIs' input/output does not associate to Parking Lot
// packet level private
class ParkingSpot {
	private final VehicleSize size;
	// null if no vehicle parked in this spot
	private Vehicle currentVehicle;

	ParkingSpot(VehicleSize size) {
		this.size = size;
	}

	boolean fit(Vehicle v) {
		if (currentVehicle == null) {
			// use enum ordinal() to obtain the enum value index in enum definition file
			// 需要慎用，因为enum中的顺序可能会乱
			// 所以这里在enum中对每个值加入对应的数值，通过getSizeValue()返回enum对应的数值
			return size.getSizeValue() >= v.getSize().getSizeValue();
		}
		return false;
	}

	/**
	 * record a vehicle is parked in by updating the currentVehicle field
	 * @param v
	 */
	void park(Vehicle v) {
		currentVehicle = v;
	}

	void leave() {
		currentVehicle = null;
	}

	Vehicle getVehicle() {
		return currentVehicle;
	}

}
