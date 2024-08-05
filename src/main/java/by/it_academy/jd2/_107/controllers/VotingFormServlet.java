package by.it_academy.jd2._107.controllers;

import by.it_academy.jd2._107.dto.VoteDTO;
import by.it_academy.jd2._107.service.api.IVoteService;
import by.it_academy.jd2._107.service.api.VoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class VotingFormServlet extends HttpServlet {

    public static final String SET_CHARTER_ENCODING = "UTF-8";
    public static final String SET_CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final IVoteService voteService = VoteService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(SET_CHARTER_ENCODING);
        resp.setContentType(SET_CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();

        VoteDTO voteDTO = new VoteDTO();

        voteDTO.setCandidate(req.getParameter("name"));
        voteDTO.setGenres(req.getParameterValues("genre"));
        voteDTO.setComment(req.getParameter("comment"));

        if (voteService.create(new VoteDTO(voteDTO.getCandidate(), voteDTO.getGenres(), voteDTO.getComment())) == true) {

            Map<String, Integer> candidate = VoteService.getInstance().sortedCandidates(voteDTO);
            Map<String, Integer> genres = VoteService.getInstance().resultGenres(voteDTO);
            Map<String, Integer> comment = VoteService.getInstance().getComment(voteDTO);

            writer.print("<fieldset><p><span style= 'color: green;'>Форма принята</span></p></fieldset>");

            writer.print("<h2>Лутший кандидат</h2>");
            candidate.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(stringIntegerEntry -> {
                        writer.write("<p>" + stringIntegerEntry.getKey() + " - " + stringIntegerEntry.getValue() + "</p>");
                    });

            writer.print("<h2>Лутший жанр</h2>");
            genres.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(stringIntegerEntry -> {
                        writer.write("<p>" + stringIntegerEntry.getKey() + " - " + stringIntegerEntry.getValue() + "</p>");
                    });

            writer.print("<h2>Комментарии пользователей</h2>");
            comment.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(stringIntegerEntry -> {
                        writer.write("<p>Комментарий № " + stringIntegerEntry.getValue() + " - " + stringIntegerEntry.getKey() + "</p>");
                    });
        } else {
            writer.print("<fieldset><p><span style= 'color: red;'>Форма непринята</span></p></fieldset>");
        }
    }
}


