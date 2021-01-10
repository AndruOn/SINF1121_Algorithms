import java.util.*;

public class E1Q1 {
    /**
     * Le gouvernement belge a imposé à la population de limiter les contact, via des "bulles".
     *
     * Le principe est assez simple. Si vous avez un contact avec quelqu'un, vous
     * êtes dès lors dans sa bulle, et il est dans la votre.
     *
     * Prenons un exemple:
     * - Lundi, Alice voit Bob et Carole
     * - Mardi, Bob voit Eve et Frank
     * - Mercredi, Eve voit Alice et Frank
     * - Jeudi, Frank et Bob mangent un bout ensemble
     *
     * Les bulles résultantes sont
     * - Bulle d'Alice = [Bob, Carole, Eve, Frank]
     * - Bulle de Bob = [Alice, Carole, Eve, Frank]
     * - Bulle de Carole = [Alice, Bob]
     * - Bulle de Eve = [Alice, Bob, Frank]
     * - ...
     *
     * Notez bien que la relation est symétrique
     * (si Alice est dans la bulle de Bob, alors Bob est dans la bulle d'Alice)
     * mais pas transitive (si Bob est dans la bulle d'Alice,
     * et que Carole est dans la bulle de Bob, Carole n'est pas forcément dans celle d'Alice)
     *
     * Etant donné qu'au plus n personnes peuvent être dans la bulle de quelqu'un sans
     * qu'elle ne soit hors-la-loi, et étant donné une liste de contacts,
     * donnez la liste des mauvais citoyens qui sont hors-la-loi.
     *
     * Notez que n peut être très, très grand, pour anticiper d'éventuelles futures mesures du
     * gouvernement.
     *
     * La question est sur 20 points:
     * - 10 points pour la validité (votre programme retourne toujours la bonne réponse)
     * - 10 points pour la performance (votre programme fonctionne dans le temps optimal)
     *
     * La performance a atteindre pour cet algorithme est O(n*m^2),
     * où n=contacts.length et m=max_i contacts.get(i).length.
     *
     * @param contacts une liste de contacts pris.
     *                 Dans l'exemple ci-dessus, elle contenait 4 listes, contenant à chaque fois
     *                 les noms des participants à chaque contact.
     * @param n un entier > 0.
     * @return une liste de personnes ayant strictement plus que n personnes dans leur bulle
     */
    public static List<String> badCitizens(List<List<String>> contacts, int n) {
        HashMap<String,Set<String>> st = new HashMap<>();
        for (List<String> l :contacts) {
            for (String nom: l) {
                if(!st.containsKey(nom)){
                    st.put(nom,new HashSet<>());
                }
                for (String nom_second: l) {
                    if(!nom.equals(nom_second)){
                        st.get(nom).add(nom_second);
                    }
                }
            }
        }
        List<String> ret = new ArrayList<>();
        for (String nom: st.keySet()) {
            if(st.get(nom).size()>n){
                ret.add(nom);
            }
        }
        return ret;
    }
}
