package Design.D3_DesignPattern;

public class FactoryDemo {
	private final ShapeFactory factory = new ShapeFactory();

	public void demo(String[] args) {
		Shape shape1 = factory.getShape("CIRCLE");
		shape1.draw(10);

		Shape shape2 = factory.getShape("RECTANGLE");
		shape2.draw(10);

		Shape shape3 = factory.getShape("SQUARE");
		shape3.draw(10);
	}
}
