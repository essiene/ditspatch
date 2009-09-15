package dit.url.util;

public class Util
{
    public static String trim(String s, char c)
    {
        /* A very small DFA that implements 
         *
         * - state_start ---- SLASH --->  (shift index) state_slashes
         * - state_start ---- NOT_SLASH ---> state_end.
         * - state_slashes --- SLASH ---> (shift index) state_slashes.
         * - state_slashes --- NOT_SLASH ---> state_end.
         *
         * We use the same DFA procedure to find the startPos, and then
         * traverse the string from behind to find endPos, and then apply
         * a String.substring(startPos, endPos);
         **/

        int i;

        int startPos = 0;
        int endPos = s.length() - 1;

        for(i = startPos ; i < s.length(); i++) {
            if(s.charAt(i) == c) {
                startPos = i + 1;
            } else {
                break;
            }
        }

        for(i = endPos; i >= 0; i--) {
            if(s.charAt(i) == c) {
                endPos = i - 1;
            } else {
                break;
            }
        }

        return s.substring(startPos, endPos+1);
    }
}
