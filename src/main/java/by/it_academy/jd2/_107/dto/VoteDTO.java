package by.it_academy.jd2._107.dto;

import by.it_academy.jd2._107.controllers.VotingFormServlet;

import java.util.Arrays;
import java.util.Objects;

public class VoteDTO {

    private String candidate ;
    private String[] genres;
    private String comment;

    public VoteDTO() {
    }

    public VoteDTO(String candidate, String[] genres, String comment) {
        this.candidate = candidate;
        this.genres = genres;
        this.comment = comment;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteDTO voteDTO = (VoteDTO) o;
        return Objects.equals(candidate, voteDTO.candidate) && Objects.deepEquals(genres, voteDTO.genres) && Objects.equals(comment, voteDTO.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidate, Arrays.hashCode(genres), comment);
    }

    @Override
    public String toString() {
        return "VoteDTO{" +
                "candidate='" + candidate + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", comment='" + comment + '\'' +
                '}';
    }
}
