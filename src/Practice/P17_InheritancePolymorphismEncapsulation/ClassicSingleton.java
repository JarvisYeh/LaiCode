package Practice.P17_InheritancePolymorphismEncapsulation;

public class ClassicSingleton {
	// 唯一的instance，属于该类(static)，不能被直接访问(private)，不能修改ref指向(final)
	private static final ClassicSingleton instance = new ClassicSingleton();
	private String name;

	private ClassicSingleton() {
		this.name = "John";
	}

	// 使用一个static method来return该唯一的instance
	public static ClassicSingleton getInstance() {
		return instance;
	}
}
