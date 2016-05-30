import java.nio.ByteBuffer;


public class BytesTestMain {

	public  static void main(String[] args){
//		int[] a=0;
//		java.lang.Integer a=7000;
//		System.out.println(a.byteValue());
		int len=4;
		java.nio.ByteBuffer buffer=ByteBuffer.allocate(4*len);
		buffer.putFloat(1.0f);
		buffer.putFloat(2.0f);
		buffer.putFloat(5.0f);
		buffer.putFloat(Float.MAX_VALUE);
		byte[] bytes=buffer.array();
//		int mid=len/2;
//		int j=mid+1;
		float[] ints=new float[bytes.length/4];
		int k=0;
		buffer.clear();
		int index=1;
		buffer=ByteBuffer.allocate(4);
		for(int i=0;i<bytes.length;i++){
			buffer.put(bytes[i]);
			index++;
			if(index>4){
				buffer.flip();
				ints[k]=buffer.getFloat();
				buffer.clear();
				index=1;
				k++;
			}

		}
		
		for(int i=0;i<ints.length;i++){
			System.out.println(ints[i]);
		}
		
//		buffer.clear();
//		System.out.println(buffer.put(ab));
	}
}
