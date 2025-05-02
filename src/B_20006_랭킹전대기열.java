import java.util.*;
import java.io.*;

public class B_20006_랭킹전대기열 {
    static int P, M;
    public static void main (String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Room> rooms = new ArrayList<>();

        for(int i=0; i<P; i++){
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            Player p = new Player(l, name);

            if(rooms.isEmpty()){
                List<Player> players = new ArrayList<>();
                players.add(p);
                rooms.add(new Room(i,p.level-10, p.level+10, players));
            }
            else {
                boolean enter = false;
                for(Room r : rooms){
                    if(p.level <= r.eLevel && p.level >= r.sLevel && r.players.size()<M){
                        r.enterRoom(p);
                        enter = true;
                        break;
                    }
                }
                // 방 생성
                if(!enter){
                    List<Player> players = new ArrayList<>();
                    players.add(p);
                    rooms.add(new Room(i,p.level-10, p.level+10, players));
                }
            }
        }

        for(Room r : rooms){
            if(r.players.size() == M){
                System.out.println("Started!");
            }
            else System.out.println("Waiting!");

            Collections.sort(r.players);

            for(Player p : r.players){
                System.out.println(p.level+" "+p.name);
            }
        }
    }
    static class Room implements Comparable<Room>{
        int id, sLevel, eLevel;
        List<Player> players;

        public Room(int id, int sLevel, int eLevel, List<Player> players){
            this.id = id;
            this.sLevel = sLevel;
            this.eLevel = eLevel;
            this.players = players;
        }
        public void enterRoom(Player p){
            players.add(p);
        }

        @Override
        public int compareTo (Room r){
            return this.id - r.id;
        }
    }
    static class Player implements Comparable<Player>{
        int level;
        String name;

        public Player(int level, String name){
            this.level = level;
            this.name = name;
        }
        @Override
        public int compareTo(Player p){
            return this.name.compareTo(p.name);
        }
    }
}
