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
        List<Label> averageScoreBySubject = new ArrayList<>();
        Queue<Subject> allSubjects = new LinkedList<>(pupils
                .stream()
                .flatMap((Pupil pupil) -> pupil.subjects().stream())
                .toList());
        List<String> nameSubjects = new LinkedList<>();
        for (Subject s : allSubjects) {
            if (!nameSubjects.contains(s.name())) {
                nameSubjects.add(s.name());
            }
        }
        for (String str : nameSubjects) {
            int sum = 0;
            int count = 0;
            for (Subject sbj : allSubjects) {
                if (str.equals(sbj.name())) {
                    sum += sbj.score();
                    count++;
                }
            }
            averageScoreBySubject.add(new Label(str, (double) sum / count));
        }
        return averageScoreBySubject;
    }

    /**
     * Метод bestStudent() - возвращает лучшего ученика. Лучшим считается ученик с наибольшим
     * суммарным баллом по всем предметам. Возвращает объект Label (имя ученика и суммарный балл).
     * @param pupils - список учеников.
     * @return - лучший ученик.
     */
    public static Label bestStudent(List<Pupil> pupils) {
        String bestName = null;
        int bestSum = 0;
        for (Pupil pupil : pupils) {
            List<Subject> subjects = pupil.subjects();
            int sum = 0;
            for (Subject subject : subjects) {
                sum += subject.score();
            }
            if (bestName == null || bestSum < sum) {
                bestName = pupil.name();
                bestSum = sum;
            }
        }
        return new Label(bestName, bestSum);
    }

    /**
     * Метод bestSubject() - возвращает предмет с наибольшим баллом для всех студентов. Возвращает
     * объект Label (имя предмета, сумма баллов каждого ученика по этому предмету).
     * @param pupils - список учеников.
     * @return - предмет с наибольшим числом баллов.
     */
    public static Label bestSubject(List<Pupil> pupils) {
        return null;
    }
}
