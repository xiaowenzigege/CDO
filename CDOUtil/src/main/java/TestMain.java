import java.lang.reflect.Field;

public class TestMain {

	public static void main(String[] args){
		try{
			Field[] fields=Arr.class.getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				System.out.println(fields[i].getName());
			}
			
			//type=type.indexOf("[")>0?type.substring(0,type.indexOf("[")):type;
		  }catch(Exception e){	
			 e.printStackTrace();
		 }	
	}
}
