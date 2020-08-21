package ru.job4j.candidate.persistence;

import ru.job4j.candidate.model.Candidate;

public class DaoRun {
    public static void main(String[] args) {
        DAO dao = DAO.instOf();
        Candidate candidateA = new Candidate("A", 1, 10);
        Candidate candidateB = new Candidate("B", 2, 20);
        Candidate candidateC = new Candidate("C", 3, 30);
        Candidate rslA = dao.create(candidateA);
        Candidate rslB = dao.create(candidateB);
        Candidate rslC = dao.create(candidateC);
        System.out.println(dao.allCandidates());
        System.out.println(dao.findById(rslA.getId()));
        System.out.println(dao.findById(rslB.getId()));
        System.out.println(dao.findById(rslC.getId()));
        System.out.println(dao.findByName(candidateA.getName()));
        System.out.println(dao.findByName(candidateB.getName()));
        System.out.println(dao.findByName(candidateC.getName()));
        rslA.setName("AA");
        rslB.setName("BB");
        rslC.setName("CC");
        dao.update(candidateA, rslA.getId());
        dao.update(candidateB, rslB.getId());
        dao.update(candidateC, rslC.getId());
        dao.delete(rslA.getId());
        dao.delete(rslB.getId());
        dao.delete(rslC.getId());
    }
}
