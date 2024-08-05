package by.it_academy.jd2._107;

import by.it_academy.jd2._107.dto.VoteDTO;
import by.it_academy.jd2._107.service.api.VoteService;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<VoteDTO> vs = VoteService.getInstance().getVoteDB();
        for (Object obj : vs) {System.out.println(obj);}
    }
}