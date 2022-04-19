/*
 
 ����) �¾�� �༺�� ��Ÿ���� enum Planet�� �̿��Ͽ� ���Ͻÿ�.
(��, enum ��ü ������ �������� �̿��ϵ��� �����Ͻÿ�.) 

��) �༺�� ������(KM):
����(2439), 
�ݼ�(6052), 
����(6371), 
��(3390), 
��(69911), 
�伺(58232), 
õ�ռ�(25362), 
�ؿռ�(24622)
 
 
 */


public class PlanetExample {
	// ������ �����ϴ� ���
	//   =>  enum �������̸� {�����1, �����2, ..., �����n};
	public enum Planet {
		Mercury(2439), Venus(6052), Earth(6371), Mars(3390), Jupiter(69911), 
		Saturn(58232), Uranus(25362), Neptune(24622);
		
		// ��ȣ���� ���� ����� ����
		private double r;
		
		// enum Planet�� ������ �����
		Planet(double r) {
			this.r = r;
		}
		
		// ���� ��ȯ�ϴ� get�޼���
		public double getkm() {
			return (r*r*Math.PI);
		}
	}
	
	public static void main(String[] args) {
		/*
		   ���������� ���Ǵ� �޼���
		   
		 1. name() => ������ ����� �̸��� ���ڿ��� ��ȯ�Ѵ�.
		 2. ordinal() => ������ ����� ���ǵ� �������� ��ȯ�Ѵ�.(�⺻������ 0���� ����)
		 3. valueOf("����������̸�"); => ������ ���������� '����������̸�'�� ��ġ�ϴ�
		                            ������ ����� ��ȯ�Ѵ�.
		*/
		// �������̸�.values() => �����͸� �迭�� �����´�.
		Planet[] planetArr = Planet.values();
		for(int i=0; i<planetArr.length;i++) {
			System.out.println(planetArr[i]+"�� ���̴� "+planetArr[i].getkm()+"��");
		}
		
		
		
		
	}

}