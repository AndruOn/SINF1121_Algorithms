import java.util.HashMap;
import java.util.Map;

public class RabinKarp
{
    //TODO by student: many things are not correct here
    private HashMap<Long,String> patHashDict = new HashMap<>(); //MODIFIED
    private int M; // pattern length
    private long Q; // a large prime
    private int R = 2048; // alphabet size
    private long RM; // R^(M-1) % Q


    public RabinKarp(String[] pat)
    {
        //TODO by student: it's obviously not correct

        this.M = pat[0].length();
        Q = 4463;

        RM = 1;
        for (int i = 1; i <= M-1; i++) // Compute R^(M-1) % Q for use
            RM = (R * RM) % Q; // in removing leading digit.

        for (String s : pat) { //MODIFIED
            patHashDict.put(hash(s, M),s);
        }

        System.out.println("M:" + M+"   RM: "+RM);
        for (Map.Entry<Long,String> entry : patHashDict.entrySet()) {
            System.out.println("hash:"+entry.getKey()+"  pattern:"+entry.getValue());
        }
    }

    //MODIFIED removed the funct check


    private long hash(String key, int M)
    { // Compute hash for key[0..M-1].
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }


    public int search(String txt)
    { // Search for hash match in text.
        //TODO by student: it's obviously not correct
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHashDict.containsKey(txtHash)) {
            if(patHashDict.get(txtHash).equals(txt)) return 0;
        } //MODIFIED
        System.out.println("txthash:"+txtHash+" substring:"+txt.substring(0,M));
        for (int i = M; i < N; i++)
        { // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash*R + txt.charAt(i)) % Q;
            String val = patHashDict.get(txtHash); //MODIFIED
            System.out.printf("txthash: %d substring:%s\n",txtHash,txt.substring(i-M+1,i+1));
            if (patHashDict.containsKey(txtHash)) {  //MODIFIED
                if (patHashDict.get(txtHash).equals(txt.substring(i - M + 1, i + 1))) {  //MODIFIED
                    return i - M + 1; // match
                }
            }
        }
        return N; // no match found
    }

    public static void main(String[] args) {
        //Exemple: Si txt = “find Here find interresting exercise for Rabin Karp” et pat={“have”, “find”, “Karp”}
        // la fonction search doit renvoyer 5 car le mot "find" présent dans le texte et
        // dans la liste commence à l'indice 5.

        RabinKarp rk = new RabinKarp(new String[]{"have", "find", "Karp"});
        System.out.println(rk.search("Here find interresting exercise for Rabin Karp"));
    }
}