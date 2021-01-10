import java.util.*;

public class E1Q2 {
    /**
     * Etant donné une liste de relations entre des gares (le train démarre de la gare `from` au temps `startTime`
     * et arrive à la gare `to` au temps `endTime`) et les positions des gares, ainsi qu'une gare et un temps de départ,
     * quelle est la gare la plus éloignée que vous pouvez atteindre avant (<=) le temps maxTime?
     *
     * Il n'y a pas à vous inquiéter de plusieurs points:
     * - les voyageurs peuvent repartir au moment exact où ils arrivent en gare;
     * - toutes les liaisons sont directes;
     * - les horaires ne sont pas périodiques, pas besoin de les répeter chaque jour.
     * - dans tout les cas, startTime < endTime et from != to dans les relations.
     * - il n'y a jamais de doublons (i.e. deux raltions strictement égales)
     *
     * Utilisez la fonction `distance` pour calculer la distance entre deux gares.
     *
     * La question est évaluée sur 20:
     * - 13 points si l'algorithme fonctionne
     * - 4 points si l'algorithme fonctionne rapidement (complexité raisonnable)
     * - 3 points supplémentaire si l'algorithme fonctionne très rapidement (complexité optimale)
     *
     * Nous laissons les termes complexité raisonnable/optimale volontairement flou. A vous, sur la base de vos
     * connaissance, de trouver parmi la famille d'algorithme approprié lequel est optimal.
     *
     * Un indice: vous l'aurez deviné, il s'agit clairement d'un problème de graphe. Mais pas n'importe quelle graphe:
     * les noeuds sont particuliers, car ils ne représentent pas un emplacement dans l'espace, mais un emplacement
     * dans l'espace temps (par exemple (Bruxelles-midi, 08h48)).
     *
     * N'oubliez pas que si j'arrive à Bxl-midi au temps i, je peux prendre n'importe quel train partant de Bxl-midi
     * partant au temps j >= i.
     *
     * By the way, saviez-vous que la fonction TreeMap.subMap (https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html#subMap-K-boolean-K-boolean-)
     * existe?
     *
     * @param relations une liste de relations, liant, une gare+un temps (la clé) (par exemple, Bxl-midi, 12h43)
     *                  à une liste de trains partant à ce moment là, représenté par une liste d'objet StationTime
     *                  indiquant à quelle endroit/heure arrivent ces trains.
     *
     *                  Les gares sont représentées par des String ("Bxl-midi") et le temps par des entiers positifs.
     * @param stationPositions la position des gares
     * @param startPoint l'endroit/temps d'où l'on démarre
     * @param maxTime le dernier moment où l'on peut arriver à la gare de destination
     * @return la gare la plus lointaine atteignable en partant de startPoint avant le temps maxTime.
     */
    public static String farthestPointReachable_dru(HashMap<StationTime, LinkedList<StationTime>> relations, Map<String, Position> stationPositions,
                                                StationTime startPoint, int maxTime) {
        TreeSet<StationTime> pq_treeset = new TreeSet<>();
        TreeMap<String,Integer> temps_station = new TreeMap<>();

        temps_station.put(startPoint.pos,startPoint.time);
        pq_treeset.add(startPoint);

        StationTime current;
        while (!pq_treeset.isEmpty()){
            current = pq_treeset.first();
            //current.print();
            if(temps_station.containsKey(current.pos)){
                if (temps_station.get(current.pos)>current.time){ //ecouter farah si ca marche pas
                    temps_station.put(startPoint.pos,current.time);
                }
            }else{
                temps_station.put(startPoint.pos,current.time);
            }
            if(relations.get(current)!=null)
                pq_treeset.addAll(relations.get(current));
        }

        System.out.println(temps_station.toString());
        return null;
    }

    public static String farthestPointReachable(HashMap<StationTime, LinkedList<StationTime>> relation, Map<String, Position> stationPositions,
                                                StationTime startPoint, int maxTime) {
        TreeMap<StationTime, LinkedList<StationTime>> relation_treeset = new TreeMap<>(relation);

        SortedMap<StationTime, LinkedList<StationTime>> reduit_relation = relation_treeset.subMap(startPoint,relation_treeset.ceilingKey(new StationTime(startPoint.pos,maxTime+1)));
        TreeMap<StationTime,Integer> pq = new TreeMap<>(StationTime::compareTo);
        Map<String,Integer> tempsArv = new HashMap<>();

        tempsArv.put(startPoint.pos,startPoint.time);
        pq.put(startPoint,startPoint.time);

        while (pq.size()!=0){
            StationTime v = pq.firstKey();
            pq.remove(v);
            for (StationTime key : reduit_relation.keySet()){
                if (key.pos.equals(v.pos) && key.time >= v.time) {
                    for(StationTime e : reduit_relation.get(key)){
                        if(tempsArv.containsKey(e.pos)){
                            if(tempsArv.get(e.pos)>e.time){
                                tempsArv.replace(e.pos,e.time);
                            }

                        } else{
                            tempsArv.put(e.pos,e.time);
                        }
                        pq.put(e,e.time);
                    }
                }
            }
        }

        int dist_max = 0;
        String arrivee = startPoint.pos;
        for (String key: tempsArv.keySet()) {
            Position start = stationPositions.get(startPoint.pos);
            Position key_pos = stationPositions.get(key);
            if(Position.distance(start,key_pos)>dist_max && tempsArv.get(key)<=maxTime){
                dist_max = Position.distance(start,key_pos);
                arrivee = key;
            }
        }
        return arrivee;
    }
}

