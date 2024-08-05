package by.it_academy.jd2._107.service.api;

import by.it_academy.jd2._107.dto.VoteDTO;

import java.time.LocalDateTime;
import java.util.*;

public class VoteService implements IVoteService{

    private static VoteService instance = null;
    private final Set<String> candidates = new HashSet<>(Arrays.asList("Сергей","Антон","Игорь","Олег"));
    private Map<String, Integer> existingCandidate = new HashMap<>();
    private Map<String, Integer> existingGenre = new HashMap<>();
    private Map<String, Integer> existingComment = new HashMap<>();
    private static List<VoteDTO> voteDB = new ArrayList<>();


    private VoteService() {
    }

    public static synchronized VoteService getInstance(){
        if(instance == null) {
            instance = new VoteService();
        }
        return instance;
    }

    @Override
    public boolean create(VoteDTO vote) {
        if(!candidates.contains(vote.getCandidate()) || vote.getComment().isEmpty() ||
                vote.getComment().length() >= 100 || vote.getGenres().length < 3 ||
                vote.getGenres().length > 5){
            System.out.println("/*<fieldset><p><span style= 'color: red;'>Форма непринята</span></p></fieldset>");
            return false;
        }
        voteDB.add(vote);
        System.out.println("<fieldset><p><span style= 'color: green;'>Форма принята</span></p></fieldset>");
        return true;
    }

    public Map<String, Integer> sortedCandidates(VoteDTO vote){
        String candidate = vote.getCandidate();
        existingCandidate.compute(candidate, (k, v) -> {
            if(v == null) {
                return 1;
            }
            return v + 1;
        });
        return existingCandidate;
    }


    public Map<String, Integer> resultGenres(VoteDTO vote){
        for (String genre : vote.getGenres()) {
            existingGenre.compute(genre, (k, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
        }
        /*existGenre.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(stringIntegerEntry -> {
                    System.out.println("<fieldset><legend><h2>Лутший жанр</h2></legend><body><p>" + stringIntegerEntry.getKey() + " - " + stringIntegerEntry.getValue() + "</p></body></fieldset>");
                });*/
        return existingGenre;
    }

    public Map<String, Integer> getComment(VoteDTO vote){
        String comment = vote.getComment() + " Время : " + LocalDateTime.now();
        existingComment.compute(comment, (k, v) -> {
                    return v = existingComment.size() + 1;
            });
        return existingComment;
    }

    public List<VoteDTO> getVoteDB() {
        return voteDB;
    }
}
