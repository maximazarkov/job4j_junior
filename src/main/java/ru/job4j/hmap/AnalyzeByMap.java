package ru.job4j.hmap;

import java.util.*;

public class AnalyzeByMap {
    /**
     * Метод averageScore() - вычисляет общий средний балл.
     * @param pupils - Список учеников.
     * @return - средний балл.
     */
    public static double averageScore(List<Pupil> pupils) {
        int sumScore = 0;
        int countAllSubjectScore = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sumScore += subject.score();
                countAllSubjectScore++;
            }
        }
        return countAllSubjectScore == 0 ? 0D : (double) sumScore / countAllSubjectScore;
    }

    /**
     * Метод averageScoreByPupil() - вычисляет средний балл по каждому ученику.
     * То есть берем одного ученика и считаем все его баллы за все предметы и делим на количество предметов.
     * Возвращает список из объекта Label (имя ученика и средний балл).
     * @param pupils - список учеников.
     * @return - средний балл по каждому ученику.
     */
    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> averageScoreByPupil = new LinkedList<>();
        for (Pupil pupil : pupils) {
            int sumScore = 0;
            int countSubjectsByPupil = 0;
            for (Subject subject : pupil.subjects()) {
                sumScore += subject.score();
                countSubjectsByPupil++;
            }
            averageScoreByPupil.add(new Label(pupil.name(), (double) sumScore / countSubjectsByPupil));
        }
        return averageScoreByPupil;
    }

    /**
     * Метод averageScoreBySubject() - вычисляет средний балл по каждому предмету.
     * Например, собираем все баллы учеников по предмету география и делим на количество учеников.
     * Возвращает список из объектов Label (название предмета и средний балл).
     * @param pupils - список учеников
     * @return - средний балл по каждому предмету
     */
    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> averageScoreBySubject = new LinkedList<>();
        Map<String, Integer> amountBySubject = new LinkedHashMap<>();
        int countPeoples = 0;
        for (Pupil pupil : pupils) {
            countPeoples++;
            List<Subject> subjects = pupil.subjects();
            for (Subject s : subjects) {
                    amountBySubject.put(s.name(), amountBySubject.getOrDefault(s.name(), 0) + s.score());
            }
        }
        int finalCountPeoples = countPeoples;
        amountBySubject.forEach((k, v) -> {
            averageScoreBySubject.add(new Label(k, (double) v / finalCountPeoples));
        });
        return averageScoreBySubject;
    }

    /**
     * Метод bestStudent() - возвращает лучшего ученика. Лучшим считается ученик с наибольшим
     * суммарным баллом по всем предметам. Возвращает объект Label (имя ученика и суммарный балл).
     * @param pupils - список учеников.
     * @return - лучший ученик.
     */
    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> amountScoreBySubject = new LinkedList<>();
        Map<String, Integer> amountBySubject = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            List<Subject> subjects = pupil.subjects();
            for (Subject s : subjects) {
                amountBySubject.put(pupil.name(), amountBySubject.getOrDefault(pupil.name(), 0) + s.score());
            }
        }
        amountBySubject.forEach((k, v) -> {
            amountScoreBySubject.add(new Label(k, (double) v));
        });
        Comparator<Label> lessSubjects = Comparator.naturalOrder();
        amountScoreBySubject.sort(lessSubjects);
        return amountScoreBySubject.get(amountScoreBySubject.size() - 1);
    }

    /**
     * Метод bestSubject() - возвращает предмет с наибольшим баллом для всех студентов. Возвращает
     * объект Label (имя предмета, сумма баллов каждого ученика по этому предмету).
     * @param pupils - список учеников.
     * @return - предмет с наибольшим числом баллов.
     */
    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> amountScoreBySubject = new LinkedList<>();
        Map<String, Integer> amountBySubject = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            List<Subject> subjects = pupil.subjects();
            for (Subject s : subjects) {
                amountBySubject.put(s.name(), amountBySubject.getOrDefault(s.name(), 0) + s.score());
            }
        }
        amountBySubject.forEach((k, v) -> {
            amountScoreBySubject.add(new Label(k, (double) v));
        });
        Comparator<Label> lessSubjects = Comparator.naturalOrder();
        amountScoreBySubject.sort(lessSubjects);
        return amountScoreBySubject.get(amountScoreBySubject.size() - 1);

    }
}
