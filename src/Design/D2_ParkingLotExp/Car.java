package Design.D2_ParkingLotExp;

public class Car extends Vehicle{
	@Override
	public VehicleSize getSize() {
		return VehicleSize.Compact;
	}
}
