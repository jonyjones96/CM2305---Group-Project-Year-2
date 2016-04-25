// import java.util.Date;
import java.util.Random;

public class randomSeed{

	public static void main(String[] args){

		Random rand = new Random();
		int randomNum = rand.nextInt(100) ;
		System.out.println(randomNum);
		String message = generateRandomString(10,0);
		System.out.println(message);

	}


	public static String generateRandomString(int length, int seed) {
      char[] values = {'a','b','c','d','e','f','g','h','i','j',
               'k','l','m','n','o','p','q','r','s','t',
               'u','v','w','x','y','z','0','1','2','3',
               '4','5','6','7','8','9'};

      String out = "";
      Random random = new Random(seed);

      for (int i=0;i<length;i++) {
          int idx=random.nextInt(values.length);
          out += values[idx];
      }
      return out;
    }
}