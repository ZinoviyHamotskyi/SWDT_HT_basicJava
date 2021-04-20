import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;

public class Main {
    public static void main (String args []) {
        System. out. println ("Test of Task number 1");
        System.out.println(getIntegerFromList(Arrays.asList("A", 1, 3)));
        System.out.println(getIntegerFromList(Arrays.asList(1,2,"a","b",0,15)));
        System.out.println(getIntegerFromList(Arrays.asList(1,2,"a","b","aasf","1", "123",231)));

        int[] testArr = {8, 4, 3, 4, 5};
        System. out. println ("Test of Task number 2");
        System.out.println(firstNonRepeatingLetter("sTreSS"));
        System.out.println(firstNonRepeatingLetter("stress"));
        System.out.println(firstNonRepeatingLetter("sTtSS"));

        System. out. println ("Test of Task number 3");
        System.out.println(totalSumOfNumberDigits(16));
        System.out.println(totalSumOfNumberDigits(942));
        System.out.println(totalSumOfNumberDigits(493193));


        System. out. println ("Test of Task number 4 (use for)");
        System.out.println(countOfPairs(testArr,6));
        System. out. println ("Test of Task number 4 (use stream)");
        System.out.println(countOfPairsByStream(testArr,6));


        System. out. println ("Test of Task number 5");
        System.out.println(friendList("Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill"));

        System. out. println ("Test of Extra Task number 1");
        System.out.println(findNextNumber(2017));
        System.out.println(findNextNumber(111));
        System.out.println(findNextNumber(79));
        System.out.println(findNextNumber(531));

        System. out. println ("Test of Extra Task number 2");
        System.out.println(integerToIP(0));
        System.out.println(integerToIP(32));
        System.out.println(integerToIP(2149583361L));

    }

    public static ArrayList<Integer> getIntegerFromList(List<Object> list){
        ArrayList newList = new ArrayList<Integer>();
        for (Object o : list) {
            if (o instanceof Integer) {
                newList.add(o);
            }
        }
        return newList;
    }

    public static Character firstNonRepeatingLetter(String string){
        String input = string.toLowerCase();
        byte[] characterCount = new byte[256];
        for (int i = 0; i < input.length(); i++) {
            characterCount[(int)input.charAt(i)]++ ;
        }
        for (int i = 0; i < input.length(); i++) {
            if(characterCount[(int)input.charAt(i)] == 1 )
                return string.charAt(i);
        }
        return null;
    }

    public static int sumOfNumberDigits(int number){
        if (number < 10) {
            return number;
        }
        return number % 10 + sumOfNumberDigits(number/10);
    }

    public static int totalSumOfNumberDigits(int number){
        int s  = sumOfNumberDigits(number);
        if(s > 9){
            return totalSumOfNumberDigits(s);
        }
        return s;
    }

    public static int countOfPairs(int[] arr, int target){
        int count = 0;
        for (int i = 0; i < arr.length; i++){
            for ( int j = i+1; j < arr.length; j++){
                if (arr[i] + arr[j] == target){
                    count++;
                }
            }
        }
        return count;
    }

    public static int countOfPairsByStream(int[] input, int target){
        AtomicInteger count = new AtomicInteger();
        IntStream.range(0,  input.length)
                .forEach(i -> IntStream.range(0,  input.length)
                        .filter(j -> i != j && input[i] + input[j] == target)
                        .forEach(j -> count.getAndIncrement())
                );
        return count.get()/2; // так как, этот шайтан-поток считает одну пару дважды
    }

    public static String friendList(String list){
        String[] friends = list.split(";");
        for(int i = 0; i < friends.length; i++){
            String[] nameSurname = friends[i].split(":");
            friends[i] = "(" + nameSurname[1] + ", " + nameSurname[0] + ")";
        }
        List<String> orderedList = Arrays.stream(friends).sorted().collect(Collectors.toList());
        String result = "";
        for(String s : orderedList){
            result = result + s;
        }
        return result;
    }



    public static int findNextNumber(int number)
    {
        int temp = number;
        List<Integer> arr = new ArrayList<Integer>();
        while(temp > 0) {
            arr.add(temp%10);
            temp = temp/10;
        }
        int index = 0;
        for (; index < arr.size() - 1; index++){
            if (arr.get(index) > arr.get(index + 1)){
                break;
            }
        }
        if (index == arr.size() - 1) {
            return -1;
        }
        temp = arr.get(index+1);
        int index_min = index;
        for (int j = index - 1; j >= 0; j--){
            if (arr.get(j) > temp && arr.get(j) < arr.get(index_min)){
                index_min = j;
            }
        }
        Collections.swap(arr, index+1, index_min);

        Collections.sort(arr.subList(0, index), Collections.reverseOrder());
        temp = 0;
        for (int i = 0; i < arr.size(); i++){
            temp = temp + (int) (arr.get(i)*Math.pow(10, i));
        }
        return temp;
    }

    public static String integerToIP(long ip) {
        return ((ip >> 24) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                (ip & 0xFF);
    }
}
