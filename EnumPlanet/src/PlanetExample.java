/*
 
 문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 

예) 행성의 반지름(KM):
수성(2439), 
금성(6052), 
지구(6371), 
성(3390), 
목성(69911), 
토성(58232), 
천왕성(25362), 
해왕성(24622)
 
 
 */


public class PlanetExample {
	// 열거형 선언하는 방법
	//   =>  enum 열거형이름 {상수값1, 상수값2, ..., 상수값n};
	public enum Planet {
		Mercury(2439), Venus(6052), Earth(6371), Mars(3390), Jupiter(69911), 
		Saturn(58232), Uranus(25362), Neptune(24622);
		
		// 괄호속의 값이 저장될 변수
		private double r;
		
		// enum Planet의 생성자 만들기
		Planet(double r) {
			this.r = r;
		}
		
		// 값을 반환하는 get메서드
		public double getkm() {
			return (r*r*Math.PI);
		}
	}
	
	public static void main(String[] args) {
		/*
		   열거형에서 사용되는 메서드
		   
		 1. name() => 열거형 상수의 이름을 문자열로 반환한다.
		 2. ordinal() => 열거형 상수가 정의된 순서값을 반환한다.(기본적으로 0부터 시작)
		 3. valueOf("열거형상수이름"); => 지정된 열거형에서 '열거형상수이름'과 일치하는
		                            열거형 상수를 반환한다.
		*/
		// 열거형이름.values() => 데이터를 배열로 가져온다.
		Planet[] planetArr = Planet.values();
		for(int i=0; i<planetArr.length;i++) {
			System.out.println(planetArr[i]+"의 넓이는 "+planetArr[i].getkm()+"㎢");
		}
		
		
		
		
	}

}