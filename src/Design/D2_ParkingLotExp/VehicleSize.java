package Design.D2_ParkingLotExp;

public enum VehicleSize {
	Compact(1), Large(2);

	private final int size;

	VehicleSize(int size) {
		this.size = size;
	}

	// Compact.getSize() returns 1
	// Large.getSize() returns 2
	public int getSizeValue() {
		return size;
	}
}
