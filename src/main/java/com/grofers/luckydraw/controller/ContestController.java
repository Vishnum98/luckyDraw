package com.grofers.luckydraw.controller;

import com.grofers.luckydraw.common.Util;
import com.grofers.luckydraw.model.*;
import com.grofers.luckydraw.repo.*;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.grofers.luckydraw.common.Util.randomString;

@RestController
@RequestMapping("/api")
public class ContestController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    WinnerRepo winnerRepo;
    @Autowired
    RaffleRepo raffleRepo;
    @Autowired
    ContestRepo contestRepo;
    @Autowired
    GiftRepo giftRepo;

    @PostMapping("/createContest")
    @Transactional
    public ResponseEntity<Contest> createContest(@RequestParam String date, @RequestParam List<Integer> gifts) throws ParseException {
        List<Gift> giftsFromDb = giftRepo.findAllById(gifts);
        Contest contest = new Contest(getDate(date), giftsFromDb);
        contest = contestRepo.save(contest);
        return new ResponseEntity<>(contest, HttpStatus.OK);
    }

    @PostMapping("/createGift")
    public ResponseEntity<Object> createGift(@RequestParam String gift) {
        Gift newGift = new Gift(gift);
        newGift = giftRepo.save(newGift);
        return new ResponseEntity<>(newGift, HttpStatus.OK);
    }

    @PostMapping("/createTicket")
    @Transactional
    public ResponseEntity<Raffle> createRaffleTicket(@RequestParam Integer uid, @RequestParam Integer cid) {

        if (userRepo.getContest(uid, cid) > 0)
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        User user = userRepo.findById(uid).get();
        Contest contest = contestRepo.findById(cid).get();

        Raffle raffle = new Raffle(getRandomKey(), contest);
        raffle = raffleRepo.save(raffle);
        user.addRaffle(raffle);
        userRepo.save(user);
        return new ResponseEntity<>(raffle, HttpStatus.OK);

    }

    private String getRandomKey() {
        String generatedString = randomString();
        while (raffleRepo.countByRaffle(generatedString) > 0) {
            generatedString = randomString();
        }
        return generatedString;
    }

    @GetMapping("/getContest")
    public ResponseEntity<Contest> getContest(@RequestParam Integer cid) {
        Contest contest = contestRepo.findById(cid).get();
        return new ResponseEntity<>(contest, HttpStatus.OK);
    }


    @GetMapping("/computeWinner")
    public ResponseEntity<List<Winner>> computeWinner(@RequestParam Integer cid) {
        Contest contest = contestRepo.findById(cid).get();
        List<Winner> winners = contest.getWinners();
        if (winners == null || winners.isEmpty()) {
            List<Raffle> raffles = raffleRepo.getByContest(cid);
            Random rand = new Random();
            Raffle raffle = raffles.get(rand.nextInt(raffles.size()));
            User user = userRepo.findByRaffleId(raffle.getId());
            Winner winner = new Winner(user, 1, contest.getGifts().get(0));
            winners.add(winnerRepo.save(winner));
            contest.setWinners(winners);
            contestRepo.save(contest);
        }

        return new ResponseEntity<>(winners, HttpStatus.OK);
    }

    @GetMapping("/pastWeekWinners")
    public ResponseEntity<List<Winner>> getWinnersForPastWeek() throws ParseException {
        LocalDateTime date = LocalDateTime.now();
        List<Contest> contestList = contestRepo.findByDateBetween(date.minusDays(7), date);
        List<Winner> winners = new ArrayList<>();
        contestList.stream()
                .filter(a -> a.getWinners() != null)
                .forEach(a->winners.addAll(a.getWinners()));
        return new ResponseEntity<>(winners, HttpStatus.OK);
    }

    @GetMapping("/nextContest")
    public ResponseEntity<Object> getNextLuckyDraw() {
        LocalDateTime date = LocalDateTime.now();
        var contest = contestRepo.findFirstByDateAfterOrderByDateAsc(date);
        if (contest == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(contest, HttpStatus.OK);

    }

    private LocalDateTime getDate(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }

}

