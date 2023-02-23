package homework;
public class EnumExample {

	public enum Planet {
		수성(2439), 금성(6052),지구(6371),화성(3390),목성(69911), 토성(58232),	천왕성(25362), 해왕성(24622);
		
		private double d;
		
		private Planet(double data) {
			this.d=data;
		}

		public double getD() {
			return d;
		}

		public void setD(double d) {
			this.d = d;
		}
	
		
	}
	public static void main(String[] args) {
		
//		for(Planet p1: Planet.values() ) {
//			System.out.println(p1);
			
			
			// 면적을 구하는 방식 : 4πr^2
		Planet[] enumArr = Planet.values();
		
			for(int i=0; i<enumArr.length; i++) {
			System.out.println(enumArr[i].name() + ":" + 4*Math.PI*Math.pow(enumArr[i].getD(), 2));
		}
			
			
			
		}
	
		
	}
	
	

