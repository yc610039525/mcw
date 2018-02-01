package cn.cd.caoyeung.service.pattern.simplefactory;

public class SimpleFactoryTest {

	public static void main(String[] args) {
		IComponent component = ComponentFactory.createComponent(1);
		System.out.println(component.getName());
		System.out.println(String.valueOf(123456));
		System.out.println(new StringBuffer(String.valueOf(123456)).reverse());
		System.out.println(reverse(123456789));
	}
	static int reverse(int i){
		int m,n=0;
		m = i;
		while(m != 0){
			n = n*10;
			n = n+m%10;
			m = m/10;
			System.out.println(n);
			}
		return n;
	}
}
