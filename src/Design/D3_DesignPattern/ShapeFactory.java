package Design.D3_DesignPattern;

public class ShapeFactory {
	// use getShape method to get object of type shape
	// Shape creation logic group up to a Factory class
	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();
		}
		if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		}
		return null;
	}
}


// different Shape class
interface Shape {
	void draw(int size);
}

class Rectangle implements Shape {

	@Override
	public void draw(int size) {
		System.out.println("Inside Rectangle:: draw()");
	}
}

class Circle implements Shape {

	@Override
	public void draw(int size) {
		System.out.println("Inside Circle:: draw()");
	}
}

class Square implements Shape {

	@Override
	public void draw(int size) {
		System.out.println("Inside Square:: draw()");
	}
}