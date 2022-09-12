import Client.FTPClientImpl;
import DataManipulation.DataManipulationImpl;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {

        FTPClientImpl ftpClient = new FTPClientImpl(args[0], Integer.parseInt(args[1]), args[2], args[3], Integer.parseInt(args[4]));
        ftpClient.open();
        DataManipulationImpl dataManipulation = new DataManipulationImpl(ftpClient);
        dataManipulation.initStudents();
        Scanner in = new Scanner(System.in);

        int number;
        while (true) {
            System.out.println("1.\tПолучение списка студентов по имени\n" +
                    "2.\tПолучение информации о студенте по id \n" +
                    "3.\tДобавление студента\n" +
                    "4.\tУдаление студента по id\n" +
                    "5.\tЗавершение работы\n");
            System.out.println("Введите номер: ");
            if (in.hasNextInt()) {
                number = in.nextInt();
            } else {
                throw new Exception("Неправильный номер");
            }

            switch (number) {
                case 1:
                    System.out.println(dataManipulation.getAllStudentsByName());
                    break;
                case 2:
                    int idInfo;
                    System.out.println("Введите id: ");
                    if (in.hasNextInt()) {
                        idInfo = in.nextInt();
                    } else {
                        throw new Exception("Неправильный номер");
                    }
                    System.out.println(dataManipulation.getStudentById(idInfo));
                    break;
                case 3:
                    String name = null;
                    System.out.println("Введите имя студента: ");
                    if (in.hasNext("([a-zA-Z]+)")) {
                        name = in.next();
                    } else {
                        throw new Exception("Неправильное имя");
                    }
                    System.out.println(dataManipulation.addStudentToList(name));
                    break;
                case 4:
                    int idDel;
                    System.out.println("Введите id: ");
                    if (in.hasNextInt()) {
                        idDel = in.nextInt();
                    } else {
                        throw new Exception("Неправильный номер");
                    }
                    System.out.println(dataManipulation.deleteStudentById(idDel));
                    break;
                case 5:
                    ftpClient.close();
                    System.exit(0);
                default:
                    throw new Exception("Неправильный номер");

            }
        }


    }
}
