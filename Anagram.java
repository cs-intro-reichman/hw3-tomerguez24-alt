/** Functions for checking if a given string is an anagram. */
public class Anagram {
    public static void main(String[] args) {
        // Tests the isAnagram function.
        System.out.println(isAnagram("silent","listen"));  // true
        System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie","Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

        // Tests the preProcess function.
        System.out.println(preProcess("What? No way!!!")); // should print "whatnoway"
        
        // Tests the randomAnagram function.
        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
        
        // Performs a stress test of randomAnagram 
        String str = "1234567";
        boolean pass = true;
        // 10 can be changed to much larger values, like 1000
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test failed");
    }  

    // Returns true if the two given strings are anagrams, false otherwise.
    public static boolean isAnagram(String str1, String str2) {
        String s1 = preProcess(str1);
        String s2 = preProcess(str2);

        if (s1.length() != s2.length()) {
            return false;
        }

        int[] counts = new int[26];

        // Count letters in s1
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            counts[c - 'a']++;
        }

        // Subtract letters using s2
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            counts[c - 'a']--;
        }

        // If all counts are zero – anagrams
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }

        return true;
    }

    // Returns a preprocessed version of the given string:
    // all the letter characters are converted to lower-case,
    // all non-letters (including spaces) are deleted.
    // For example: "What? No way!" -> "whatnoway"
    public static String preProcess(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        return sb.toString();
    } 

    // Returns a random anagram of the given string.
    public static String randomAnagram(String str) {
        char[] arr = str.toCharArray();

        for (int i = arr.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return new String(arr);
    }
}